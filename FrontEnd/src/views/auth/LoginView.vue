<template>
  <AppContent class="img">
    <AppHeader>로그인</AppHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @submit.prevent>
        <div class="mb-3">
          <label for="id" class="form-label">ID</label>
          <input
            type="text"
            class="form-control"
            id="id"
            placeholder="Enter your ID"
          />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input
            type="password"
            class="form-control"
            id="Password"
            placeholder="Enter your Password"
          />
        </div>
        <button
          type="button"
          class="d-grid col-12 btn btn-primary text-truncate"
        >
          로그인
        </button>
        <div class="d-flex justify-content-between mt-3">
          <button
            type="button"
            class="btn btn-outline-danger text-truncate"
            @click="goFindPW"
          >
            비밀번호 찾기
          </button>
          <button
            type="button"
            class="btn btn-outline-primary text-truncate"
            @click="goSignup"
          >
            회원 가입
          </button>
        </div>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppHeader from '@/components/AppHeader.vue';
import AppContent from '@/components/AppContent.vue';
import router from '@/router';

const goFindPW = () => {
  router.push('/findPassword');
};
const goSignup = () => {
  router.push('/signUp');
};
</script>

<style scoped>
.img {
  background-image: url('@/img/aprtments01.jpg');
}
</style>
