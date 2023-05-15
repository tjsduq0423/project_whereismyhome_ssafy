<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">QnA작성</h1>
    <AppBoardForm
      v-model:title="title"
      v-model:content="content"
      @submit.prevent="createQnA"
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
import { useAuthStore } from '@/stores/auth';
import { storeToRefs } from 'pinia';
import { registBoard } from '@/api/board';
import { useAlert } from '@/composables/alert';

const { vAlert, vSuccess } = useAlert();
const router = useRouter();
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);

const title = ref('');
const content = ref('');

const createQnA = async () => {
  try {
    await registBoard(userInfo.value.id, title.value, content.value);
    router.push({ name: 'QnA' });
    vSuccess('QnA 등록 성공');
  } catch (err) {
    vAlert('QnA 등록 실패');
    console.error(err);
  }
};

const goListPage = () => router.push({ name: 'QnA' });
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/Qna01.jpg');
}
.cardWidthPadding {
  margin: 0rem 28vw 0rem 28vw;
}
</style>
