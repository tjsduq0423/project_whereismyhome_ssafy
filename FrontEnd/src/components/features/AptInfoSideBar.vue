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
              <h4>
                매매 가격 :
                {{
                  `${Math.floor(priceRange[0] / 10000)}억 ${
                    priceRange[0] % 10000
                  }만원 ~ ${Math.floor(priceRange[1] / 10000)}억 ${priceRange[1] % 10000}만원`
                }}
              </h4>
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
                    <td>
                      {{
                        `${Math.floor(item.dealAmount / 10000)}억 ${item.dealAmount % 10000}만원`
                      }}
                      억
                    </td>
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
            <div class="row">
              <div class="col-6 text-center d-flex flex-column fs-5">
                <div><i class="bi bi-check2 me-2"></i>조회수</div>
                <div>{{ aptRankInfo.viewCount }} 회</div>
              </div>
              <div class="col-6 text-center d-flex flex-column fs-5">
                <div><i class="bi bi-heart-fill me-2"></i> 북마크</div>
                <div>{{ aptRankInfo.mark }} 회</div>
              </div>
            </div>
            <div class="row">
              <div class="col-12 text-center d-flex justify-content-center">
                <p class="fs-4 text-danger m-0">
                  종합순위 in {{ aptInfo.gugunName }} - {{ aptRankInfo.rank }}위
                </p>
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
              <li
                class="nav-item"
                v-for="tabName in Object.keys(aptToAmenDistanceInfo)"
                :key="tabName"
              >
                <a
                  style="color: rgb(107, 107, 107)"
                  :class="{ active: tabName === curTab }"
                  class="nav-link"
                  href="#"
                  @click.prevent="curTab = tabName"
                  >{{ tabName }}</a
                >
              </li>
            </ul>
            <template v-for="tabName in Object.keys(aptToAmenDistanceInfo)" :key="tabName">
              <div class="content mt-2" v-if="curTab === tabName">
                <div
                  v-for="item in aptToAmenDistanceInfo[tabName]"
                  :key="item"
                  class="d-flex flex-column justify-content-center align-items-start mb-2 border border-1 p-2"
                >
                  <div class="fs-5">
                    {{ item.name }}
                  </div>
                  <div>
                    <i class="bi bi-arrow-right-short fs-5"></i>
                    {{ item.dist }}
                  </div>
                </div>
              </div>
            </template>
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
          <div class="accordion-body p-2">
            <div class="m-0" v-for="(data, idx) in newsDataList" :key="idx">
              <div class="title fs-5 text-primary fw-bold" v-html="data.title"></div>
              <hr class="m-0" />
              <div class="content text-secondary" v-html="data.content"></div>
              <hr class="mb-1" />
            </div>
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
const {
  aptToAmenDistanceInfo,
  showSideBar,
  aptInfo,
  aptRankInfo,
  priceRange,
  chartData,
  tableItems,
} = storeToRefs(sideBarStore);

// 북마크
import { useBookmarkStore } from '@/stores/bookmark';
import { tryOnBeforeUnmount, useDebounceFn } from '@vueuse/core';
const bookmarkStore = useBookmarkStore();
const { bookmarkList } = storeToRefs(bookmarkStore);
const { dBookmark, aBookmark } = bookmarkStore;

// 현재 고른 apt 기준 북마크 여부
const curBookmark = ref(false);
watch(bookmarkList, newBookmarkList => {
  if (newBookmarkList?.find(bookmark => bookmark.aptCode === aptInfo.value.aptCode)) {
    curBookmark.value = true;
  } else {
    curBookmark.value = false;
  }
});

// 북마크 토글
import { useAuthStore } from '@/stores/auth';
import axios from 'axios';
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);
const clickBookmark = useDebounceFn(async () => {
  try {
    if (curBookmark.value) {
      await dBookmark(userInfo.value.id, aptInfo.value.aptCode);
    } else {
      await aBookmark(userInfo.value.id, aptInfo.value.aptCode);
    }
    curBookmark.value = !curBookmark.value;
  } catch (err) {
    console.error(err);
  }
}, 500);

// 페이지네이션 변수
const curPage = ref(1);
const _tableItems = ref(null);
watch([tableItems, curPage], ([items, p]) => {
  _tableItems.value = [...items.slice((p - 1) * 10, p * 10)];
});

// tab 제어
const curTab = ref('학교');
const newsDataList = ref([]);

// new tabs 파싱
watch(aptInfo, async v => {
  const url = `/news/search.naver?keyword=${v.gugunName} 부동산&field=1`;

  try {
    const response = await axios.get(url, {
      withCredentials: true,
    });
    const parser = new DOMParser();
    const htmlDoc = parser.parseFromString(response.data, 'text/html');
    const sectionHeadline = htmlDoc.querySelector('.headline_list');
    if (!sectionHeadline) return;
    console.log(sectionHeadline);
    const lis = sectionHeadline.getElementsByTagName('li');
    for (let i = 0; i < Math.min(lis.length, 10); i++) {
      let li = lis[i];
      let tags_a = li.getElementsByTagName('a');
      let title;
      if (tags_a?.length === 1) title = tags_a[0].innerHTML;
      else title = tags_a[1].innerHTML;
      let content = li.getElementsByTagName('dd')[0].innerHTML;
      newsDataList.value.push({
        title,
        content,
      });
    }
  } catch (err) {
    console.error('Error occurred while fetching news:', err);
  }
});
tryOnBeforeUnmount(() => {
  showSideBar.value = false;
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
.bi-check2::before,
.bi-heart-fill::before,
.bi-arrow-right-short::before {
  display: inline-block;
  vertical-align: middle;
}
</style>
