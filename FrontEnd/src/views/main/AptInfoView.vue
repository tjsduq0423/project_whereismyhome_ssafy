<template>
  <AppContent class="img">
    <nav class="card-header navbar navbar-expand-lg bg-light">
      <div class="container-fluid">
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 gap-3 fs-5">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#"
                >아파트 매매 정보</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">북마크 매매 정보</a>
            </li>

            <!-- 시도 select -->
            <li class="nav-item dropdown">
              <button
                class="btn btn-outline-dark dropdown-toggle"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style="min-width: 10rem"
              >
                {{ selectedSido === null ? '시도선택' : selectedSido }}
              </button>
              <ul class="dropdown-menu text-center" style="min-width: 10rem">
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
            </li>
            <!-- 구군 셀렉트 -->
            <li class="nav-item dropdown">
              <button
                class="btn btn-outline-dark dropdown-toggle"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                style="width: 10rem"
              >
                {{ selectedGugun === null ? '구군 선택' : selectedGugun }}
              </button>
              <ul
                v-show="gugunList !== undefined"
                class="dropdown-menu text-center overflow-auto"
                style="max-height: 36rem"
              >
                <li v-for="gugun in gugunList" :key="gugun">
                  <a
                    class="dropdown-item"
                    href=""
                    @click.prevent="selectedGugun = gugun"
                    style="width: 10rem"
                    >{{ gugun }}</a
                  >
                </li>
              </ul>
            </li>
            <!-- 학교, 병원, 버정, 지하철, CCTV checkBoxes -->
            <li class="nav-item gap-2" style="min-width: 20rem">
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
            </li>
            <!-- 금액, 면적 필터 dropdown -->
            <li class="nav-item">
              <button class="btn btn-outline-dark fs-5 px-4" type="button">
                <i class="bi bi-filter me-2"></i>
                필터
              </button>
            </li>
            <!-- 지역, 아파트, 지하철 검색 filter(input box) -->
            <li class="nav-item">
              <form class="d-flex" role="search">
                <input
                  class="form-control me-2"
                  type="search"
                  placeholder="Search"
                  aria-label="Search"
                />
                <button class="btn btn-outline-dark" type="button">
                  Search
                </button>
              </form>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div id="map" style="min-width: 500px; height: 400px"></div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import sidoGugunData from '@/assets/data/sido_gugun';
import { computed, onMounted, ref } from 'vue';

// 시도 구군 셀렉트 바
const selectedSido = ref(null);
const selectedGugun = ref(null);
const sidoList = [...Object.keys(sidoGugunData)];
const gugunList = computed(() => {
  return sidoGugunData[selectedSido.value];
});

// kakao map 제어
const map = ref(null);

const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
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
  }`;
  document.head.appendChild(script);
});
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/apartment04jpg.jpg');
}
a {
  color: black;
}
.navbar {
  overflow: hidden;
}
.nav-item {
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 10rem;
}
</style>
