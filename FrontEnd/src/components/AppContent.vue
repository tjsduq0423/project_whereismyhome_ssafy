<template>
  <div class="viewBox d-flex justify-content-center">
    <slot></slot>
  </div>
</template>

<script setup></script>

<style scoped>
.viewBox {
  background-position: center center;
  background-size: cover;
  background-repeat: no-repeat;
  position: relative;
  height: 100vh;
}
</style>
