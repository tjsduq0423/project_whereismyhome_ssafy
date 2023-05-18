<template>
  <AppContent class="img">
    <AppCardHeader> 비밀번호 찾기</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @submit.prevent>
        <div class="mb-4">
          <label for="id" class="form-label">ID</label>
          <input type="text" class="form-control" id="id" v-model="id" placeholder="Enter your ID" />
        </div>
        <div class="mb-4">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" v-model="email" placeholder="Ex - example@naver.com" />
        </div>
        <div class="row m-0">
          <button class="btn btn-success text-truncate" type="button" @click="sendEmail">비밀번호 찾기</button>
          <AppModal
            :is-open="modalOpen"
            @close-modal="
              () => {
                modalOpen = false;
              }
            "
          >
            <div class="row">
              <label for="code" class="form-label"> Authentication Code</label>
              <input
                type="text"
                class="form-control"
                id="id"
                v-model="code"
                placeholder="Enter your Authentication code"
              />
              <small id="codeHelp" class="form-text text-muted">이메일로 전송된 코드를 입력해주세요</small>
            </div>
            <div class="row">
              <button type="button" @click="showPassword" class="btn btn-success text-truncate my-3">인증</button>
            </div>
          </AppModal>
        </div>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppModal from '@/components/AppModal.vue';
import AppContent from '@/components/AppContent.vue';
import AppCardHeader from '@/components/AppCardHeader.vue';
import { ref } from 'vue';
import { useAlert } from '@/composables/alert';
const { vAlert } = useAlert();
const id = ref('');
const email = ref('');

const code = ref('');
const modalOpen = ref(false);

const sendEmail = () => {
  if (validate()) {
    //메일 보내기
    modalOpen.value = true;
  }
};
const validate = () => {
  if (!id.value || !email.value) {
    vAlert('입력 형식을 맞춰주세요');
    return false;
  }
  if (/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value) === false) {
    vAlert('이메일 형식이 맞지 않습니다.');
    return false;
  }
  return true;
};
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)), url('/img/aprtments01.jpg');
}
</style>
