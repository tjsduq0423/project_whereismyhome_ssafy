<template>
  <!-- 로드뷰 + 헤더 + 등등 들어갈 자리 -->
  <div class="sidebar">
    <div class="aptInfo" v-if="aptInfo">
      <KaKaoLoadview v-if="showSideBar"></KaKaoLoadview>
      <div class="container-fluid p-3">
        <div class="fs-3">{{ aptInfo.aptName }}</div>
        <div style="color: rgba(172, 172, 172, 0.976)">{{ aptInfo.buildYear }}년 건설</div>
        <div class="d-flex align-items-center justify-content-start">
          <i class="bi bi-geo-alt-fill"></i>
          <div>
            {{ `${aptInfo.sidoName} ${aptInfo.gugunName}` }}
            {{ aptInfo.dongName ? `${aptInfo.dongName} ${aptInfo.jibun} 번지` : aptInfo.roadName }}
          </div>
        </div>

        <div class="bookmark d-grid">
          <button
            :class="{ active: curBookmark }"
            class="btn btn-outline-danger px-4 mt-1"
            type="button"
            @click="clickBookmark"
          >
            <i class="bi bi-heart"></i>
          </button>
        </div>
      </div>
    </div>
    <div class="accordion accordion-flush" id="accordionFlushExample">
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingOne">
          <button
            class="accordion-button collapsed fs-5"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#flush-collapseOne"
            aria-expanded="false"
            aria-controls="flush-collapseOne"
          >
            거래 내역
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
              <h4>매매 가격 : {{ `${priceRange[0] / 10000} 억 ~ ${priceRange[1] / 10000} 억` }}</h4>
            </div>
            <div class="row p-0 m-0">
              <AppLineChart :chart-data="chartData"></AppLineChart>
            </div>
            <div class="row">
              <table class="table text-center text-truncate">
                <thead>
                  <tr>
                    <th scope="col-auto">거래일</th>
                    <th scope="col-auto">거래금액</th>
                    <th scope="col-auto">평수</th>
                    <th scope="col-auto">층수</th>
                  </tr>
                </thead>
                <tbody v-if="_tableItems" class="table-group-divider">
                  <tr v-for="(item, idx) in _tableItems" :key="idx">
                    <th>{{ item.date }}</th>
                    <td>{{ item.dealAmount / 10000 }} 억</td>
                    <td>{{ (item.area / 3.3058).toFixed(1) }}</td>
                    <td>{{ item.floor }}</td>
                  </tr>
                </tbody>
              </table>
              <AppPaginationBar
                :current-page="curPage"
                :total-content-count="tableItems.length"
                :show-pagination-btn-count="7"
                @page="page => (curPage = page)"
              ></AppPaginationBar>
            </div>
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingTwo">
          <button
            class="accordion-button collapsed fs-5"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#flush-collapseTwo"
            aria-expanded="false"
            aria-controls="flush-collapseTwo"
          >
            아파트 인기 순위
          </button>
        </h2>
        <div
          id="flush-collapseTwo"
          class="accordion-collapse collapse"
          aria-labelledby="flush-headingTwo"
          data-bs-parent="#accordionFlushExample"
        >
          <div class="accordion-body pe-4">
            <div class="row m-0 p-0">
              <div class="col-6 p-0 text-center d-flex"><i class="bi bi-check2"></i> 조회수</div>
              <div class="col-6 text-center d-flex"><i class="bi bi-heart-fill"></i> 북마크</div>
            </div>
            <div class="row">
              <div class="col-6 text-center d-flex">{{ aptInfo.gugunName }}상위 몇퍼</div>
              <div class="col-6 text-center d-flex">
                {{ aptInfo.gugunName }} 상위 몇퍼 {{ aptRankInfo }} ?? 종합 55등이네 그냥 이대로
                써도 될지도?
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingThree">
          <button
            class="accordion-button collapsed fs-5"
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
            <ul class="nav nav-tabs">
              <li class="nav-item">
                <a :class="{ active: true }" class="nav-link" href="#" @click.prevent="">학교</a>
              </li>
              <li class="nav-item">
                <a :class="{ active: true }" class="nav-link" href="#" @click.prevent="">학교</a>
              </li>
              <li class="nav-item">
                <a :class="{ active: true }" class="nav-link" href="#" @click.prevent="">학교</a>
              </li>
              <li class="nav-item">
                <a :class="{ active: true }" class="nav-link" href="#" @click.prevent="">학교</a>
              </li>
            </ul>
            <div class="content">test</div>
          </div>
        </div>
      </div>
      <div class="accordion-item">
        <h2 class="accordion-header" id="flush-headingFour">
          <button
            class="accordion-button collapsed fs-5"
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
          <div class="accordion-body p-0">
            <div v-html="sectionHeadlineContent" style="text-decoration: none"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import AppPaginationBar from './AppPaginationBar.vue';
import AppLineChart from '../chart/AppLineChart.vue';
import KaKaoLoadview from '../kakao/KaKaoLoadview.vue';
import { ref, watch } from 'vue';

// 로드 뷰 렌더링 제어
import { useSideBarStore } from '@/stores/sideBar';
import { storeToRefs } from 'pinia';
const sideBarStore = useSideBarStore();
const { showSideBar, aptInfo, aptRankInfo, priceRange, chartData, tableItems } =
  storeToRefs(sideBarStore);
// 북마크
import { useBookmarkStore } from '@/stores/bookmark';
import { useDebounceFn } from '@vueuse/core';
import axios from 'axios';
const bookmarkStore = useBookmarkStore();
const { bookmarkList } = storeToRefs(bookmarkStore);

// 현재 고른 apt 기준 북마크 여부
const curBookmark = ref(null);
watch(bookmarkList, () => {
  bookmarkList.value.forEach(v => {
    if (v.aptCode === aptInfo.aptCode) {
      curBookmark.value = true;
      return;
    }
  });
  curBookmark.value = false;
});

const clickBookmark = () => {
  curBookmark.value = !curBookmark.value;
  useDebounceFn(() => {
    console.log('test');
    // api call
    // 알람 등록
  }, 500);
};

// 페이지네이션 변수
const curPage = ref(1);
const _tableItems = ref(null);
watch([tableItems, curPage], ([items, p]) => {
  _tableItems.value = [...items.slice((p - 1) * 10, p * 10)];
});

// new tabs
const sectionHeadlineContent = ref('');
watch(aptInfo, async v => {
  const url = `https://land.naver.com/news/search.naver?keyword=${v.gugunName} 부동산`;

  try {
    const response = await axios.get(url);
    const parser = new DOMParser();
    const htmlDoc = parser.parseFromString(response.data, 'text/html');
    const sectionHeadline = htmlDoc.querySelector('.headline_list');

    if (sectionHeadline) {
      sectionHeadlineContent.value = sectionHeadline.innerHTML;
    }
  } catch (err) {
    console.error('Error occurred while fetching news:', err);
  }
});
watch(aptInfo, async v => {
  const url = `https://land.naver.com/news/search.naver?keyword=${v.gugunName} 부동산`;

  try {
    const response = await axios.get(url);
    const parser = new DOMParser();
    const htmlDoc = parser.parseFromString(response.data, 'text/html');
    const sectionHeadline = htmlDoc.querySelector('.headline_list');

    if (sectionHeadline) {
      sectionHeadlineContent.value = sectionHeadline.innerHTML;
    }
  } catch (err) {
    console.error('Error occurred while fetching news:', err);
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
