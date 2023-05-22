<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">QnA수정</h1>
    <AppBoardForm v-model:title="title" v-model:content="content" @submit.prevent="updateQnA">
      <template #actions>
        <button type="submit" class="btn btn-outline-success btn-lg ms-auto">수정</button>
        <button type="button" class="btn btn-outline-danger btn-lg" @click="goListPage">
          취소
        </button>
      </template>
    </AppBoardForm>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/layouts/AppContent.vue';
import AppBoardForm from '@/components/layouts/AppBoardForm.vue';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { putBoard, getBoardDetail } from '@/api/board';
import { useAlert } from '@/composables/alert';

const { vAlert, vSuccess } = useAlert();
const route = useRoute();
const router = useRouter();
const id = route.params.id;

// detail content api call
const title = ref('');
const content = ref('');

const getItem = async () => {
  try {
    const response = await getBoardDetail(id);
    title.value = response.data.title;
    content.value = response.data.content;
  } catch (err) {
    vAlert('데이터를 가져오지 못했습니다.');
    console.error(err);
  }
};
getItem();

const updateQnA = async () => {
  try {
    await putBoard(id, title.value, content.value);
    router.push({ name: 'QnA' });
    vSuccess('수정 성공했습니다.');
  } catch (err) {
    vAlert('수정 실패했습니다.');
    console.error(err);
  }
};
const goListPage = () => router.push({ name: 'QnA' });
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)), url('/img/Qna01.jpg');
}
.cardWidthPadding {
  margin: 0rem 28vw 0rem 28vw;
}
</style>
