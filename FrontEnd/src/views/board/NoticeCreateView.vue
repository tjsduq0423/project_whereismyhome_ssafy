<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">공지작성</h1>
    <AppBoardForm
      v-model:title="title"
      v-model:content="content"
      @submit.prevent="createNotice"
    >
      <template #actions>
        <button type="submit" class="btn btn-outline-success btn-lg ms-auto">
          저장
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

import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { registNotice } from '@/api/notice.js';

const router = useRouter();
const title = ref('');
const content = ref('');

const createNotice = async () => {
  try {
    await registNotice(title.value, content.value);
    router.push({ name: 'Notice' });
    // 알림 처리
  } catch (err) {
    console.error(err);
  }
};

const goListPage = () => router.push({ name: 'Notice' });
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/notice02.jpg');
}
.cardWidthPadding {
  margin: 0rem 28vw 0rem 28vw;
}
</style>
