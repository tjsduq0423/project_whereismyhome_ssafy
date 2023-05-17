<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">QnA</h1>
    <table class="table text-center text-truncate">
      <thead>
        <tr>
          <th scope="col">답변상태</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성시간</th>
        </tr>
      </thead>
      <tbody class="table-group-divider">
        <tr v-for="item in _items" :key="item.id" class="text-truncate">
          <th>{{ item.responseContent == null ? '답변완료' : '미답변' }}</th>
          <td>
            <a href="" class="text-decoration-none text-success" @click.prevent="goDetailPage(item.id)">{{
              item.title
            }}</a>
          </td>
          <td>{{ item.memberId }}</td>
          <td>{{ formatDate(item.createtime) }}</td>
        </tr>
      </tbody>
    </table>
    <AppPaginationBar
      :current-page="curPage"
      :total-content-count="items.length"
      :show-content-count="showContentCount"
      :show-pagination-btn-count="showPaginationBtnCount"
      @page="page => (curPage = page)"
    ></AppPaginationBar>
    <button
      v-if="userInfo !== null && userInfo.name !== '관리자'"
      type="button"
      class="btn btn-outline-success ms-auto me-2 btn-lg"
      @click="goCreatePage"
    >
      문의하기
    </button>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppPaginationBar from '@/components/AppPaginationBar.vue';
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { storeToRefs } from 'pinia';
import { getBoardList } from '@/api/board';
import { useAlert } from '@/composables/alert';

const { vAlert } = useAlert();
const router = useRouter();
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);

// 페이지 items
const items = ref([]);
const selectAll = async () => {
  try {
    const response = await getBoardList();
    items.value = [...response.data];
  } catch (err) {
    vAlert('데이터를 가져오지 못했습니다.');
    console.error(err);
  }
};
selectAll();
// pagination 제어
const curPage = ref(1);
const showContentCount = 10;
const showPaginationBtnCount = 10;

const _items = computed(() => {
  const start = (curPage.value - 1) * showContentCount;
  const end = start + showContentCount;
  return items.value.slice(start, end);
});

// 상세페이지 이동
const goDetailPage = id => {
  router.push({ name: 'QnADetail', params: { id } });
};
// 문의하기 이동
const goCreatePage = () => {
  router.push({ name: 'QnACreate' });
};
// 날짜 제어
const formatDate = date => {
  const originalDate = new Date(date);
  const year = originalDate.getFullYear();
  const month = String(originalDate.getMonth() + 1).padStart(2, '0');
  const day = String(originalDate.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;
};
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)), url('@/assets/img/Qna01.jpg');
}
.cardWidthPadding {
  margin: 0rem 28vw 0rem 28vw;
}
</style>
