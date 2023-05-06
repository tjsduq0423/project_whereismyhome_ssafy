<template>
  <AppNav></AppNav>

  <RouterView></RouterView>

  <AppFooter></AppFooter>
</template>
<script setup>
import { RouterView } from 'vue-router';
import AppNav from './components/AppNav.vue';
import AppFooter from './components/AppFooter.vue';
</script>

<style scoped>
@font-face {
  font-family: 'BMFonts';
  src: url('@/assets/fonts/otf/BMHANNAProOTF.otf');
}
* {
  font-family: 'BMFonts', sans-serif;
}
</style>
