<template>
  <AppContent class="img">
    <div class="cardWidthPadding"></div>
    <h2 class="" style="word-break: break-all; word-wrap: normal">
      {{ item.title }}
    </h2>
    <hr />
    <p style="height: 30vh; max-width: 60vw; word-break: break-all; word-wrap: normal" class="fs-4">
      {{ item.content }}
    </p>
    <hr />
    <div class="row g-2">
      <div class="col-auto me-auto">
        <button type="button" class="btn btn-outline-success btn-lg" @click="goListPage">
          목록
        </button>
      </div>
      <!-- 관리자 권한 v-if 필요 -->
      <div class="col-auto">
        <button
          type="button"
          class="btn btn-outline-primary btn-lg"
          @click="goEditPage"
          v-if="userInfo !== null && userInfo.name === '관리자'"
        >
          수정
        </button>
      </div>
    </div>
  </AppContent>
</template>

<script setup>
import AppContent from '@/components/layouts/AppContent.vue';
import { useRoute, useRouter } from 'vue-router';
import { useAuthStore } from '@/stores/auth';
import { storeToRefs } from 'pinia';
import { getNoticeById } from '@/api/notice';
import { ref } from 'vue';
import { useAlert } from '@/composables/alert';
const { vAlert } = useAlert();

const route = useRoute();
const router = useRouter();
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);
const id = route.params.id;
const item = ref({});

const fetchData = async () => {
  try {
    const response = await getNoticeById(id);
    item.value = response.data;
  } catch (err) {
    vAlert('데이터를 가져오지 못했습니다.');
    console.error(err);
  }
};
fetchData();
const goListPage = () => router.push({ name: 'Notice' });
const goEditPage = () => router.push({ name: 'NoticeEdit', params: { id } });
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('/img/notice02.jpg');
}
.cardWidthPadding {
  margin: 0rem 28vw 0rem 28vw;
}
</style>
