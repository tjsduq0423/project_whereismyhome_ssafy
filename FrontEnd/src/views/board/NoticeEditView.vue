<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h1 class="text-center text-truncate mb-5">공지수정</h1>
    <AppBoardForm
      v-model:title="title"
      v-model:content="content"
      @submit.prevent="updateNotice"
    >
      <template #actions>
        <button type="submit" class="btn btn-outline-success btn-lg ms-auto">
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
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { putNotice, getNoticeById } from '@/api/notice.js';

const route = useRoute();
const router = useRouter();
const id = route.params.id;

const title = ref('');
const content = ref('');
const getItem = async () => {
  try {
    const response = await getNoticeById(id);
    title.value = response.data.title;
    content.value = response.data.content;
    // 알람
  } catch (err) {
    console.error(err);
  }
};
getItem();

const updateNotice = async () => {
  try {
    await putNotice(id, title.value, content.value);
    router.push({ name: 'NoticeDetail', params: { id } });
    // 알람
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
