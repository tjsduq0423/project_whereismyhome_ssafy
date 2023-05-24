<template>
  <!-- 로드뷰 + 헤더 + 등등 들어갈 자리 -->
  <div class="sidebar">
    <div class="container-fluid p-0" v-if="bookmarkList.length !== 0">
      <div
        v-for="bookmark in bookmarkList"
        :key="bookmark.aptCode"
        class="listEl row m-0 p-0"
        @click="changeLatLng(bookmark.lat, bookmark.lng)"
      >
        <div class="col-12 fs-5 mt-3">{{ bookmark.aptName }}</div>
        <div class="col-12">
          <i class="bi bi-cart4"></i>
          {{ `${Math.floor(bookmark.min / 10000)}억 ${bookmark.min % 10000}만원` }} ~
          {{ `${Math.floor(bookmark.max / 10000)}억 ${bookmark.max % 10000}만원` }}
        </div>
        <div class="col-12 mb-3 d-flex">
          <i class="bi bi-geo-alt-fill"></i>
          {{ bookmark.areaName }}
          <span class="ms-auto"> 건축년도 : {{ bookmark.buildYear }}</span>
        </div>
        <hr class="m-0" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { storeToRefs } from 'pinia';
import { useBookmarkStore } from '@/stores/bookmark';
const bookmarkStore = useBookmarkStore();
const { bookmarkList, centerlatlng } = storeToRefs(bookmarkStore);

const changeLatLng = (lat, lng) => {
  console.log('test');
  centerlatlng.value = [lat, lng];
};
</script>

<style lang="scss" scoped>
.sidebar {
  position: absolute;
  z-index: 3;
  width: 365px;
  height: 100%;
  background-color: white;
  overflow: auto;
}
.sidebar::-webkit-scrollbar {
  width: 8px; /* 스크롤바의 너비 */
}
.sidebar::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #d1d1d1; /* 스크롤바의 색상 */
  border-radius: 10px;
}
.bi-cart4::before,
.bi-geo-alt-fill::before {
  display: inline-block;
  vertical-align: baseline;
}
.listEl {
  &:hover {
    background-color: rgb(187, 185, 185);
    cursor: pointer;
  }
}
</style>
