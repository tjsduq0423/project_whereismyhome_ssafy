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
          <RouterLink class="fs-4" :to="{ name: 'MyBookMarkInMapView' }"
            >북마크 매매 정보</RouterLink
          >
        </div>
        <div class="col-auto mp"></div>
      </div>
    </div>
    <MyKaKaoMap v-if="!delayMap">
      <transition
        appear
        mode="out-in"
        enter-active-class="animate__animated animate__slideInLeft"
        leave-active-class="animate__animated animate__slideOutLeft"
      >
        <AptInfoSideBar v-show="showSideBar"></AptInfoSideBar>
      </transition>
      <transition
        appear
        mode="out-in"
        enter-active-class="animate__animated animate__slideInLeft"
        leave-active-class="animate__animated animate__slideOutLeft"
      >
        <MyAptInfoSideBar v-show="!showSideBar"></MyAptInfoSideBar>
      </transition>
    </MyKaKaoMap>
    <div
      v-else
      class="d-flex flex-column align-items-center justify-content-center"
      style="min-height: 80vh"
    >
      <div
        class="spinner-border"
        role="status"
        style="height: 10vh; width: 10vh; border-width: 0.5rem"
      >
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>
  </AppContent>
</template>

<script setup>
import MyKaKaoMap from '@/components/kakao/MyKaKaoMap.vue';
import AptInfoSideBar from '@/components/features/AptInfoSideBar.vue';
import MyAptInfoSideBar from '@/components/features/MyAptInfoSideBar.vue';
import AppContent from '@/components/layouts/AppContent.vue';
import { ref } from 'vue';

//delay sninner
const delayMap = ref(true);
setTimeout(() => {
  delayMap.value = false;
}, 1200);
// sidebar isShow 제어
import { useSideBarStore } from '@/stores/sideBar';
import { storeToRefs } from 'pinia';
import { useBookmarkStore } from '@/stores/bookmark';
import { useAuthStore } from '@/stores/auth';
const { userInfo } = storeToRefs(useAuthStore());
const sideBarStore = useSideBarStore();
const { showSideBar } = storeToRefs(sideBarStore);
const bookmarkStore = useBookmarkStore();
const { setBookmarkList } = bookmarkStore;

const settingBookMark = async () => {
  await setBookmarkList(userInfo.value.id);
};
settingBookMark();
</script>

<style lang="scss" scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('/img/apartment00.jpg');
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
