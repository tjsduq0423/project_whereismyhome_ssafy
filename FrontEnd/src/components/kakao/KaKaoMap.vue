<template>
  <div id="map" style="min-height: 80vh">
    <slot></slot>
  </div>
</template>

<script setup>
// kakao map 생성.
import { debounce } from 'lodash';
import { storeToRefs } from 'pinia';
import { useKakaoStore } from '@/stores/kakao';
import { ref, watch, watchEffect } from 'vue';
import { tryOnMounted } from '@vueuse/core';

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

import { useMarkersStore } from '@/stores/markers';
const markersStore = useMarkersStore();
const { houseMarkers } = storeToRefs(markersStore);
const { setHouseMarkers } = markersStore;
// 중심좌표 추적 -> aptInfo api call
watchEffect(async () => {
  const [lat, lng] = mapCenterLatLng.value;
  await setHouseMarkers(lng, lat);
});

watch(houseMarkers, v => {
  const imgSize = new kakao.value.maps.Size(35, 35);
  const markerImg = new kakao.value.maps.MarkerImage('/img/apartmentMarker.png', imgSize);
  v.forEach(
    p =>
      new kakao.value.maps.Marker({
        map: map.value,
        position: new kakao.value.maps.LatLng(p.lat, p.lng),
        title: p.apartmentName,
        image: markerImg,
      }),
  );
});
</script>

<style lang="scss" scoped></style>
