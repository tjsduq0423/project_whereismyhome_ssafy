<template>
  <AppContent class="img">
    <AppCardHeader>회원가입</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @submit.prevent>
        <div class="form-group mb-2">
          <label for="id" class="form-label">ID</label>
          <input type="text" class="form-control" id="id" v-model="id" placeholder="Enter your ID" />
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
            6~14자의 특수문자,영문,숫자를 조합해 지어주세요
          </small>
        </div>
        <div class="form-group mb-2">
          <label for="name" class="form-label">Name</label>
          <input type="text" class="form-control" id="name" v-model="name" placeholder="Enter your Name" />
        </div>
        <div class="form-group mb-4">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" v-model="email" placeholder="Ex - example@naver.com" />
          <small id="emailHelp" class="form-text text-muted">이메일 인증 하실 이메일을 입력해주세요</small>
        </div>
        <div class="row m-0">
          <button class="btn btn-success text-truncate" type="button" @click="sendEmail">회원가입</button>
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
              <button type="button" @click="signUP" class="btn btn-success text-truncate my-3">인증</button>
            </div>
          </AppModal>
        </div>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppCardHeader from '@/components/AppCardHeader.vue';
import AppContent from '@/components/AppContent.vue';
import AppModal from '@/components/AppModal.vue';

import { join } from '@/api/member';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAlert } from '@/composables/alert';
const { vAlert, vSuccess } = useAlert();
const router = useRouter();

const id = ref('');
const password = ref('');
const name = ref('');
const email = ref('');
const code = ref('');
const modalOpen = ref(false);

const sendEmail = () => {
  if (validate()) {
    //메일 보내기
    modalOpen.value = true;
  }
};

const signUP = async () => {
  try {
    await join(id.value, password.value, name.value, email.value);
    vSuccess('회원 가입 성공');
    router.push({ name: 'Home' });
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
    vAlert('입력 형식을 맞춰주세요');
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
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)), url('@/assets/img/aprtments01.jpg');
}
input[type='password']::placeholder {
  font-family: 'BMFonts';
}
input[type='password'] {
  font-family: sans-serif;
}
</style>
