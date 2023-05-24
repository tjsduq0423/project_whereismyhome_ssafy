<template>
  <table class="fs-3 m-0">
    <thead class="border-bottom border-3 border-dark">
      <tr>
        <th class="text-center">제목</th>
        <th class="text-center">답변내용</th>
        <th class="text-center">작성일자</th>
      </tr>
    </thead>
  </table>
  <div ref="tbodyEl" class="infinity">
    <table class="table text-truncate">
      <tbody>
        <tr v-for="item in _items" :key="item.id" class="text-truncate fs-4">
          <td class="text-truncate text-center">
            <a
              href=""
              class="text-decoration-none text-success"
              @click.prevent="goDetailPage(item.id)"
              >{{ item.title }}</a
            >
          </td>
          <td class="text-truncate text-center">
            {{ item.responseContent ? item.responseContent : '답변없음' }}
          </td>
          <td class="text-center text-truncate">{{ formatDate(item.createtime) }}</td>
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
    padding-left: 16px;
    min-width: 15vw;
  }
  td:nth-child(2),
  th:nth-child(2) {
    min-width: 35vw;
  }
  td:nth-child(3),
  th:nth-child(3) {
    min-width: 10vw;
  }
}
</style>
