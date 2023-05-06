<template>
  <nav class="navbar fixed-top navbar-expand-md bg-transparent navbar-dark">
    <div class="container">
      <RouterLink class="navbar-brand fs-3" to="/">HappyHouse</RouterLink>
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
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <button
              class="nav-link fs-5 fw-bold border-0 bg-transparent"
              @click="goAptInfoPage"
            >
              <i class="bi bi-buildings pe-2"></i>아파트 매매 정보
            </button>
          </li>

          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle fs-5 fw-bold"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="bi bi-grid-3x3-gap pe-1"></i>
              게시판
            </a>
            <ul class="dropdown-menu text-right">
              <li>
                <RouterLink class="dropdown-item fs-5" to="/notice">
                  <i class="bi bi-megaphone pe-2"></i>공지사항
                </RouterLink>
              </li>
              <hr class="dropdown-divider" />
              <li>
                <RouterLink class="dropdown-item fs-5" to="/qna">
                  <i class="bi bi-clipboard"></i> 질의응답
                </RouterLink>
              </li>
            </ul>
          </li>
          <!-- 로그인. 마이 페이지 -->
          <li v-if="accessToken === false" class="nav-item">
            <button
              class="nav-link fs-5 fw-bold border-0 bg-transparent"
              @click="goLoginPage"
            >
              <i class="bi bi-box-arrow-in-right pe-1"></i> 로그인
            </button>
          </li>
          <!-- 유저 마이페이지 -->
          <li v-else class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle fs-5 fw-bold"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i class="bi bi-person-circle"></i>
              {{ userPosition }}
            </a>
            <ul class="dropdown-menu text-right">
              <li>
                <RouterLink class="dropdown-item fs-5" to="/notice">
                  <i class="bi bi-info-square pe-2"></i>내 정보
                </RouterLink>
              </li>
              <hr class="dropdown-divider" />
              <li>
                <a
                  class="dropdown-item fs-5"
                  type="button"
                  @click.prevent="logout"
                >
                  <i class="bi bi-x-circle pe-1"></i> 로그아웃
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
import router from '@/router';
import { ref } from 'vue';

const userPosition = ref('관리자');
const accessToken = ref(true);
const goLoginPage = () => {
  router.push({ name: 'login' });
};
const goAptInfoPage = () => {
  router.push({ name: 'aptInfo' });
};

// logout
const logout = () => {
  accessToken.value = false;
  router.push({ name: 'home' });
};
</script>

<style scoped>
.nav-link {
  color: white;
}
</style>
