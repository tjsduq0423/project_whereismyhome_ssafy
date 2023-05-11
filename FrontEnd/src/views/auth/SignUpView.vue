<template>
  <AppContent class="img">
    <AppCardHeader>회원가입</AppCardHeader>
    <div class="card-body d-flex flex-column justify-content-center">
      <form @click.prevent>
        <div class="form-group mb-4">
          <label for="id" class="form-label">ID</label>
          <input
            type="text"
            class="form-control"
            id="id"
            v-model="id"
            placeholder="Enter your ID"
          />
          <small id="emailHelp" class="form-text text-muted"
            >We'll never share your email with anyone else.</small
          >
        </div>
        <div class="form-group mb-4">
          <label for="InputPassword" class="form-label">Password</label>
          <input
            type="password"
            class="form-control"
            id="InputPassword"
            v-model="password"
            placeholder="Enter your Password"
          />
        </div>
        <div class="form-group mb-4">
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
          />
        </div>
        <button
          type="button"
          class="d-grid col-12 btn btn-success text-truncate mt-4"
          @click="signUP"
        >
          회원가입
        </button>
      </form>
    </div>
  </AppContent>
</template>

<script setup>
import AppCardHeader from '@/components/AppCardHeader.vue';
import AppContent from '@/components/AppContent.vue';
import { join } from '@/api/member';
import { ref } from 'vue';

const id = ref('');
const password = ref('');
const name = ref('');
const email = ref('');

const signUP = async () => {
  try {
    await join(id.value, password.value, name.value, email.value);
  } catch (err) {
    console.error(err);
  }
};
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
