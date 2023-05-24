<template>
  <div id="map" style="min-height: 80vh">
    <slot></slot>
  </div>
</template>

<script setup>
// kakao map 생성.
import { ref, watch } from 'vue';
import { storeToRefs } from 'pinia';
import { tryOnMounted, watchDebounced, useDebounceFn } from '@vueuse/core';
import { useKakaoStore } from '@/stores/kakao';
import { useLoading } from '@/composables/loading';
import { useCookies } from 'vue3-cookies';
import { plusViewCount } from '@/api/info';
const { cookies } = useCookies();
const { vLoading } = useLoading();
const kakaoStore = useKakaoStore();
const { kakao, mapCenterLatLng } = storeToRefs(kakaoStore);

// 맵 초기화
const map = ref(null);
const initMap = () => {
  const container = document.getElementById('map');
  const [lat, lng] = mapCenterLatLng.value;
  const options = {
    center: new kakao.value.maps.LatLng(lat, lng),
    level: 3,
  };
  //맵 생성 + zoom controller 부착
  map.value = new kakao.value.maps.Map(container, options);
  map.value.setMaxLevel(8);
  map.value.setZoomable(false);
  map.value.setKeyboardShortcuts(false);
  map.value.addControl(new kakao.value.maps.ZoomControl(), kakao.value.maps.ControlPosition.RIGHT);
  // 지도 확대 축소 이벤트
  kakao.value.maps.event.addListener(
    map.value,
    'zoom_changed',
    useDebounceFn(() => {
      const level = map.value.getLevel();
      if (level > 3) {
        if (isShowHospital.value) onOffMarkers(false, hospitalMarkerList.value);
        if (isShowBus.value) onOffMarkers(false, busMarkerList.value);
        if (isShowSchool.value) onOffMarkers(false, subwayMarkerList.value);
        if (isShowSubway.value) onOffMarkers(false, schoolMarkerList.value);
      } else {
        if (isShowHospital.value) onOffMarkers(true, hospitalMarkerList.value);
        if (isShowBus.value) onOffMarkers(true, busMarkerList.value);
        if (isShowSchool.value) onOffMarkers(true, schoolMarkerList.value);
        if (isShowSubway.value) onOffMarkers(true, subwayMarkerList.value);
      }
      if (level > 5) {
        zoomFilter.value = false;
        zoomFilter_Overlay.value = true;
      } else {
        zoomFilter.value = true;
        zoomFilter_Overlay.value = false;
      }
    }, 500),
  );
  // 중심좌표 추적 이벤트
  kakao.value.maps.event.addListener(
    map.value,
    'center_changed',
    useDebounceFn(() => {
      vLoading();
      const latlng = map.value.getCenter();
      mapCenterLatLng.value = [latlng.getLat(), latlng.getLng()];
    }, 500),
  );

  // 지도 클릭 시 사이드바 닫기
  kakao.value.maps.event.addListener(map.value, 'click', function (mouseEvent) {
    if (mouseEvent.target === mouseEvent.currentTarget) {
      showSideBar.value = false;
    }
  });
};

// 지역구별 커스텀 오버레이
const gugunOverlayList = ref([]);
const zoomFilter_Overlay = ref(false);

const initGugunOverlay = () => {
  const geocoder = new kakao.value.maps.services.Geocoder();
  avgInGugun.value.forEach(info => {
    geocoder.addressSearch(
      info.gugunName,
      (result, status) => {
        if (status === kakao.value.maps.services.Status.OK) {
          const latlng = new kakao.value.maps.LatLng(result[0]?.y, result[0]?.x);
          const contentHTML = document.createElement('div');
          contentHTML.classList.add('card');
          contentHTML.classList.add('rounded-pill');
          contentHTML.classList.add('text-bg-warning');
          contentHTML.innerHTML = `<div class="card-body text-center" style="font-size:0.75em;padding:1em">
                          <p class="m-0">
                            ${info.gugunName}
                          </p>
                          <p class="m-0">
                            ${Math.floor(info.avgMount / 10000)}억 ${info.avgMount % 10000}만원
                          </p>
                        </div>`;
          contentHTML.addEventListener('click', () => {
            map.value.setLevel(4);
            map.value.setCenter(latlng);
          });

          const customOverlay = new kakao.value.maps.CustomOverlay({
            map: map.value,
            clickable: true,
            content: contentHTML,
            position: latlng,
            zIndex: 300,
          });
          customOverlay.setVisible(zoomFilter_Overlay.value);
          gugunOverlayList.value.push(customOverlay);
        }
      },
      {
        size: 1,
      },
    );
  });
};

