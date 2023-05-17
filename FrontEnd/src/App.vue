<template>
  <AppNav></AppNav>

  <RouterView></RouterView>

  <AppFooter></AppFooter>

  <AppAlert></AppAlert>
</template>
<script setup>
import { RouterView } from 'vue-router';
import AppNav from '@/components/AppNav.vue';
import AppFooter from '@/components/AppFooter.vue';
import AppAlert from '@/components/AppAlert.vue';
</script>

<style lang="scss">
@font-face {
  font-family: 'BMFonts';
  src: url('@/assets/fonts/otf/BMHANNAProOTF.otf');
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
