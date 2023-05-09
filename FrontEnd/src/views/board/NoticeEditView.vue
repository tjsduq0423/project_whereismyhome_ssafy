<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">공지수정</h1>
    <AppBoardForm
      v-model:title="item.title"
      v-model:content="item.content"
      @submit.prevent="updateNotice"
    >
      <template #actions>
        <button type="button" class="btn btn-outline-success btn-lg ms-auto">
          수정
        </button>
        <button
          type="button"
          class="btn btn-outline-danger btn-lg"
          @click="goListPage"
        >
          취소
        </button>
      </template>
    </AppBoardForm>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import AppBoardForm from '@/components/AppBoardForm.vue';
import data from '@/assets/data/noticeData.js';

import { useRoute, useRouter } from 'vue-router';
const route = useRoute();
const router = useRouter();
const id = route.params.id;

const item = { ...data[id] };
const goListPage = () => router.push({ name: 'Notice' });
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/notice02.jpg');
}
.cardWidthPadding {
  margin: 0rem 32rem 0rem 32rem;
}
</style>
