<template>
  <nav class="navbar navbar-expand-md navbar-dark bg-transparent">
    <div class="container">
      <RouterLink class="navbar-brand fs-3" to="/">
        <i class="bi bi-house-heart-fill"></i>
        HappyHouse</RouterLink
      >
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <button
              class="nav-link fs-5 fw-bold border-0 bg-transparent"
              @click="router.push({ name: 'AptInfo' })"
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
            <ul class="dropdown-menu text-right m-0">
              <li>
                <a
                  class="dropdown-item fs-5"
                  href=""
                  @click.prevent="router.push({ name: 'Notice' })"
                >
                  <i class="bi bi-megaphone"></i> 공지사항
                </a>
              </li>
              <hr class="dropdown-divider" />
              <li>
                <a
                  class="dropdown-item fs-5"
                  href=""
                  @click.prevent="router.push({ name: 'QnA' })"
                >
                  <i class="bi bi-clipboard"></i> 질의응답
                </a>
              </li>
            </ul>
          </li>
          <!-- 로그인. 마이 페이지 -->
          <li v-if="userInfo === null" class="nav-item">
            <button
              class="nav-link fs-5 fw-bold border-0 bg-transparent"
              @click="router.push({ name: 'Login' })"
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
              {{ userInfo.name }}
            </a>
            <ul class="dropdown-menu text-right m-0">
              <template v-if="userInfo.name !== '관리자'">
                <li>
                  <a
                    class="dropdown-item fs-5 me"
                    @click.prevent="router.push({ name: 'MyPage' })"
                  >
                    <i class="bi bi-info-square"></i> Profile
                  </a>
                </li>
                <hr class="dropdown-divider" />
              </template>
              <li>
                <a
                  class="dropdown-item fs-5"
                  type="button"
                  @click.prevent="logout"
                >
                  <i class="bi bi-x-circle"></i> Logout
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
import { useRouter } from 'vue-router';
import { useAuthStore } from '../stores/auth';
import { storeToRefs } from 'pinia';
import { useAlert } from '@/composables/alert';

const { vAlert, vSuccess } = useAlert();
const router = useRouter();

const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);
// logout
const logout = () => {
  try {
    authStore.invalidateMember();
    vSuccess('로그아웃 되었습니다.');
    router.push({ name: 'Home' });
  } catch (err) {
    vAlert('로그아웃 실패?!..');
    console.error(err);
  }
};
</script>

<style scoped>
.navbar {
  position: absolute;
  z-index: 3;
  min-width: 100%;
}
.nav-link {
  color: white;
}
</style>
