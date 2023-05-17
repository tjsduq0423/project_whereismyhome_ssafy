<template>
  <div id="map" style="min-height: 80vh">
    <transition
      appear
      mode="out-in"
      enter-active-class="animate__animated animate__slideInLeft"
      leave-active-class="animate__animated animate__slideOutLeft"
    >
      <AppSideBar v-show="showSideBar"></AppSideBar>
    </transition>
  </div>
</template>

<script setup>
import AppSideBar from './AppSideBar.vue';
import { debounce } from 'lodash';

// kakao map 생성.

import { useKakaoStore } from '@/stores/kakao';
import { storeToRefs } from 'pinia';
import { onMounted, ref, watch } from 'vue';
const showSideBar = ref(false);

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

watch(
  kakao,
  newValue => {
    newValue.maps.load(initMap);
  },
  { deep: true },
);
</script>

<style lang="scss" scoped></style>
