<template>
  <AppContent class="img">
    <AppHeader> 비밀번호 찾기</AppHeader>
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
          <label for="email" class="form-label">Email</label>
          <input
            type="email"
            class="form-control"
            id="email"
            placeholder="Ex - example@naver.com"
          />
        </div>
        <button
          type="button"
          class="d-grid col-12 btn btn-primary text-truncate"
        >
          비밀번호 찾기
        </button>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppHeader from '@/components/AppHeader.vue';
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/aprtments01.jpg');
}
</style>
