<template>
  <!-- 로드뷰 + 헤더 + 등등 들어갈 자리 -->
  <div class="sidebar">
    <div class="aptInfo">
      <div ref="roadViewContainer" style="width: 100%; height: 30vh"></div>
      <div class="container-fluid p-3">
        <div class="fs-3">삼성동중앙하이츠 빌리지</div>
        <div style="color: rgba(172, 172, 172, 0.976)">2004년 건설</div>
        <div class="d-flex align-items-center justify-content-start">
          <i class="bi bi-geo-alt-fill"></i>
          <div>서울 특별시 강남구 14-1 번지</div>
        </div>
      </div>
    </div>
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingOne">
          <button
            class="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#flush-collapseOne"
            aria-expanded="false"
            aria-controls="flush-collapseOne"
          >
            매매정보
          </button>
        </h2>
        <div
          id="flush-collapseOne"
          class="accordion-collapse collapse"
          aria-labelledby="flush-headingOne"
          data-bs-parent="#accordionFlushExample"
        >
          <div class="container-fluid p-3">
            <div class="row">
              <div>매매 정보</div>
            </div>
            <div class="row">
              <AppLineChart :chart-data="chartData" :chart-options="chartOptions"></AppLineChart>
            </div>
            <div class="row">
              <table class="table text-center text-truncate">
                <thead>
                  <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성시간</th>
                  </tr>
                </thead>
                <!-- <tbody class="table-group-divider">
                  <tr v-for="item in _items" :key="item.id">
                    <th>{{ item.id }}</th>
                    <td>
                      <a
                        href=""
                        class="text-decoration-none text-success"
                        @click.prevent="goDetailPage(item.id)"
                        >{{ item.title }}</a
                      >
                    </td>
                    <td>{{ item.author }}</td>
                    <td>{{ item.createTime }}</td>
                  </tr>
                </tbody> -->
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingTwo">
          <button
            class="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#flush-collapseTwo"
            aria-expanded="false"
            aria-controls="flush-collapseTwo"
          >
            단지 인기 순위
          </button>
        </h2>
        <div
          id="flush-collapseTwo"
          class="accordion-collapse collapse"
          aria-labelledby="flush-headingTwo"
          data-bs-parent="#accordionFlushExample"
        >
          <div class="accordion-body">단지 조회수, 북마크, 거래량 기반 인기도 in 지역구</div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingThree">
          <button
            class="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#flush-collapseThree"
            aria-expanded="false"
            aria-controls="flush-collapseThree"
          >
            주변 인프라
          </button>
        </h2>
        <div
          id="flush-collapseThree"
          class="accordion-collapse collapse"
          aria-labelledby="flush-headingThree"
          data-bs-parent="#accordionFlushExample"
        >
          <div class="accordion-body">
            학교 , 병원 ,버스 , 지하철 등등 편의 시설 현 아파트 위치 기준 반경 500 m 내 tab으로 가까운 순으로 표시
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingFour">
          <button
            class="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#flush-collapseFour"
            aria-expanded="false"
            aria-controls="flush-collapseFour"
          >
            관련 뉴스
          </button>
        </h2>
        <div
          id="flush-collapseFour"
          class="accordion-collapse collapse"
          aria-labelledby="flush-headingFour"
          data-bs-parent="#accordionFlushExample"
        >
          <div class="accordion-body">해당 아파트를 검색했을 때 나오는 화면 insert</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import AppLineChart from './AppLineChart.vue';
import { onMounted, ref } from 'vue';

import { data, options } from '@/assets/data/homeChart';
// 차트 데이터.
const chartData = ref({ ...data });
const chartOptions = ref({ ...options });

//kakao 로드 뷰
const roadViewContainer = ref(null);
const createLoadView = (lat = '33.450701', lng = '126.570667') => {
  /* global kakao */
  const roadview = new kakao.maps.Roadview(roadViewContainer.value);
  const roadviewClient = new kakao.maps.RoadviewClient();
  const position = new kakao.maps.LatLng(lat, lng);
  // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
  roadviewClient.getNearestPanoId(position, 50, function (panoId) {
    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
  });
};
onMounted(() => {
  try {
    createLoadView();
  } catch (err) {
    console.error(err);
  }
});
</script>

<style lang="scss" scoped>
.sidebar {
  position: absolute;
  z-index: 3;
  width: 365px;
  height: 100%;
  background-color: white;
  overflow: auto;
}
.sidebar::-webkit-scrollbar {
  width: 8px; /* 스크롤바의 너비 */
}
.sidebar::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #d1d1d1; /* 스크롤바의 색상 */
  border-radius: 10px;
}
</style>
