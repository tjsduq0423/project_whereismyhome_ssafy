<template>
  <div
    class="viewBox d-flex flex-column justify-content-center align-items-center"
  >
    <slot name="header"></slot>
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
  margin-top: 86px;
  margin-bottom: 5vh;
}
</style>
