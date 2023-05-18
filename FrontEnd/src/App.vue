<template>
  <AppNav></AppNav>

  <RouterView></RouterView>

  <AppFooter></AppFooter>

  <AppAlert></AppAlert>
</template>
<script setup>
import { RouterView } from 'vue-router';
import AppNav from '@/components/layouts/AppNav.vue';
import AppFooter from '@/components/layouts/AppFooter.vue';
import AppAlert from '@/components/features/AppAlert.vue';
</script>

<style lang="scss">
@font-face {
  font-family: 'BMFonts';
  src: url('@/styles/fonts/BMHANNAProOTF.otf');
}
* {
  font-family: 'BMFonts', sans-serif;
}

body::-webkit-scrollbar {
  width: 8px; /* 스크롤바의 너비 */
}

body::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #d1d1d1; /* 스크롤바의 색상 */
  border-radius: 10px;
}
</style>
