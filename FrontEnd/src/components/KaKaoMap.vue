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
import { onMounted, ref, watch, watchEffect } from 'vue';
import { getHouseMarkers } from '@/api/amen';

const kakaoStore = useKakaoStore();
const { kakao } = storeToRefs(kakaoStore);

const mapCenterLatLng = ref([]);
const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.value.maps.LatLng(36.35536036402348, 127.29840381673439),
    level: 5,
  };

  const map = new kakao.value.maps.Map(container, options);

  kakao.value.maps.event.addListener(
    map,
    'center_changed',
    debounce(() => {
      const latlng = map.getCenter();
      mapCenterLatLng.value = [latlng.getLat(), latlng.getLng()];
    }, 500),
  );
};

onMounted(() => {
  try {
    if (kakao.value) {
      kakao.value.maps.load(initMap);
    }
  } catch (err) {
    console.error(err);
  }
});

watchEffect(async () => {
  const [lat, lng] = mapCenterLatLng.value;
  const response = await getHouseMarkers(lng, lat);
  console.log(response);
  console.log(response.data);
});

watch(
  kakao,
  newValue => {
    newValue.maps.load(initMap);
  },
  { deep: true },
);
</script>

<style lang="scss" scoped></style>
