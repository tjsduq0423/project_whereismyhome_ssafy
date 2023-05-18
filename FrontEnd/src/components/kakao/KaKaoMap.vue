<template>
  <div id="map" style="min-height: 80vh">
    <slot></slot>
  </div>
</template>

<script setup>
// kakao map 생성.
import { debounce } from 'lodash';
import { storeToRefs } from 'pinia';
import { tryOnMounted } from '@vueuse/core';
import { ref, watch } from 'vue';

import { useKakaoStore } from '@/stores/kakao';
const kakaoStore = useKakaoStore();
const { kakao, mapCenterLatLng } = storeToRefs(kakaoStore);

// 맵 초기화
const map = ref(null);
const initMap = () => {
  const container = document.getElementById('map');
  const [lat, lng] = mapCenterLatLng.value;
  const options = {
    center: new kakao.value.maps.LatLng(lat, lng),
    level: 5,
  };

  map.value = new kakao.value.maps.Map(container, options);
  // 중심좌표 추적
  kakao.value.maps.event.addListener(
    map.value,
    'center_changed',
    debounce(() => {
      const latlng = map.value.getCenter();
      mapCenterLatLng.value = [latlng.getLat(), latlng.getLng()];
    }, 500),
  );
};

// 맵 마운트
tryOnMounted(() => {
  kakao.value.maps.load(initMap);
});

//마커 제어
import { useMarkersStore } from '@/stores/markers';
const markersStore = useMarkersStore();
const { apartMarkers, sidoGugun } = storeToRefs(markersStore);
const { setApartMarkers, setCCTVMarkers, setHospitalMarkers, setSubwayMarkers, setBusMarkers } = markersStore;

// 중심좌표 추적 -> 마커 데이터 api call
watch(
  mapCenterLatLng,
  async v => {
    const [lat, lng] = v;
    await setApartMarkers(lng, lat);
    await setCCTVMarkers(lng, lat);
    await setHospitalMarkers(lng, lat);
    await setSubwayMarkers(lng, lat);
    await setBusMarkers(lng, lat);
  },
  { deep: true },
);

//apartMarker
const apartMarkerList = ref([]);
const mountApartMarkers = map => {
  console.log('mountApartMarkers', map);
  apartMarkerList.value.forEach(marker => {
    marker.setMap(map);
  });
};

watch(apartMarkers, v => {
  const imgSize = new kakao.value.maps.Size(35, 35);
  const markerImg = new kakao.value.maps.MarkerImage('/img/apartmentMarker.png', imgSize);
  mountApartMarkers(null);
  apartMarkerList.value = [];
  v.forEach(p =>
    apartMarkerList.value.push(
      new kakao.value.maps.Marker({
        position: new kakao.value.maps.LatLng(p.lat, p.lng),
        image: markerImg,
      }),
    ),
  );
  mountApartMarkers(map.value);
});

// 셀렉트 바 좌표 이동 (좌표이동 추가)
watch(
  sidoGugun,
  v => {
    const geocoder = new kakao.value.maps.services.Geocoder();
    const [sido, gugun] = v;
    geocoder.addressSearch(`${sido} ${gugun}`, (result, status) => {
      if (status === kakao.value.maps.services.Status.OK) {
        map.value.setCenter(new kakao.value.maps.LatLng(result[0].address.y, result[0].address.x));
      }
    });
  },
  { deep: true },
);
// function setCenter() {
//     // 이동할 위도 경도 위치를 생성합니다
//     var moveLatLon = new kakao.maps.LatLng(33.452613, 126.570888);

//     // 지도 중심을 이동 시킵니다
//     map.setCenter(moveLatLon);
// }
</script>

<style lang="scss" scoped></style>
