<template>
  <div class="viewBox d-flex flex-column justify-content-center align-items-center">
    <transition appear enter-active-class="animate__animated animate__bounceInDown ">
      <slot name="header"></slot>
    </transition>
    <transition
      appear
      mode="out-in"
      enter-active-class="animate__animated animate__slideInRight"
      leave-active-class="animate__animated animate__slideOutLeft"
    >
      <div class="card p-5">
        <slot></slot>
      </div>
    </transition>
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
  margin-top: 70px;
  margin-bottom: 5vh;
}
</style>
