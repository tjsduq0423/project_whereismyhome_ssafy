<template>
  <div class="header px-4 py-2 border rounded shadow-sm">
    <slot></slot>
  </div>
</template>

<style scoped>
.header {
  top: 0%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 2em;
  position: absolute;
  z-index: 1;
  text-align: center;
  min-width: 50%;
  background-color: white;
}
</style>