watch(zoomFilter_Overlay, v => {
  gugunOverlayList.value.forEach(overlay => {
    overlay.setVisible(v);
  });
});

// 맵 마운트
tryOnMounted(async () => {
  kakao.value.maps.load(initMap);
  await setAvgInGugun();
  initGugunOverlay();
});

// memberId
import { useAuthStore } from '@/stores/auth';
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);
// 북마크
import { useBookmarkStore } from '@/stores/bookmark';
const bookmarkStore = useBookmarkStore();
const { setBookmarkList } = bookmarkStore;
// 사이드바
import { useSideBarStore } from '@/stores/sideBar';
const sideBarStore = useSideBarStore();
const { showSideBar, lodaViewLatLng } = storeToRefs(sideBarStore);
const { setAptInfo, setAptDealInfo, setAptRankByCode, setAptToAmenDistanceInfo } = sideBarStore;
//마커
import { useMarkersStore } from '@/stores/markers';
const markersStore = useMarkersStore();
const {
  avgInGugun,
  schoolMarkers,
  hospitalMarkers,
  apartMarkers,
  subwayMarkers,
  busMarkers,
  isShowSchool,
  isShowHospital,
  isShowSubway,
  isShowBus,
  sidoGugun,
  priceRange,
  buildYearRange,
} = storeToRefs(markersStore);
const {
  setApartMarkers,
  setSchoolMarkers,
  setHospitalMarkers,
  setSubwayMarkers,
  setBusMarkers,
  setAvgInGugun,
} = markersStore;

// 중심좌표 추적 -> 마커 데이터 api call
watchDebounced(
  mapCenterLatLng,
  async v => {
    const zoomlevel = map.value.getLevel();
    const [lat, lng] = v;
    await setApartMarkers(lng, lat, zoomlevel);
    await setSchoolMarkers(lng, lat, zoomlevel);
    await setHospitalMarkers(lng, lat, zoomlevel);
    await setSubwayMarkers(lng, lat, zoomlevel);
    await setBusMarkers(lng, lat, zoomlevel);
  },
  { debounce: 500, maxWait: 5000 },
);

//아파트 마운트 및 필터를 위한 반응형 변수들.
const apartMarkerList = ref([]);
const schoolMarkerList = ref([]);
const hospitalMarkerList = ref([]);
const busMarkerList = ref([]);
const subwayMarkerList = ref([]);
// loading 변수

// mount 함수
// 마커 mount
const mountMarkers = (map, markerList) => {
  markerList.forEach(marker => {
    marker.setMap(map);
  });
};
const onOffMarkers = (isShow, markerList) => {
  markerList.forEach(marker => {
    marker.setVisible(isShow);
  });
};
// 전국구( 강남구 평균 매매가 ~원) 해당하는 평균 매매가와 위치별 커스텀 오버레이 생성  => 해당 오버레이 클릭시 줌
// 마커 필터링 watcher
const zoomFilter = ref(true);
watchDebounced(
  [priceRange, buildYearRange, zoomFilter],
  ([newPriceRange, newBuildYearRange, newZoomFilter]) => {
    apartMarkerList.value.forEach(aptMarker => {
      const avg = aptMarker.info.avg;
      const bulidYear = aptMarker.info.buildYear;
      if (
        (parseInt(newPriceRange) === 21 || avg / 10000 < parseInt(newPriceRange)) &&
        2023 - bulidYear <= newBuildYearRange &&
        newZoomFilter
      ) {
        aptMarker.marker.setVisible(true);
      } else {
        aptMarker.marker.setVisible(false);
      }
    });
  },
  {
    debounce: 250,
    maxWait: 5000,
  },
);

