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
    <button @click="showSideBar = !showSideBar">사이드바 on/off</button>
    <KaKaoMap>
      <transition
        appear
        mode="out-in"
        enter-active-class="animate__animated animate__slideInLeft"
        leave-active-class="animate__animated animate__slideOutLeft"
      >
        <AppSideBar v-show="showSideBar"></AppSideBar>
      </transition>
    </KaKaoMap>
  </AppContent>
</template>

<script setup>
import AppSideBar from '@/components/AppSideBar.vue';
import KaKaoMap from '@/components/KaKaoMap.vue';
import AppContent from '@/components/AppContent.vue';
import { ref } from 'vue';

const showSideBar = ref(false);
</script>

<style lang="scss" scoped>
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
a {
  font-weight: bold;
  text-decoration: none;
  color: black;
  &:hover {
    border-bottom: 0.35rem solid black;
  }
  &.router-link-active {
    border-bottom: 0.35rem solid black;
  }
}
</style>
