<template>
  <AppContent class="img">
    <AppCardHeader>회원가입</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @click.prevent>
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
        <div class="mb-4">
          <label for="name" class="form-label">Name</label>
          <input
            type="text"
            class="form-control"
            id="name"
            placeholder="Enter your Name"
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
          class="d-grid col-12 btn btn-primary text-truncate mt-4"
        >
          회원가입
        </button>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppCardHeader from '@/components/AppCardHeader.vue';
import AppContent from '@/components/AppContent.vue';
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/aprtments01.jpg');
}
</style>
