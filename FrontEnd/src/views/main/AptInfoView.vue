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

        <!-- 시도 select box -->
        <div class="col-auto d-flex align-items-center gap-2">
          <div class="dropdown-center">
            <button
              class="btn btn-outline-dark dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              style="min-width: 8vw"
            >
              {{ selectedSido === null ? '시도선택' : selectedSido }}
            </button>
            <ul class="dropdown-menu text-center m-0" style="min-width: 8vw">
              <li v-for="sido in sidoList" :key="sido">
                <a
                  class="dropdown-item"
                  href=""
                  @click.prevent="
                    () => {
                      selectedSido = sido;
                      selectedGugun = null;
                    }
                  "
                  >{{ sido }}</a
                >
              </li>
            </ul>
          </div>

          <!-- 구군 select box -->
          <div class="dropdown-center">
            <button
              class="btn btn-outline-dark dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              style="min-width: 8vw"
            >
              {{ selectedGugun === null ? '구군 선택' : selectedGugun }}
            </button>
            <ul
              v-show="gugunList !== undefined"
              class="dropdown-menu text-center overflow-auto m-0"
              style="max-height: 30rem; min-width: 8vw"
            >
              <li v-for="gugun in gugunList" :key="gugun">
                <a class="dropdown-item" href="" @click.prevent="gugunSelect(gugun)">{{ gugun }}</a>
              </li>
            </ul>
          </div>
        </div>

        <!-- 학교, 병원, 버정, 지하철, CCTV checkBoxes -->
        <div class="col-auto d-flex flex-column align-items-center fs-6">
          <div class="row">
            <div class="col-auto">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  @click="vLoading"
                  v-model="isShowSchool"
                  id="flexCheckDefault"
                />
                <label class="form-check-label" for="flexCheckDefault"> 학교 </label>
              </div>
            </div>
            <div class="col-auto">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  @click="vLoading"
                  v-model="isShowHospital"
                  id="flexCheckDefault"
                />
                <label class="form-check-label" for="flexCheckDefault"> 병원 </label>
              </div>
            </div>
            <div class="col-auto">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  @click="vLoading"
                  v-model="isShowBus"
                  id="flexCheckDefault"
                />
                <label class="form-check-label" for="flexCheckDefault"> 버스 </label>
              </div>
            </div>
            <div class="col-auto">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  @click="vLoading"
                  v-model="isShowSubway"
                  id="flexCheckDefault"
                />
                <label class="form-check-label" for="flexCheckDefault"> 지하철 </label>
              </div>
            </div>
            <div class="col-auto">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  @click="vLoading"
                  v-model="isShowCCTV"
                  id="flexCheckDefault"
                />
                <label class="form-check-label" for="flexCheckDefault"> CCTV </label>
              </div>
            </div>
          </div>
          <div class="row">
            <small id="codeHelp" class="form-text text-muted m-0 p-0"
              >지도 중심 기준 편의시설 정보 마커 on/off , 표시 기준 : 지도 zoom level 3이하</small
            >
          </div>
        </div>

        <!-- 금액, 면적 필터 dropdown -->
        <div class="dropdown-center col-auto d-flex align-items-center justify-content-center">
          <button
            class="btn btn-outline-dark dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            data-bs-auto-close="outside"
            aria-expanded="false"
          >
            <i class="bi bi-filter"></i>
            필터
          </button>
          <div class="dropdown-menu p-5" style="min-width: 30vw">
            <div class="row m-0 p-0">
              <h4>평균 거래가</h4>
              <label for="customRange1" class="form-label">{{
                priceRange <= 20 ? `~${priceRange}억` : '무제한'
              }}</label>
              <input
                type="range"
                class="form-range"
                min="0"
                max="21"
                step="1"
                id="customRange1"
                v-model="priceRange"
              />
            </div>
            <hr />
            <div class="row m-0 p-0">
              <h4>준공년도</h4>
              <div class="text-success"></div>
              <div class="row mb-2">
                <div class="col-4">
                  <button
                    :class="{ active: buildYearRange === 100 }"
                    class="btn btn-outline-secondary"
                    @click="buildYearRange = 100"
                  >
                    전체
                  </button>
                </div>
                <div class="col-4">
                  <button
                    :class="{ active: buildYearRange === 5 }"
                    class="btn btn-outline-secondary"
                    @click="buildYearRange = 5"
                  >
                    5년 이내
                  </button>
                </div>
                <div class="col-4">
                  <button
                    :class="{ active: buildYearRange === 10 }"
                    class="btn btn-outline-secondary"
                    @click="buildYearRange = 10"
                  >
                    10년 이내
                  </button>
                </div>
              </div>
              <div class="row">
                <div class="col-4">
                  <button
                    :class="{ active: buildYearRange === 15 }"
                    class="btn btn-outline-secondary"
                    @click="buildYearRange = 15"
                  >
                    15년 이내
                  </button>
                </div>
                <div class="col-4">
                  <button
                    :class="{ active: buildYearRange === 20 }"
                    class="btn btn-outline-secondary"
                    @click="buildYearRange = 20"
                  >
                    20년 이내
                  </button>
                </div>
                <div class="col-4">
                  <button
                    :class="{ active: buildYearRange === 25 }"
                    class="btn btn-outline-secondary"
                    @click="buildYearRange = 25"
                  >
                    25년 이내
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 지역, 아파트, 지하철 검색 filter(input box) -->
        <div class="col d-flex align-items-center">
          <form class="position-relative">
            <i class="searchIcon bi bi-search"></i>
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
          </form>
        </div>
      </div>
    </div>
    <!-- 맵 + 사이드 바 -->
    <KaKaoMap v-if="delayMap">
      <transition
        appear
        mode="out-in"
        enter-active-class="animate__animated animate__slideInLeft"
        leave-active-class="animate__animated animate__slideOutLeft"
      >
        <AptInfoSideBar v-show="isShow"></AptInfoSideBar>
      </transition>
    </KaKaoMap>

    <div v-else class="d-flex align-items-center justify-content-center" style="min-height: 80vh">
      <div
        class="spinner-border"
        role="status"
        style="height: 10vh; width: 10vh; border-width: 0.5rem"
      >
        <span class="visually-hidden">Loading...</span>
      </div>
    </div>
    <AppLoading style="z-index: 100"></AppLoading>
  </AppContent>
