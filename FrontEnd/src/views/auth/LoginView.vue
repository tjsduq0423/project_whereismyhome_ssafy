<template>
  <AppContent class="img">
    <AppCardHeader>로그인</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <button type="button" class="mb-4 btn p-0 shadow-sm border border-0" @click="kakao">
        <img src="/img/kakao_login_medium_wide.png" style="width: 100%; height: 100%" />
      </button>
      <form @submit.prevent>
        <div class="mb-4">
          <label for="id" class="form-label">ID</label>
          <input
            type="text"
            class="form-control"
            id="id"
            v-model="id"
            autoComplete="username"
            placeholder="Enter your ID"
            @keydown.enter="login"
          />
        </div>
        <div class="mb-4">
          <label for="password" class="form-label">Password</label>
          <input
            type="password"
            class="form-control"
            id="Password"
            v-model="password"
            autoComplete="current-password"
            placeholder="Enter your Password"
            @keydown.enter="login"
          />
        </div>
        <button
          type="button"
          class="d-grid col-12 btn btn-success text-truncate mb-4"
          @click="login"
        >
          로그인
        </button>
        <div class="d-flex justify-content-between mt-3">
          <button type="button" class="btn btn-outline-danger text-truncate" @click="goFindPW">
            비밀번호 찾기
          </button>
          <button type="button" class="btn btn-outline-success text-truncate" @click="goSignup">
            회원 가입
          </button>
        </div>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppCardHeader from '@/components/layouts/AppCardHeader.vue';
import AppContent from '@/components/layouts/AppContent.vue';
import { useRouter, useRoute } from 'vue-router';
import { useAuthStore } from '@/stores/auth.js';
import { ref } from 'vue';
import { useAlert } from '@/composables/alert';
const router = useRouter();
const route = useRoute();

const { vAlert, vSuccess } = useAlert();
const { validateMember, kvalidateMember } = useAuthStore();
const id = ref('');
const password = ref('');

const kLogin = async code => {
  try {
    await kvalidateMember(code);
    vSuccess('카카오 로그인 성공');
    router.push({ name: 'Home' });
  } catch (err) {
    vAlert('카카오 로그인 실패');
    console.error(err);
  }
};

const login = async () => {
  if (!validate()) return;
  try {
    await validateMember(id.value, password.value);
    vSuccess('로그인 성공');
    router.push({ name: 'Home' });
  } catch (err) {
    vAlert('ID와 비밀번호를 다시 확인해주세요');
    console.error(err);
  }
};

const validate = () => {
  if (!id.value || !password.value) {
    vAlert('입력 형식을 맞춰주세요');
    return false;
  }
  return true;
};

const goFindPW = () => {
  router.push({ name: 'FindPassword' });
};
const goSignup = () => {
  router.push({ name: 'SignUp' });
};
const kakao = () => {
  window.open(
    'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=6c779f7199f5aa77f349e6165df482bc&redirect_uri=http://localhost:5500/login',
    '_self',
  );
};
// 쿼리스트링으로 code를 받게되면 이를 통해 서버에 요청을 보내는 로직을 수행한다.
if (route.query.code) {
  console.log(route.query.code);
  kLogin(route.query.code);
}
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
