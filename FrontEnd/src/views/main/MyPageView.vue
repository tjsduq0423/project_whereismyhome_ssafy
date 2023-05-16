<template>
  <AppContent class="img">
    <div class="container-fluid">
      <div class="row">
        <div class="col text-center">
          <h1 class="mb-5">
            {{ userInfo.name }}
          </h1>
          <h5>userInfo.email</h5>
        </div>
      </div>

      <div class="row tabs">
        <div class="col">
          <RouterLink :to="{ name: MyQnA }">My QnA</RouterLink>
        </div>
        <div class="col">
          <RouterLink :to="{ name: MyBookMark }">My BookMark</RouterLink>
        </div>
      </div>

      <div class="row">
        <hr />
      </div>
      <div class="row body">
        <div class="col">content</div>
      </div>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import { useAuthStore } from '@/stores/auth';
import { storeToRefs } from 'pinia';

const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/apartment04jpg.jpg');
}

a:hover,
a.router-link-active {
  border-bottom: 2px solid rgba(92, 180, 218, 0.998);
}
</style>
