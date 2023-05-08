<template>
  <div class="viewBox d-flex justify-content-center">
    <div class="card p-5">
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
  min-height: 100vh;
  position: relative;
}
.card {
  box-shadow: 0px 32px 32px -16px rgba(0, 0, 0, 0.6);
  margin-top: auto;
  margin-bottom: auto;
}
</style>
