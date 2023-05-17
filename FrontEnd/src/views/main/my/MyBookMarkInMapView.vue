<template>
  <AppContent class="img">
    <template #header><div class="aptInfoHeader"></div></template>

    <div class="container-fluid card-header">
      <div class="row bg-light p-2">
        <!-- 아파트 북마크 선택 탭 taps -->
        <div class="col-auto d-flex align-items-center">
          <RouterLink class="fs-4" :to="{ name: 'AptInfo' }">아파트 매매 정보</RouterLink>
        </div>
        <div class="col-auto d-flex align-items-center">
          <RouterLink class="fs-4" :to="{ name: 'MyBookMarkInMapView' }">북마크 매매 정보</RouterLink>
        </div>
        <div class="col-auto mp"></div>
      </div>
    </div>

    <button @click="testFunction">사이드바 on/off</button>

    <!-- 맵 + 사이드 바 -->
    <div id="map" style="min-height: 80vh">
      <AppSideBar v-if="showSideBar"></AppSideBar>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppSideBar from '@/components/AppSideBar.vue';
import { onMounted, ref } from 'vue';
// 아파트 매매 정보 관련 사이드바 제어
const showSideBar = ref(false);
const testFunction = async () => {
  showSideBar.value = !showSideBar.value;
};

// kakao map 생성.
/* global kakao */
const map = ref(null);
const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 5,
  };
  map.value = new kakao.maps.Map(container, options);
};

onMounted(() => {
  try {
    kakao.maps.load(initMap);
  } catch (err) {
    console.error(err);
  }
});
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)), url('@/assets/img/apartment00.jpg');
}
.searchIcon {
  position: absolute;
  top: 0;
  right: 0;
  transform: translate(-60%, 20%);
  font-size: 20px;
  font-weight: bolder;
}

.aptInfoHeader {
  margin: 0px 0vw 15vh 0vw;
}
.mp {
  margin-right: 60vw;
}
</style>
