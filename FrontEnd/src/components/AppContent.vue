<template>
  <div class="viewBox d-flex justify-content-center align-items-center">
    <div class="card shadow-lg py-5 px-5">
      <slot></slot>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.viewBox {
  background-position: center center;
  background-size: cover;
  background-repeat: no-repeat;
  height: 100vh;
  position: relative;
}
.card {
}
</style>
