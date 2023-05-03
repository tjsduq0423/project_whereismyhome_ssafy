<template>
  <AppContent class="img">
    <div class="mt-5 text-center text-warning">
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
