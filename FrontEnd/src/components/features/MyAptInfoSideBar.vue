<template>
  <!-- 로드뷰 + 헤더 + 등등 들어갈 자리 -->
  <div class="sidebar">
    <div
      class="aptInfo d-flex flex-column justify-content-center align-items-center"
      v-if="bookmarkList.length !== 0"
    >
      <div
        v-for="bookmark in bookmarkList"
        :key="bookmark.aptCode"
        class="listEl"
        @click="changeLatLng(bookmark.lat, bookmark.lng)"
      >
        <div>{{ bookmark.aptName }}</div>
        <div>
          <i class="bi bi-cart4"></i>
          {{ `${Math.floor(bookmark.min / 10000)}억 ${bookmark.min % 10000}만원` }} ~
          {{ `${Math.floor(bookmark.max / 10000)}억 ${bookmark.max % 10000}만원` }}
        </div>
        <div>
          <i class="bi bi-geo-alt-fill"></i>
          {{ bookmark.areaName }}
          <span> ~ {{ bookmark.buildYear }}</span>
        </div>
        <hr />
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
  vertical-align: middle;
}
.listEl {
  &:hover {
    background-color: rgb(187, 185, 185);
    cursor: pointer;
  }
}
</style>
