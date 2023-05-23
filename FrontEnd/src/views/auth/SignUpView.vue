<template>
  <AppContent class="img">
    <AppCardHeader>회원가입</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @submit.prevent>
        <div class="form-group mb-2">
          <label for="id" class="form-label">ID</label>
          <input
            type="text"
            class="form-control"
            id="id"
            v-model="id"
            placeholder="Enter your ID"
            @keyup="checkId"
          />
          <small v-if="id && isDuplicated" id="passwordHelp" class="form-text text-danger">
            중복 아이디입니다. 다시 입력해주세요.
          </small>
        </div>

        <div class="form-group mb-2">
          <label for="InputPassword" class="form-label">Password</label>
          <input
            type="password"
            class="form-control"
            id="InputPassword"
            v-model="password"
            placeholder="Enter your Password"
          />
          <small id="passwordHelp" class="form-text text-muted">
            6~14자의 특수문자,영문,숫자를 조합해주세요
          </small>
        </div>
        <div class="form-group mb-2">
          <label for="name" class="form-label">Name</label>
          <input
            type="text"
            class="form-control"
            id="name"
            v-model="name"
            placeholder="Enter your Name"
          />
        </div>
        <div class="form-group mb-4">
          <label for="email" class="form-label">Email</label>
          <input
            type="email"
            class="form-control"
            id="email"
            v-model="email"
            placeholder="Ex - example@naver.com"
            @keydown.enter="authEmail"
          />
          <small id="emailHelp" class="form-text text-muted"
            >인증을 위한 이메일을 입력해주세요</small
          >
        </div>
        <div class="row m-0">
          <button class="btn btn-success text-truncate" type="button" @click="authEmail">
            회원가입
          </button>
          <AppModal :is-open="modalOpen">
            <div class="card p-5 mx-auto" style="width: 30vw">
              <div class="row p-0 m-0">
                <label for="code" class="form-label">인증 코드 입력</label>
                <input
                  type="text"
                  class="form-control"
                  id="id"
                  v-model="inputCode"
                  placeholder="Enter your Authentication code"
                />
                <small id="codeHelp" class="form-text text-muted"
                  >이메일로 전송된 코드를 입력해주세요</small
                >
              </div>
              <div class="row p-0 m-0">
                <button type="button" @click="signUP" class="btn btn-success text-truncate my-3">
                  인증
                </button>
              </div>
              <div class="row p-0 m-0">
                <button class="btn btn-success text-truncate" @click="modalOpen = false">
                  닫기
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
import AppCardHeader from '@/components/layouts/AppCardHeader.vue';
import AppContent from '@/components/layouts/AppContent.vue';
import AppModal from '@/components/features/AppModal.vue';

import { join, sendEmail, checkMember } from '@/api/member';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAlert } from '@/composables/alert';
import { useDebounceFn } from '@vueuse/core';
const { vAlert, vSuccess } = useAlert();
const router = useRouter();

const id = ref('');
const password = ref('');
const name = ref('');
const email = ref('');

const isDuplicated = ref(false);
const checkId = useDebounceFn(async () => {
  if (!id.value) return;
  const response = await checkMember(id.value);
  isDuplicated.value = !response.data;
}, 500);

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

const signUP = async () => {
  if (inputCode.value !== authCode.value) {
    inputCode.value = '';
    vAlert('코드를 다시 확인해주세요.');
    return;
  }
  try {
    await join(id.value, password.value, name.value, email.value);
    vSuccess('회원 가입 성공');
    router.push({ name: 'Login' });
  } catch (err) {
    console.error(err);
    vAlert('회원 가입 실패');
    id.value = '';
    password.value = '';
    name.value = '';
    email.value = '';
    modalOpen.value = false;
  }
};

const validate = () => {
  if (!id.value || !password.value || !name.value || !email.value) {
    vAlert('모든 정보를 입력해주세요');
    return false;
  }
  if (password.value.length < 6 || password.value.length > 14) {
    vAlert('비밀번호 길이가 적절하지 않습니다.');
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
