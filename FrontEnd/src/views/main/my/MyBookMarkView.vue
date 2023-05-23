<template>
  <table class="table text-truncate fs-3 m-0">
    <thead>
      <tr>
        <th>아파트 이름</th>
        <th>지역</th>
        <th>건설년도</th>
        <th>거래가격</th>
      </tr>
    </thead>
  </table>
  <div ref="tbodyEl" class="infinity">
    <table class="table text-truncate">
      <tbody>
        <tr v-for="(item, idx) in _items" :key="idx" class="text-truncate">
          <td class="text-truncate">
            {{ item.aptName }}
          </td>
          <td class="text-truncate">
            {{ item.areaName }}
          </td>
          <td class="text-center text-truncate">{{ item.buildYear }}</td>
          <td>{{ item.min / 10000 }}억 ~ {{ item.max / 10000 }} 억</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="d-flex">
    <button type="button" class="btn btn-outline-success ms-auto mt-3 btn-lg" @click="goMap">
      맵에서 보기
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { useInfiniteScroll } from '@vueuse/core';
import { getBookmark } from '@/api/bookmark';
const router = useRouter();
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);

const tbodyEl = ref(null);
const items = ref([]);
const _items = ref([]);

try {
  const response = await getBookmark(userInfo.value.id);
  items.value = [...response.data];
  _items.value.push(...items.value.slice(0, 5));
} catch (err) {
  console.error(err);
}

useInfiniteScroll(
  tbodyEl,
  () => {
    const length = _items.value.length + 1;
    _items.value.push(...items.value.slice(length, length + 5));
  },
  { distance: 10 },
);

const goMap = () => {
  router.push({ name: '' });
};
</script>

<style lang="scss" scoped>
.infinity {
  height: 30vh;
  overflow-y: scroll;
  &::-webkit-scrollbar {
    width: 8px; /* 스크롤바의 너비 */
  }
  &::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: #d1d1d1; /* 스크롤바의 색상 */
    border-radius: 10px;
  }
  &::-webkit-scrollbar-track {
    background-color: #ececec;
  }
}
tr {
  td:nth-child(1),
  th:nth-child(1) {
    text-align: start;
    padding-left: 16px;
    min-width: 20vw;
  }
  td:nth-child(2),
  th:nth-child(2) {
    min-width: 30vw;
  }
  td:nth-child(3),
  th:nth-child(3) {
    min-width: 8vw;
  }
}
</style>
