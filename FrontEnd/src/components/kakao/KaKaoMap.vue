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
  //맵 생성.
  map.value = new kakao.value.maps.Map(container, options);
  map.value.setZoomable(false);
  map.value.addControl(new kakao.value.maps.ZoomControl(), kakao.value.maps.ControlPosition.RIGHT);

  // 중심좌표 추적 이벤트
  kakao.value.maps.event.addListener(map.value, 'dragend', () => {
    vLoading(delayTimeByMarker.value);
    const latlng = map.value.getCenter();
    mapCenterLatLng.value = [latlng.getLat(), latlng.getLng()];
  });

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

//마커 제어
import { useMarkersStore } from '@/stores/markers';
const markersStore = useMarkersStore();
const {
  showSideBar,
  delayTimeByMarker,
  apartMarkers,
  schoolMarkers,
  cctvMarkers,
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
// infoWindow 마커 클로저 함수
const makeOverListener = (map, customOverlay) => {
  return function () {
    console.log('test');
    customOverlay.setMap(map);
  };
};
// 마커 좌표를 가지고 있는 배열들을 감시하여 맵에 마커 mount unmount
// 아파트 감시
watch(apartMarkers, v => {
  const imgSize = new kakao.value.maps.Size(35, 40);
  const markerImg = new kakao.value.maps.MarkerImage('/img/apartmentMarker.png', imgSize);
  mountMarkers(null, apartMarkerList.value);
  apartMarkerList.value = [];
  v.forEach(p =>
    apartMarkerList.value.push(
      new kakao.value.maps.Marker({
        position: new kakao.value.maps.LatLng(p.lat, p.lng),
        image: markerImg,
      }),
    ),
  );
  mountMarkers(map.value, apartMarkerList.value);
});

// 학교 감시
watch(schoolMarkers, v => {
  const imgSize = new kakao.value.maps.Size(35, 35);
  const markerImg = new kakao.value.maps.MarkerImage('/img/schoolMarker.png', imgSize);
  mountMarkers(null, schoolMarkerList.value);
  schoolMarkerList.value = [];
  v.forEach(p => {
    const marker = new kakao.value.maps.Marker({
      position: new kakao.value.maps.LatLng(p.lat, p.lng),
      image: markerImg,
    });
    const customOverlay = new kakao.value.maps.CustomOverlay({
      position: new kakao.value.maps.LatLng(p.lat, p.lng),
      content: '<div style="padding:5px;">Hello World!</div>',
    });
    kakao.value.maps.event.addListener(
      marker,
      'mouseover',
      makeOverListener(map.value, customOverlay),
    );
    kakao.value.maps.event.addListener(marker, 'mouseout', makeOverListener(null, customOverlay));
    schoolMarkerList.value.push(marker);
  });
  if (isShowSchool.value === false) return;
  mountMarkers(map.value, schoolMarkerList.value);
});

watch(isShowSchool, v => {
  if (v === false) {
    mountMarkers(null, schoolMarkerList.value);
  } else {
    mountMarkers(map.value, schoolMarkerList.value);
  }
});
// cctv 감시
watch(cctvMarkers, v => {
  const imgSize = new kakao.value.maps.Size(15, 15);
  const markerImg = new kakao.value.maps.MarkerImage('/img/cctvMarker.png', imgSize);
  mountMarkers(null, cctvMarkerList.value);
  cctvMarkerList.value = [];
  v.forEach(p =>
    cctvMarkerList.value.push(
      new kakao.value.maps.Marker({
        position: new kakao.value.maps.LatLng(p.lat, p.lng),
        image: markerImg,
      }),
    ),
  );
  if (isShowCCTV.value === false) return;
  mountMarkers(map.value, cctvMarkerList.value);
});

watch(isShowCCTV, v => {
  if (v === false) {
    mountMarkers(null, cctvMarkerList.value);
  } else {
    mountMarkers(map.value, cctvMarkerList.value);
  }
});

// 병원 감시
watch(hospitalMarkers, v => {
  const imgSize = new kakao.value.maps.Size(20, 20);
  const markerImg = new kakao.value.maps.MarkerImage('/img/hospitalMarker.png', imgSize);
  mountMarkers(null, hospitalMarkerList.value);
  hospitalMarkerList.value = [];
  v.forEach(p =>
    hospitalMarkerList.value.push(
      new kakao.value.maps.Marker({
        position: new kakao.value.maps.LatLng(p.lat, p.lng),
        image: markerImg,
      }),
    ),
  );
  if (isShowHospital.value === false) return;
  mountMarkers(map.value, hospitalMarkerList.value);
});

watch(isShowHospital, v => {
  if (v === false) {
    mountMarkers(null, hospitalMarkerList.value);
  } else {
    mountMarkers(map.value, hospitalMarkerList.value);
  }
});

//버스정류장 감시
watch(busMarkers, v => {
  const imgSize = new kakao.value.maps.Size(15, 15);
  const markerImg = new kakao.value.maps.MarkerImage('/img/busMarker.png', imgSize);
  mountMarkers(null, busMarkerList.value);
  busMarkerList.value = [];
  v.forEach(p =>
    busMarkerList.value.push(
      new kakao.value.maps.Marker({
        position: new kakao.value.maps.LatLng(p.lat, p.lng),
        image: markerImg,
      }),
    ),
  );
  if (isShowBus.value === false) return;
  mountMarkers(map.value, busMarkerList.value);
});

watch(isShowBus, v => {
  if (v === false) {
    mountMarkers(null, busMarkerList.value);
  } else {
    mountMarkers(map.value, busMarkerList.value);
  }
});

// 지하철 감시
watch(subwayMarkers, v => {
  const imgSize = new kakao.value.maps.Size(30, 30);
  const markerImg = new kakao.value.maps.MarkerImage('/img/subwayMarker.png', imgSize);
  mountMarkers(null, subwayMarkerList.value);
  subwayMarkerList.value = [];
  v.forEach(p =>
    subwayMarkerList.value.push(
      new kakao.value.maps.Marker({
        position: new kakao.value.maps.LatLng(p.lat, p.lng),
        image: markerImg,
      }),
    ),
  );
  if (isShowSubway.value === false) return;
  mountMarkers(map.value, subwayMarkerList.value);
});

watch(isShowSubway, v => {
  if (v === false) {
    mountMarkers(null, subwayMarkerList.value);
  } else {
    mountMarkers(map.value, subwayMarkerList.value);
  }
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
</script>

<style lang="scss" scoped></style>
