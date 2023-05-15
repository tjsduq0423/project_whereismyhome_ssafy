<template>
  <AppContent class="img">
    <AppCardHeader> 비밀번호 찾기</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @submit.prevent>
        <div class="mb-4">
          <label for="id" class="form-label">ID</label>
          <input
            type="text"
            class="form-control"
            id="id"
            v-model="id"
            placeholder="Enter your ID"
          />
        </div>
        <div class="mb-4">
          <label for="email" class="form-label">Email</label>
          <input
            type="email"
            class="form-control"
            id="email"
            v-model="email"
            placeholder="Ex - example@naver.com"
          />
        </div>
        <button
          type="button"
          class="d-grid col-12 btn btn-success text-truncate"
        >
          비밀번호 찾기
        </button>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppCardHeader from '@/components/AppCardHeader.vue';
import { ref } from 'vue';
// import { useAlert } from '@/composables/alert';
// const { vAlert, vSuccess } = useAlert();
const id = ref('');
const email = ref('');
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/aprtments01.jpg');
}
</style>
