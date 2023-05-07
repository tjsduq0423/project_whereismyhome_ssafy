<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">공지작성</h1>
    <AppBoardForm
      v-model:title="title"
      v-model:content="content"
      @submit.prevent="createQnA"
    >
      <template #actions>
        <button type="button" class="btn btn-outline-success btn-lg ms-auto">
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
import { useRouter } from 'vue-router';
import { ref } from 'vue';

const router = useRouter();
const title = ref('');
const content = ref('');

const goListPage = () => router.push({ name: 'QnA' });
const createQnA = () => {};
</script>

<style scoped>
.img {
  background-image: url('@/assets/img/Qna01.jpg');
}
.cardWidthPadding {
  margin: 0rem 32rem 0rem 32rem;
}
</style>
