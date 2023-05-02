<template>
  <div class="viewBox d-flex justify-content-center">
    <RouterView></RouterView>
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
</script>

<style scoped>
.viewBox {
  height: 100vh;
}
</style>
