<template>
  <div id="map" style="min-height: 80vh">
    <slot></slot>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { storeToRefs } from 'pinia';
import { tryOnMounted } from '@vueuse/core';
import { useKakaoStore } from '@/stores/kakao';
import { useBookmarkStore } from '@/stores/bookmark';
import { useSideBarStore } from '@/stores/sideBar';
const bookmarkStore = useBookmarkStore();
const { bookmarkList, centerlatlng } = storeToRefs(bookmarkStore);
const kakaoStore = useKakaoStore();
const { kakao } = storeToRefs(kakaoStore);
const sideBarStore = useSideBarStore();
const { showSideBar, lodaViewLatLng } = storeToRefs(sideBarStore);
const { setAptInfo, setAptDealInfo, setAptRankByCode, setAptToAmenDistanceInfo } = sideBarStore;

const map = ref(null);
const initMap = () => {
  const container = document.getElementById('map');
  const [lat, lng] = centerlatlng.value;
  const options = {
    center: new kakao.value.maps.LatLng(lat, lng),
    level: 3,
  };
  map.value = new kakao.value.maps.Map(container, options);
  map.value.setMaxLevel(8);
  map.value.setZoomable(false);
  map.value.setKeyboardShortcuts(false);
  map.value.addControl(new kakao.value.maps.ZoomControl(), kakao.value.maps.ControlPosition.RIGHT);
  kakao.value.maps.event.addListener(map.value, 'click', function (mouseEvent) {
    if (mouseEvent.target === mouseEvent.currentTarget) {
      showSideBar.value = false;
    }
  });
  settingMarkers();
};
watch(centerlatlng, v => {
  const [lat, lng] = v;
  map.value.setCenter(new kakao.value.maps.LatLng(lat, lng));
});
const settingMarkers = () => {
  const imgSize = new kakao.value.maps.Size(35, 40);
  const markerImg = new kakao.value.maps.MarkerImage('/img/apartmentMarker.png', imgSize);
  bookmarkList.value.forEach(info => {
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
        await setAptInfo(info.aptCode, info.aptName);
        await setAptDealInfo(info.aptCode);
        await setAptRankByCode(info.aptCode);
        await setAptToAmenDistanceInfo(info.aptCode);
        showSideBar.value = true;
      } catch (err) {
        console.error(err);
      }
    });
  });
};
tryOnMounted(() => {
  kakao.value.maps.load(initMap);
});
</script>

<style lang="scss" scoped></style>
