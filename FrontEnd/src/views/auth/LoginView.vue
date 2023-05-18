<template>
  <AppContent class="img">
    <AppCardHeader>로그인</AppCardHeader>
    <div>
      <img src="@/assets/img/kakao_login_medium.png" @click="kakao" />
    </div>
    <div class="card-body d-flex flex-column justify-content-center">
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
import AppCardHeader from '@/components/AppCardHeader.vue'
import AppContent from '@/components/AppContent.vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth.js'
import { ref } from 'vue'
import { useAlert } from '@/composables/alert'

const { vAlert, vSuccess } = useAlert()
const { validateMember } = useAuthStore()
const id = ref('')
const password = ref('')

const router = useRouter()

const login = () => {
  try {
    validateMember(id.value, password.value)
    vSuccess('로그인 성공')
    router.push({ name: 'Home' })
  } catch (err) {
    vAlert('ID와 비밀번호를 다시 확인해주세요')
    console.error(err)
  }
}
const goFindPW = () => {
  router.push({ name: 'FindPassword' })
}
const goSignup = () => {
  router.push({ name: 'SignUp' })
}
const kakao = () => {
  window.open(
    'https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=6c779f7199f5aa77f349e6165df482bc&redirect_uri=http://localhost:5500/login'
  )
}
</script>

<style scoped>
.img {
  background-image: linear-gradient(rgba(0, 0, 0, 0.4), rgba(0, 0, 0, 0.5)),
    url('@/assets/img/aprtments01.jpg');
}
input[type='password']::placeholder {
  font-family: 'BMFonts';
}
input[type='password'] {
  font-family: sans-serif;
}
</style>
