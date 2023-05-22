<template>
  <AppContent class="img">
    <AppCardHeader> 비밀번호 찾기</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @submit.prevent>
        <div class="mb-4">
          <label for="id" class="form-label">ID</label>
          <input
            type="text"
            class="form-control"
            id="id"
            v-model="id"
            placeholder="Enter your ID"
          />
        </div>
        <div class="mb-4">
          <label for="email" class="form-label">Email</label>
          <input
            type="email"
            class="form-control"
            id="email"
            v-model="email"
            placeholder="Ex - example@naver.com"
            @keydown.enter="authEmail"
          />
        </div>
        <div class="row m-0">
          <button class="btn btn-success text-truncate" type="button" @click="authEmail">
            비밀번호 찾기
          </button>
          <AppModal :is-open="modalOpen">
            <div v-if="!isAuth" class="card p-5 mx-auto" style="width: 30vw">
              <div class="row p-0 m-0">
                <label for="code" class="form-label">인증 코드 입력</label>
                <input
                  type="text"
                  class="form-control"
                  id="inputCode"
                  v-model="inputCode"
                  placeholder="Enter your Authentication code"
                />
                <small id="codeHelp" class="form-text text-muted"
                  >이메일로 전송된 코드를 입력해주세요</small
                >
              </div>
              <div class="row p-0 m-0">
                <button
                  type="button"
                  @click="isAuth = inputCode === authCode"
                  class="btn btn-success text-truncate my-3"
                >
                  인증
                </button>
              </div>
              <div class="row p-0 m-0">
                <button class="btn btn-success text-truncate" @click="modalOpen = false">
                  닫기
                </button>
              </div>
            </div>
            <div v-else class="card p-5 mx-auto" style="width: 30vw">
              <div class="row p-0 m-0">
                <label for="code" class="form-label">새 비밀번호 입력</label>
                <input
                  type="password"
                  class="form-control"
                  id="inputPassword"
                  v-model="inputPassword"
                  placeholder="Enter your new Password"
                  @keydown.enter="chagePW"
                />
                <small id="codeHelp" class="form-text text-muted"
                  >이메일로 전송된 코드를 입력해주세요</small
                >
              </div>
              <div class="row p-0 m-0">
                <button type="button" @click="chagePW" class="btn btn-success text-truncate my-3">
                  비밀번호 변경
                </button>
              </div>
            </div>
          </AppModal>
        </div>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppModal from '@/components/features/AppModal.vue';
import AppContent from '@/components/layouts/AppContent.vue';
import AppCardHeader from '@/components/layouts/AppCardHeader.vue';
import { ref } from 'vue';
import { useAlert } from '@/composables/alert';
import { changePassword, sendEmail } from '@/api/member';
import { useRouter } from 'vue-router';
const router = useRouter();
const { vAlert, vSuccess } = useAlert();
const id = ref('');
const email = ref('');

const isAuth = ref(false);
const inputPassword = ref('');
const chagePW = async () => {
  try {
    await changePassword(id.value, inputPassword.value);
    vSuccess('비밀번호 변경 성공');
    router.push({ name: 'Login' });
  } catch (err) {
    console.error(err);
    vAlert('비밀번호 변경 실패');
  }
};

const modalOpen = ref(false);
const inputCode = ref('');
const authCode = ref('');

const authEmail = async () => {
  if (!validate()) return;
  modalOpen.value = true;
  try {
    const response = await sendEmail(email.value);
    authCode.value = response.data;
  } catch (err) {
    console.error(err);
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
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('/img/aprtments01.jpg');
}
input[type='password']::placeholder {
  font-family: 'BMFonts';
}
input[type='password'] {
  font-family: sans-serif;
}
</style>
