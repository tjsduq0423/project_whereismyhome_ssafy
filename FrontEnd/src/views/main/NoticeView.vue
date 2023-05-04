<template>
  <AppContent class="img">
    <div class="card-body px-5">
      <div class="header">
        <h1 class="text-center">공지사항</h1>
      </div>

      <div class="body">
        <table class="table text-center">
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
      </div>
      <div class="footer d-flex justify-content-center">
        <nav aria-label="Search results pages">
          <ul class="pagination">
            <li class="page-item">
              <a class="page-link" href="#">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
          </ul>
        </nav>
      </div>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import { ref } from 'vue';

const articles = ref([]);
const dummyData = [
  { id: 1, title: 'test1', author: 'author1', createTime: '1시' },
  { id: 2, title: 'test2', author: 'author2', createTime: '2시' },
  { id: 3, title: 'test3', author: 'author3', createTime: '3시' },
  { id: 4, title: 'test4', author: 'author4', createTime: '4시' },
  { id: 5, title: 'test5', author: 'author5', createTime: '5시' },
  { id: 6, title: 'test6', author: 'author6', createTime: '6시' },
  { id: 7, title: 'test7', author: 'author7', createTime: '7시' },
  { id: 8, title: 'test8', author: 'author8', createTime: '8시' },
  { id: 9, title: 'test9', author: 'author9', createTime: '9시' },
];
articles.value = [...dummyData];
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/notice02.jpg');
}
.card-body {
  width: 1000px;
}
</style>
