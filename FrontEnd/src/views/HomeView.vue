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
    <h1 class="chartHeader text-center">Trend</h1>
    <AppBarChart :chart-data="chartData" :chart-options="chartOptions"> </AppBarChart>
    <h6 class="footerText">거래량이 어쩌구 조회수가 저쩌구 해서 나온 트렌드가 그렇다구~</h6>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppBarChart from '@/components/AppBarChart.vue';
import { data, options } from '@/assets/data/homeChart';

const chartData = { ...data };
const chartOptions = { ...options };
</script>

<style lang="scss" scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)), url('@/assets/img/apartment03jpg.jpg');
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
