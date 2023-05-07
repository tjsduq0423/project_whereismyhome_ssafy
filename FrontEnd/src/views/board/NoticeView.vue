<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">공지사항</h1>
    <table class="table text-center text-truncate">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성시간</th>
        </tr>
      </thead>
      <tbody class="table-group-divider">
        <tr v-for="item in _items" :key="item.id">
          <th>{{ item.id }}</th>
          <td>
            <a
              href=""
              class="text-decoration-none"
              @click.prevent="goDetailPage(item.id)"
              >{{ item.title }}</a
            >
          </td>
          <td>{{ item.author }}</td>
          <td>{{ item.createTime }}</td>
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
      type="button"
      class="btn btn-outline-success ms-auto me-2 btn-lg"
      @click="goCreatePage"
    >
      공지작성
    </button>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppPaginationBar from '@/components/AppPaginationBar.vue';
import { useRouter } from 'vue-router';
import { computed, ref } from 'vue';
import data from '@/assets/data/noticeData.js';

const router = useRouter();

const curPage = ref(1);
const items = ref([...data]);
const showContentCount = 5;
const showPaginationBtnCount = 10;
const _items = computed(() => {
  const start = (curPage.value - 1) * showContentCount + 1;
  const end = start + showContentCount;
  return items.value.slice(start, end);
});

//상세페이지 이동
const goDetailPage = id => {
  router.push({ name: 'NoticeDetail', params: { id } });
};
// 공지작성 이동
const goCreatePage = () => {
  router.push({ name: 'NoticeCreate' });
};
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/notice02.jpg');
}
.cardWidthPadding {
  margin: 0rem 32rem 0rem 32rem;
}
</style>
