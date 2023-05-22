<template>
  <div id="map" style="min-height: 80vh">
    <slot></slot>
  </div>
</template>

<script setup>
// kakao map 생성.
import { ref, watch } from 'vue';
import { storeToRefs } from 'pinia';
import { tryOnMounted } from '@vueuse/core';
import { useKakaoStore } from '@/stores/kakao';
import { useLoading } from '@/composables/loading';
import { useDebounceFn } from '@vueuse/core';

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
  map.value.addControl(new kakao.value.maps.ZoomControl(), kakao.value.maps.ControlPosition.RIGHT);
  // 지도 확대 축소 이벤트
  kakao.value.maps.event.addListener(map.value, 'zoom_changed', () => {
    const level = map.value.getLevel();
    if (level > 3) {
      if (isShowCCTV.value) onOffMarkers(false, cctvMarkerList.value);
      if (isShowHospital.value) onOffMarkers(false, hospitalMarkerList.value);
      if (isShowBus.value) onOffMarkers(false, busMarkerList.value);
      if (isShowSchool.value) onOffMarkers(false, subwayMarkerList.value);
      if (isShowSubway.value) onOffMarkers(false, schoolMarkerList.value);
    } else {
      if (isShowCCTV.value) onOffMarkers(true, cctvMarkerList.value);
      if (isShowHospital.value) onOffMarkers(true, hospitalMarkerList.value);
      if (isShowBus.value) onOffMarkers(true, busMarkerList.value);
      if (isShowSchool.value) onOffMarkers(true, schoolMarkerList.value);
      if (isShowSubway.value) onOffMarkers(true, subwayMarkerList.value);
    }
  });
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

// 맵 마운트
tryOnMounted(() => {
  kakao.value.maps.load(initMap);
});
// memberId
import { useAuthStore } from '@/stores/auth';
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);
// 북마크
import { useBookmarkStore } from '@/stores/bookmark';
const bookmarkStore = useBookmarkStore();
const { getBookmarkList } = bookmarkStore;
// 사이드바
import { useSideBarStore } from '@/stores/sideBar';
const sideBarStore = useSideBarStore();
const { showSideBar, lodaViewLatLng } = storeToRefs(sideBarStore);
const { setAptInfo, setAptDealInfo, setAptRankByCode } = sideBarStore;
//마커
import { useMarkersStore } from '@/stores/markers';
const markersStore = useMarkersStore();
const {
  apartMarkers,
  schoolMarkers,
  // cctvMarkers,
  hospitalMarkers,
  subwayMarkers,
  busMarkers,
  isShowCCTV,
  isShowSchool,
  isShowHospital,
  isShowSubway,
  isShowBus,
  sidoGugun,
} = storeToRefs(markersStore);
const {
  setApartMarkers,
  setSchoolMarkers,
  setCCTVMarkers,
  setHospitalMarkers,
  setSubwayMarkers,
  setBusMarkers,
} = markersStore;

// 중심좌표 추적 -> 마커 데이터 api call
watch(mapCenterLatLng, async v => {
  // zoomLevel
  // const level = map.value.getLevel();
  const [lat, lng] = v;
  await setApartMarkers(lng, lat);
  await setSchoolMarkers(lng, lat);
  await setCCTVMarkers(lng, lat);
  await setHospitalMarkers(lng, lat);
  await setSubwayMarkers(lng, lat);
  await setBusMarkers(lng, lat);
});

//아파트 마운트 및 필터를 위한 반응형 변수들.
const apartMarkerList = ref([]);
const schoolMarkerList = ref([]);
const cctvMarkerList = ref([]);
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

// 마커 좌표를 가지고 있는 배열들을 감시하여 맵에 마커 mount unmount
// watch debounce 사용할 것.
watch(apartMarkers, v => {
  const imgSize = new kakao.value.maps.Size(35, 40);
  const markerImg = new kakao.value.maps.MarkerImage('/img/apartmentMarker.png', imgSize);
  // 모든 아파트 다 찍을 거임. 로딩 중에 찍을 거고 감시 없이 클러스터링
  mountMarkers(null, apartMarkerList.value);
  apartMarkerList.value = [];
  v.forEach(p => {
    const marker = new kakao.value.maps.Marker({
      map: map.value,
      position: new kakao.value.maps.LatLng(p.lat, p.lng),
      image: markerImg,
      clickable: true,
    });
    kakao.value.maps.event.addListener(marker, 'click', async () => {
      if (showSideBar.value === true) showSideBar.value = false;
      try {
        lodaViewLatLng.value = [p.lat, p.lng];
        await setAptInfo(p.aptCode, p.apartmentName);
        await setAptDealInfo(p.aptCode);
        await setAptRankByCode(p.aptCode);
        await getBookmarkList(userInfo.value.id);
        showSideBar.value = true;
      } catch (err) {
        console.error(err);
      }
    });
    apartMarkerList.value.push(marker);
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
      yAnchor: 0,
      xAnchor: 0,
    });
    const customOverlay = new kakao.value.maps.CustomOverlay({
      map: map.value,
      position: marker.getPosition(),
      content: `<div class="card mt-5 text-center">
                  <div class="card-body p-2">
                    <p class="card-text">${p.name}</p>
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
// cctv 감시
// watch(cctvMarkers, v => {
//   const imgSize = new kakao.value.maps.Size(10, 10);
//   const markerImg = new kakao.value.maps.MarkerImage('/img/cctvMarker.png', imgSize);
//   mountMarkers(null, cctvMarkerList.value);
//   cctvMarkerList.value = [];
//   v.forEach(p => {
//     const marker = new kakao.value.maps.Marker({
//       map: map.value,
//       position: new kakao.value.maps.LatLng(p.lat, p.lng),
//       image: markerImg,
//       clickable: true,
//     });
//     const customOverlay = new kakao.value.maps.CustomOverlay({
//       map: map.value,
//       position: marker.getPosition(),
//       content: `<div class="card mt-5 text-center">
//                   <div class="card-body p-2">
//                     <p class="card-text">용도 - ${p.usage}</p>
//                   </div>
//                 </div>`,
//       clickable: true,
//       zIndex: 10,
//     });
//     customOverlay.setVisible(false);
//     kakao.value.maps.event.addListener(marker, 'click', () => {
//       customOverlay.setVisible(true);
//       setTimeout(() => {
//         customOverlay.setVisible(false);
//       }, 2000);
//     });
//     cctvMarkerList.value.push(marker);
//   });
//   onOffMarkers(map.value.getLevel() <= 3 && isShowCCTV.value, cctvMarkerList.value);
// });

// watch(isShowCCTV, v => {
//   onOffMarkers(map.value.getLevel() <= 3 && v, cctvMarkerList.value);
// });

// 병원 감시
watch(hospitalMarkers, v => {
  const imgSize = new kakao.value.maps.Size(25, 25);
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
                    <h6 class="card-title m-0">${p.name}</h6>
                    <p class="card-text">전화번호 - ${p.phoneNumber}</p>
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
                    <h6 class="card-title m-0">버스정류장 - ${p.name}</h6>
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
                    <h6 class="card-title m-0">역 - ${p.name}</h6>
                    <p class="card-text">전화번호 - ${p.phoneNumber}</p>
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
  const geocoder = new kakao.value.maps.services.Geocoder();
  geocoder.addressSearch(v, (result, status) => {
    if (status === kakao.value.maps.services.Status.OK) {
      map.value.setCenter(new kakao.value.maps.LatLng(result[0].address.y, result[0].address.x));
    }
  });
});
</script>

<style lang="scss" scoped></style>