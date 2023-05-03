<template>
  <AppContent class="img">
    <div class="text-warning">
      <h1>Oops...</h1>
      <h1>404 Error</h1>
      <h1>Page Not Found</h1>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
</script>

<style scoped>
.img {
  background-image: url('@/img/notFound.jpg');
}
</style>
