<template>
  <AppContent class="img">
    <template #header><div class="aptInfoHeader"></div></template>

    <div class="container-fluid card-header">
      <div class="row bg-light p-2">
        <!-- 아파트 북마크 선택 탭 taps -->
        <div class="col-auto d-flex align-items-center">
          <a class="nav-link fs-4" aria-current="page" href="#"
            >아파트 매매 정보</a
          >
        </div>
        <div class="col-auto d-flex align-items-center">
          <a class="nav-link fs-4" href="#">북마크 매매 정보</a>
        </div>

        <!-- 시도 select box -->
        <div class="col-auto d-flex align-items-center gap-2">
          <div class="dropdown-center">
            <button
              class="btn btn-outline-dark dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              style="min-width: 10vw"
            >
              {{ selectedSido === null ? '시도선택' : selectedSido }}
            </button>
            <ul class="dropdown-menu text-center m-0">
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
              style="min-width: 10vw"
            >
              {{ selectedGugun === null ? '구군 선택' : selectedGugun }}
            </button>
            <ul
              v-show="gugunList !== undefined"
              class="dropdown-menu text-center overflow-auto m-0"
              style="height: 30rem"
            >
              <li v-for="gugun in gugunList" :key="gugun">
                <a
                  class="dropdown-item"
                  href=""
                  @click.prevent="selectedGugun = gugun"
                  >{{ gugun }}</a
                >
              </li>
            </ul>
          </div>
        </div>

        <!-- 학교, 병원, 버정, 지하철, CCTV checkBoxes -->
        <div class="col-auto gap-2 d-flex align-items-center fs-6">
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label class="form-check-label" for="flexCheckDefault">
              학교
            </label>
          </div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label class="form-check-label" for="flexCheckDefault">
              병원
            </label>
          </div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label class="form-check-label" for="flexCheckDefault">
              버스
            </label>
          </div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label class="form-check-label" for="flexCheckDefault">
              지하철
            </label>
          </div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="flexCheckDefault"
            />
            <label class="form-check-label" for="flexCheckDefault">
              CCTV
            </label>
          </div>
        </div>

        <!-- 금액, 면적 필터 dropdown -->
        <div
          class="dropdown-center col-auto d-flex align-items-center justify-content-center"
        >
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
    <button @click="showSideBar = !showSideBar">사이드바 on/off</button>
    <!-- 맵 + 사이드 바 -->
    <div id="map" style="min-height: 80vh">
      <AppSideBar v-if="showSideBar"></AppSideBar>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppSideBar from '@/components/AppSideBar.vue';
import sidoGugunData from '@/assets/data/sido_gugun';
import { computed, onMounted, ref } from 'vue';
// 아파트 매매 정보 관련 사이드바 제어
const showSideBar = ref(false);

// 시도 구군 셀렉트 바
const selectedSido = ref(null);
const selectedGugun = ref(null);
const sidoList = [...Object.keys(sidoGugunData)];
const gugunList = computed(() => {
  return sidoGugunData[selectedSido.value];
});

// kakao map 생성.
const map = ref(null);

const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 5,
  };
  map.value = new kakao.maps.Map(container, options);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
    return;
  }
  const script = document.createElement('script');
  /* global kakao */
  script.onload = () => kakao.maps.load(initMap);
  script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
    import.meta.env.VITE_APP_KAKAOMAP_KEY
  }&libraries=services,clusterer`;
  document.head.appendChild(script);
});
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/apartment04jpg.jpg');
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
</style>
