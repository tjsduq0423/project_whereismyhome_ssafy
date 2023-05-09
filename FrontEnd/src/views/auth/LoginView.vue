<template>
  <AppContent class="img">
    <AppCardHeader>로그인</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @submit.prevent>
        <div class="mb-4">
          <label for="id" class="form-label">ID</label>
          <input
            type="text"
            class="form-control"
            id="id"
            placeholder="Enter your ID"
          />
        </div>
        <div class="mb-4">
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
          class="d-grid col-12 btn btn-primary text-truncate mb-4"
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
import AppCardHeader from '@/components/AppCardHeader.vue';
import AppContent from '@/components/AppContent.vue';
import router from '@/router';

const goFindPW = () => {
  router.push('/FindPassword');
};
const goSignup = () => {
  router.push('/SignUp');
};
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/aprtments01.jpg');
}
</style>
