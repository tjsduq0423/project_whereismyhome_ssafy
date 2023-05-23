<template>
  <table class="fs-3 m-0">
    <thead>
      <tr>
        <th class="text-center">아파트 이름</th>
        <th class="text-center">지역</th>
        <th class="text-center">건설년도</th>
        <th class="text-center">거래가격</th>
      </tr>
    </thead>
  </table>
  <div ref="tbodyEl" class="infinity">
    <table class="table text-truncate">
      <tbody class="table-group-divider">
        <tr v-for="(item, idx) in _items" :key="idx" class="text-truncate fs-4">
          <td class="text-truncate text-center">
            {{ item.aptName }}
          </td>
          <td class="text-truncate text-center">
            {{ item.areaName }}
          </td>
          <td class="text-center text-truncate">{{ item.buildYear }}</td>
          <td class="text-center">{{ item.min / 10000 }}억 ~ {{ item.max / 10000 }} 억</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="d-flex">
    <button
      type="button"
      class="btn btn-outline-success ms-auto mt-3 btn-lg"
      @click="$router.push('/myBookMarkInMapView')"
    >
      맵에서 보기
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { storeToRefs } from 'pinia';
// import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { useInfiniteScroll } from '@vueuse/core';
import { getBookmark } from '@/api/bookmark';
// const router = useRouter();
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
    padding-left: 16px;
    min-width: 30vw;
  }
  td:nth-child(2),
  th:nth-child(2) {
    min-width: 20vw;
  }
  td:nth-child(3),
  th:nth-child(3) {
    min-width: 6vw;
  }
  td:nth-child(4),
  th:nth-child(4) {
    min-width: 14vw;
  }
}
</style>
