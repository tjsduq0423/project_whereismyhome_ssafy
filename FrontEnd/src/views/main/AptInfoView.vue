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
                <a class="dropdown-item" href="" @click.prevent="selectedGugun = gugun">{{ gugun }}</a>
              </li>
            </ul>
          </div>
        </div>

        <!-- 학교, 병원, 버정, 지하철, CCTV checkBoxes -->
        <div class="col-auto gap-2 d-flex align-items-center fs-6">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 학교 </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 병원 </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 버스 </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> 지하철 </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" />
            <label class="form-check-label" for="flexCheckDefault"> CCTV </label>
          </div>
        </div>

        <!-- 금액, 면적 필터 dropdown -->
        <div class="dropdown-center col-auto d-flex align-items-center justify-content-center">
          <button
            class="btn btn-outline-dark dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <i class="bi bi-filter"></i>
            필터
          </button>
          <ul class="dropdown-menu text-center m-0">
            아직 아무것도
          </ul>
        </div>

        <!-- 지역, 아파트, 지하철 검색 filter(input box) -->
        <div class="col d-flex align-items-center">
          <form class="position-relative">
            <i class="searchIcon bi bi-search"></i>
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
          </form>
        </div>
      </div>
    </div>
    <button @click="showSideBar = !showSideBar">사이드바 on/off</button>
    <!-- 맵 + 사이드 바 -->
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
import KaKaoMap from '@/components/KaKaoMap.vue';
import AppSideBar from '@/components/AppSideBar.vue';
import AppContent from '@/components/AppContent.vue';
import sidoGugunData from '@/assets/data/sido_gugun';
import { computed, ref } from 'vue';

const showSideBar = ref(false);
// 시도 구군 셀렉트 바
const selectedSido = ref(null);
const selectedGugun = ref(null);
const sidoList = [...Object.keys(sidoGugunData)];
const gugunList = computed(() => {
  return sidoGugunData[selectedSido.value];
});
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
</style>
