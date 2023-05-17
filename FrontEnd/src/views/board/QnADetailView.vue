<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h2 class="" style="word-break: break-all; word-wrap: normal">
      {{ item.title }}
    </h2>
    <hr />
    <p style="height: 33vh; word-break: break-all; word-wrap: normal; max-width: 60vw" class="fs-4">
      {{ item.content }}
    </p>
    <div class="row g-2">
      <div class="col-auto me-auto">
        <button type="button" class="btn btn-outline-success btn-lg" @click="goListPage">목록</button>
      </div>
      <!-- 관리자 권한 v-if 필요 -->
      <template v-if="userInfo.id === item.memberId">
        <div class="col-auto">
          <button type="button" class="btn btn-outline-primary btn-lg" @click="goEditPage">수정</button>
        </div>
        <div class="col-auto">
          <button type="button" class="btn btn-outline-danger btn-lg" @click="deleteQnA">삭제</button>
        </div>
      </template>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/AppContent.vue';
import { useRoute, useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { getBoardDetail, deleteBoard } from '@/api/board';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';
import { useAlert } from '@/composables/alert';
const { vAlert, vSuccess } = useAlert();

const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);

const route = useRoute();
const router = useRouter();
const id = route.params.id;

// detail content api call
const item = ref({});
const getItem = async () => {
  try {
    const response = await getBoardDetail(id);
    item.value = { ...response.data };
  } catch (err) {
    vAlert('데이터를 가져오지 못했습니다.');
    console.error(err);
  }
};
getItem();

const deleteQnA = async () => {
  try {
    await deleteBoard(id);
    router.push({ name: 'QnA' });
    vSuccess('QnA 삭제 성공');
  } catch (err) {
    vAlert('QnA 삭제 실패');
    console.error(err);
  }
};

const goListPage = () => router.push({ name: 'QnA' });
const goEditPage = () => router.push({ name: 'QnAEdit', params: { id } });
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)), url('@/assets/img/Qna01.jpg');
}
.cardWidthPadding {
  margin: 0rem 28vw 0rem 28vw;
}
</style>
