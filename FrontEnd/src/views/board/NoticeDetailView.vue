<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="my-3 text-center">{{ item.title }}</h1>
    <hr />
    <div>{{ item.content }}</div>
    <hr />
    <div class="row g-2">
      <div class="col-auto me-auto">
        <button
          type="button"
          class="btn btn-outline-primary btn-lg"
          @click="goListPage"
        >
          목록
        </button>
      </div>
      <!-- 관리자 권한 v-if 필요 -->
      <div class="col-auto">
        <button
          type="button"
          class="btn btn-outline-success btn-lg"
          @click="goEditPage"
        >
          수정
        </button>
      </div>
      <div class="col-auto">
        <button
          type="button"
          class="btn btn-outline-danger btn-lg"
          @click="deleteNotice"
        >
          삭제
        </button>
      </div>
    </div>
  </AppContent>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import AppContent from '@/components/AppContent.vue';
import data from '@/assets/data/noticeData.js';
const route = useRoute();
const router = useRouter();
const id = route.params.id;

const item = { ...data[id] };
const goListPage = () => router.push({ name: 'Notice' });
const goEditPage = () => router.push({ name: 'NoticeEdit', params: { id } });
const deleteNotice = () => {};
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/notice02.jpg');
}
.cardWidthPadding {
  margin: 0rem 32rem 0rem 32rem;
}
</style>
