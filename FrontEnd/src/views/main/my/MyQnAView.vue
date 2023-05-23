<template>
  <table class="table text-truncate fs-3 m-0">
    <thead class="border border-0">
      <tr>
        <th class="text-center">제목</th>
        <th class="">답변</th>
        <th class="text-center">작성시간</th>
      </tr>
    </thead>
  </table>
  <div ref="tbodyEl" class="infinity">
    <table class="table text-truncate">
      <tbody class="">
        <tr v-for="item in _items" :key="item.id" class="text-truncate">
          <td class="text-center">
            <a
              href=""
              class="text-decoration-none text-success"
              @click.prevent="goDetailPage(item.id)"
              >{{ item.title }}</a
            >
          </td>
          <td>{{ item.responseContent ? item.responseContent : '답변없음' }}</td>
          <td class="text-center">{{ formatDate(item.createtime) }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="d-flex">
    <button type="button" class="btn btn-outline-success ms-auto mt-3 btn-lg" @click="goCreatePage">
      질문작성
    </button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { useInfiniteScroll } from '@vueuse/core';
import { getBoardById } from '@/api/board';
const router = useRouter();
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);

const tbodyEl = ref(null);
const items = ref([]);
const _items = ref([]);

try {
  const response = await getBoardById(userInfo.value.id);
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

// 상세페이지 이동
const goDetailPage = id => {
  router.push({ name: 'QnADetail', params: { id } });
};
// 문의하기 이동
const goCreatePage = () => {
  router.push({ name: 'QnACreate' });
};
const formatDate = date => {
  const originalDate = new Date(date);
  const year = originalDate.getFullYear();
  const month = String(originalDate.getMonth() + 1).padStart(2, '0');
  const day = String(originalDate.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;
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
    width: 20vw;
  }
  td:nth-child(2),
  th:nth-child(2) {
    width: 60vw;
  }
  td:nth-child(3),
  th:nth-child(3) {
    width: 8vw;
  }
}
</style>
