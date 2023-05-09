<template>
  <AppContent class="img">
    <h1>Home Page</h1>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/apartment03jpg.jpg');
}
</style>
