<template>
  <div id="roadViewContainer" style="width: 100%; height: 30vh"></div>
</template>

<script setup>
//kakao.value 로드 뷰
import { useKakaoStore } from '@/stores/kakao';
import { useSideBarStore } from '@/stores/sideBar';
import { tryOnMounted } from '@vueuse/core';
import { storeToRefs } from 'pinia';
const kakaoStore = useKakaoStore();
const sideBarStore = useSideBarStore();
const { lodaViewLatLng } = storeToRefs(sideBarStore);
const { kakao } = storeToRefs(kakaoStore);

const createLoadView = (lat, lng) => {
  const roadViewContainer = document.getElementById('roadViewContainer');
  const roadview = new kakao.value.maps.Roadview(roadViewContainer);
  const roadviewClient = new kakao.value.maps.RoadviewClient();
  const position = new kakao.value.maps.LatLng(lat, lng);
  // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
  roadviewClient.getNearestPanoId(position, 50, function (panoId) {
    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
  });
};

tryOnMounted(() => {
  const [lat, lng] = lodaViewLatLng.value;
  kakao.value.maps.load(() => {
    createLoadView(lat, lng);
  });
});
</script>

<style lang="scss" scoped></style>
