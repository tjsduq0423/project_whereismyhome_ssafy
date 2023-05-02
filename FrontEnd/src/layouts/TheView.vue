<template>
  <div class="viewBox d-flex justify-content-center mt-5">
    <RouterView></RouterView>
  </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
</script>

<style scoped>
.viewBox {
  position: relative;
  height: 100vh;
}
</style>