watch(apartMarkers, v => {
  const imgSize = new kakao.value.maps.Size(35, 40);
  const markerImg = new kakao.value.maps.MarkerImage('/img/apartmentMarker.png', imgSize);
  mountMarkers(
    null,
    apartMarkerList.value.map(el => el.marker),
  );
  apartMarkerList.value = [];
  v.forEach(info => {
    const marker = new kakao.value.maps.Marker({
      map: map.value,
      position: new kakao.value.maps.LatLng(info.lat, info.lng),
      image: markerImg,
      clickable: true,
    });
    kakao.value.maps.event.addListener(marker, 'click', async () => {
      if (showSideBar.value === true) showSideBar.value = false;
      try {
        lodaViewLatLng.value = [info.lat, info.lng];
        await setAptInfo(info.aptCode, info.apartmentName);
        await setAptDealInfo(info.aptCode);
        await setAptRankByCode(info.aptCode);
        await setBookmarkList(userInfo.value.id);
        await setAptToAmenDistanceInfo(info.aptCode);
        if (!cookies.isKey(info.aptCode)) {
          cookies.set(info.aptCode, 'code', '30d');
          await plusViewCount(info.aptCode);
        }
        showSideBar.value = true;
      } catch (err) {
        console.error(err);
      }
    });
    marker.setVisible(zoomFilter.value);
    apartMarkerList.value.push({ marker, info });
  });
});

// 학교 감시
watch(schoolMarkers, v => {
  const imgSize = new kakao.value.maps.Size(40, 40);
  const markerImg = new kakao.value.maps.MarkerImage('/img/schoolMarker.png', imgSize);
  mountMarkers(null, schoolMarkerList.value);
  schoolMarkerList.value = [];
  v.forEach(p => {
    const marker = new kakao.value.maps.Marker({
      map: map.value,
      position: new kakao.value.maps.LatLng(p.lat, p.lng),
      image: markerImg,
      clickable: true,
    });
    const customOverlay = new kakao.value.maps.CustomOverlay({
      map: map.value,
      position: marker.getPosition(),
      content: `<div class="card mt-5 text-center">
                  <div class="card-body p-2">
                    <p class="card-text fs-5">${p.name}</p>
                  </div>
                </div>`,
      clickable: true,
      zIndex: 10,
    });
    customOverlay.setVisible(false);
    kakao.value.maps.event.addListener(marker, 'click', () => {
      customOverlay.setVisible(true);
      setTimeout(() => {
        customOverlay.setVisible(false);
      }, 2000);
    });
    schoolMarkerList.value.push(marker);
  });
  onOffMarkers(map.value.getLevel() <= 3 && isShowSchool.value, schoolMarkerList.value);
});

watch(isShowSchool, v => {
  onOffMarkers(map.value.getLevel() <= 3 && v, schoolMarkerList.value);
});

// 병원 감시
watch(hospitalMarkers, v => {
  const imgSize = new kakao.value.maps.Size(20, 20);
  const markerImg = new kakao.value.maps.MarkerImage('/img/hospitalMarker.png', imgSize);
  mountMarkers(null, hospitalMarkerList.value);
  hospitalMarkerList.value = [];
  v.forEach(p => {
    const marker = new kakao.value.maps.Marker({
      map: map.value,
      position: new kakao.value.maps.LatLng(p.lat, p.lng),
      image: markerImg,
      clickable: true,
    });
    const customOverlay = new kakao.value.maps.CustomOverlay({
      map: map.value,
      position: marker.getPosition(),
      content: `<div class="card mt-5 text-center">
                  <div class="card-body p-2">
                    <h5 class="card-title m-0">${p.name}</h5>
                    <p class="card-text">${p.phoneNumber ? `전화번호 : ${p.phoneNumber}` : ''}</p>
                  </div>
                </div>`,
      clickable: true,
      zIndex: 10,
    });
    customOverlay.setVisible(false);
    kakao.value.maps.event.addListener(marker, 'click', () => {
      customOverlay.setVisible(true);
      setTimeout(() => {
        customOverlay.setVisible(false);
      }, 2000);
    });
    hospitalMarkerList.value.push(marker);
  });

  onOffMarkers(map.value.getLevel() <= 3 && isShowHospital.value, hospitalMarkerList.value);
});

watch(isShowHospital, v => {
  onOffMarkers(map.value.getLevel() <= 3 && v, hospitalMarkerList.value);
});

