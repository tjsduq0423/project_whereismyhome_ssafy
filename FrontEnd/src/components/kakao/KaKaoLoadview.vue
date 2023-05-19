<template>
  <div id="roadViewContainer" style="width: 100%; height: 30vh"></div>
</template>

<script setup>
//kakao.value 로드 뷰
import { useKakaoStore } from '@/stores/kakao';
import { storeToRefs } from 'pinia';
import { onMounted, watch } from 'vue';
const kakaoStore = useKakaoStore();
const { kakao } = storeToRefs(kakaoStore);

const createLoadView = (lat = '33.450701', lng = '126.570667') => {
  const roadViewContainer = document.getElementById('roadViewContainer');
  const roadview = new kakao.value.maps.Roadview(roadViewContainer);
  const roadviewClient = new kakao.value.maps.RoadviewClient();
  const position = new kakao.value.maps.LatLng(lat, lng);
  // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
  roadviewClient.getNearestPanoId(position, 50, function (panoId) {
    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
  });
};

onMounted(() => {
  try {
    if (kakao.value === null) return;
    kakao.value.maps.load(createLoadView);
  } catch (err) {
    console.error(err);
  }
});

watch(
  kakao,
  newValue => {
    newValue.maps.load(createLoadView);
  },
  { deep: true },
);
</script>

<style lang="scss" scoped></style>
