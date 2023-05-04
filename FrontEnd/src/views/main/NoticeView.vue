<template>
  <AppContent class="img">
    <div class="header"></div>
    <h1 class="text-center text-truncate mb-5">공지사항</h1>
    <table class="table text-center text-truncate">
      <thead class="table-light">
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성시간</th>
        </tr>
      </thead>
      <tbody class="table-group-divider">
        <tr v-for="article in articles" :key="article.id">
          <th>{{ article.id }}</th>
          <td>{{ article.title }}</td>
          <td>{{ article.author }}</td>
          <td>{{ article.createTime }}</td>
        </tr>
      </tbody>
    </table>
    <AppPaginationBar></AppPaginationBar>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppPaginationBar from '@/components/AppPaginationBar.vue';
import { ref } from 'vue';
import { data } from '@/assets/data/dummy.js';

const articles = ref([]);
articles.value = [...data];
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/notice02.jpg');
}
.header {
  margin: 0rem 32rem 0rem 32rem;
}
</style>