//버스정류장 감시
watch(busMarkers, v => {
  const imgSize = new kakao.value.maps.Size(25, 25);
  const markerImg = new kakao.value.maps.MarkerImage('/img/busMarker.png', imgSize);
  mountMarkers(null, busMarkerList.value);
  busMarkerList.value = [];
  v.forEach(p => {
    const marker = new kakao.value.maps.Marker({
      map: map.value,
      position: new kakao.value.maps.LatLng(p.lat, p.lng),
      image: markerImg,
      clickable: true,
    });
    const customOverlay = new kakao.value.maps.CustomOverlay({
      map: map.value,
      position: marker.getPosition(),
      content: `<div class="card mt-5 text-center">
                  <div class="card-body p-2">
                    <h5 class="card-title m-0">정류장 이름 : ${p.name}</h5>
                  </div>
                </div>`,
      clickable: true,
      zIndex: 10,
    });
    customOverlay.setVisible(false);
    kakao.value.maps.event.addListener(marker, 'click', () => {
      customOverlay.setVisible(true);
      setTimeout(() => {
        customOverlay.setVisible(false);
      }, 2000);
    });
    busMarkerList.value.push(marker);
  });

  onOffMarkers(map.value.getLevel() <= 3 && isShowBus.value, busMarkerList.value);
});

watch(isShowBus, v => {
  onOffMarkers(map.value.getLevel() <= 3 && v, busMarkerList.value);
});

// 지하철 감시
watch(subwayMarkers, v => {
  const imgSize = new kakao.value.maps.Size(40, 40);
  const markerImg = new kakao.value.maps.MarkerImage('/img/subwayMarker.png', imgSize);
  mountMarkers(null, subwayMarkerList.value);
  subwayMarkerList.value = [];
  v.forEach(p => {
    const marker = new kakao.value.maps.Marker({
      map: map.value,
      position: new kakao.value.maps.LatLng(p.lat, p.lng),
      image: markerImg,
      clickable: true,
    });
    const customOverlay = new kakao.value.maps.CustomOverlay({
      map: map.value,
      position: marker.getPosition(),
      content: `<div class="card mt-5 text-center">
                  <div class="card-body p-2">
                    <h4 class="card-title m-0">${p.name}역</h4>
                    <p class="card-text">${
                      p.phoneNumber !== 'null' ? `전화번호 : ${p.phoneNumber}` : ''
                    }</p>
                  </div>
                </div>`,
      clickable: true,
      zIndex: 10,
    });
    customOverlay.setVisible(false);
    kakao.value.maps.event.addListener(marker, 'click', () => {
      customOverlay.setVisible(true);
      setTimeout(() => {
        customOverlay.setVisible(false);
      }, 2000);
    });
    subwayMarkerList.value.push(marker);
  });

  onOffMarkers(map.value.getLevel() <= 3 && isShowSubway.value, subwayMarkerList.value);
});

watch(isShowSubway, v => {
  onOffMarkers(map.value.getLevel() <= 3 && v, subwayMarkerList.value);
});

// 셀렉트 바 좌표 이동 (좌표이동 추가)
watch(sidoGugun, v => {
  const geocoder = new kakao.value.maps.services.Geocoder();
  const [sido, gugun] = v;
  geocoder.addressSearch(`${sido} ${gugun}`, (result, status) => {
    if (status === kakao.value.maps.services.Status.OK) {
      map.value.setCenter(new kakao.value.maps.LatLng(result[0].address.y, result[0].address.x));
    }
  });
});
// searchBar 이벤트 감시
import { useSearchStore } from '@/stores/search';
const searchStore = useSearchStore();
const { selectedSearchInput } = storeToRefs(searchStore);

watch(selectedSearchInput, v => {
  const places = new kakao.value.maps.services.Places();
  const [name, category] = v;
  places.keywordSearch(
    name,
    (result, status) => {
      if (status === kakao.value.maps.services.Status.OK) {
        map.value.setCenter(new kakao.value.maps.LatLng(result[0]?.y, result[0]?.x));
      }
    },
    { category_group_code: category },
  );
});
// 지역구 별 오버레이 생성
</script>

<style lang="scss" scoped></style>
