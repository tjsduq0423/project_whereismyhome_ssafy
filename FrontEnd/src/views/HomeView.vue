<template>
  <AppContent class="img">
    <template #header
      ><div class="cardOver">
        <figure>
          <blockquote class="blockquote">
            <p>Find your own house</p>
          </blockquote>
          <figcaption class="blockquote-footer">
            <cite title="Source Title">by HappyHouse</cite>
          </figcaption>
        </figure>
      </div>
    </template>

    <h1 class="chartHeader text-center">
      <i class="bi bi-fire" style="color: red"></i> Trend
      <i class="bi bi-fire" style="color: red"></i>
    </h1>
    <AppBarChart v-if="chartData" :chart-data="chartData"> </AppBarChart>
    <div
      v-else
      class="d-flex align-items-center justify-content-center"
      style="width: 1438px; height: 719px"
    >
      <div
        class="spinner-border"
        role="status"
        style="height: 10vh; width: 10vh; border-width: 0.5rem"
      >
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>
    <small class="footerText text-muted mt-3"
      >거래량, 북마크, 조회수 데이터를 기준으로 산출한 관심도 그래프 입니다.</small
    >
    <hr />
    <div class="d-flex flex-wrap gap-2 justify-content-center align-items-center">
      <button
        v-for="sido in Object.keys(sidoNameList)"
        :key="sido"
        type="button"
        :class="{ active: curSidoName === sidoNameList[sido] }"
        class="btn btn-outline-secondary px-5"
        @click="curSidoName = sidoNameList[sido]"
      >
        {{ sido }}
      </button>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/layouts/AppContent.vue';
import AppBarChart from '@/components/chart/AppBarChart.vue';

import { useHomeChartStore } from '@/stores/homeChart';
import { storeToRefs } from 'pinia';
import { watch } from 'vue';
import { tryOnMounted } from '@vueuse/core';

const homeChartStore = useHomeChartStore();
const { curSidoName, chartData, dataList } = storeToRefs(homeChartStore);
const { setChartData } = homeChartStore;
watch(curSidoName, async v => {
  if (dataList.value.find(item => item.name === v)) return;
  try {
    await setChartData(v);
  } catch (err) {
    console.error(err);
  }
});
tryOnMounted(() => {
  curSidoName.value = '서울특별시';
});
const sidoNameList = {
  서울: '서울특별시',
  부산: '부산광역시',
  대구: '대구광역시',
  인천: '인천광역시',
  광주: '광주광역시',
  대전: '대전광역시',
  울산: '울산광역시',
  세종: '세종특별자치시',
  경기: '경기도',
  강원: '강원도',
  충북: '충청북도',
  충남: '충청남도',
  전북: '전라북도',
  전남: '전라남도',
  경북: '경상북도',
  경남: '경상남도',
  제주: '제주특별자치도',
};
</script>

<style lang="scss" scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('/img/apartment03jpg.jpg');
  .cardOver {
    margin-top: 25vh;
    margin-bottom: 5vh;
    text-align: center;
    color: white;
    p {
      font-size: 3.3rem;
    }
    figcaption {
      color: rgba(189, 188, 186, 0.765);
      font-size: 1.4rem;
    }
  }
}
.chartHeader {
  margin: 0px 0px 3vh 0px;
}
.footerText {
  text-align: end;
}
</style>