</template>

<script setup>
import AppLoading from '@/components/features/AppLoading.vue';
import KaKaoMap from '@/components/kakao/KaKaoMap.vue';
import AptInfoSideBar from '@/components/features/AptInfoSideBar.vue';
import AppContent from '@/components/layouts/AppContent.vue';
import sidoGugunData from '@/data/sido_gugun';
import { computed, ref } from 'vue';
import { useLoading } from '@/composables/loading';
//page delay
const delayMap = ref(false);
setTimeout(() => {
  delayMap.value = true;
}, 1000);
// Data delay
const { vLoading } = useLoading();

// 시도 구군 셀렉트 바
const selectedSido = ref(null);
const selectedGugun = ref(null);

const sidoList = [...Object.keys(sidoGugunData)];
const gugunList = computed(() => {
  let data = sidoGugunData[selectedSido.value];
  if (data === undefined || data === null) return [];
  data = [...data];
  data.sort();
  return data;
});
const gugunSelect = gugun => {
  selectedGugun.value = gugun;
  sidoGugun.value = [selectedSido.value, gugun];
};
import { useSideBarStore } from '@/stores/sideBar';
const sideBarStore = useSideBarStore();
const { isShow } = storeToRefs(sideBarStore);
import { useMarkersStore } from '@/stores/markers';
import { storeToRefs } from 'pinia';
const markerStrore = useMarkersStore();
const {
  buildYearRange,
  priceRange,
  sidoGugun,
  isShowCCTV,
  isShowSchool,
  isShowHospital,
  isShowSubway,
  isShowBus,
} = storeToRefs(markerStrore);
</script>

<style lang="scss" scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('/img/apartment00.jpg');
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

.dropdown-menu {
  &::-webkit-scrollbar {
    width: 8px; /* 스크롤바의 너비 */
  }
  &::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: #d1d1d1; /* 스크롤바의 색상 */
    border-radius: 10px;
  }
  &::-webkit-scrollbar-track {
    background-color: #ececec;
  }
}
div > a {
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
.btn {
  width: 100%;
}
</style>
