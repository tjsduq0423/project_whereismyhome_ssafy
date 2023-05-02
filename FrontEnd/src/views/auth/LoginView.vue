<template>
  <div class="header px-4 py-2 border rounded shadow-sm">로그인</div>
  <div class="row mt-5 w-25">
    <div class="col">
      <div class="card shadow-sm">
        <div class="card-body mt-5 mb-3">
          <form @click.prevent>
            <div class="mb-3">
              <label for="id" class="form-label">ID</label>
              <input
                type="text"
                class="form-control"
                id="id"
                placeholder="Enter your ID"
              />
            </div>
            <div class="mb-3">
              <label for="password" class="form-label">Password</label>
              <input
                type="password"
                class="form-control"
                id="Password"
                placeholder="Enter your Password"
              />
            </div>
            <button
              type="button"
              class="d-grid col-12 btn btn-primary text-truncate"
            >
              로그인
            </button>
            <div class="d-flex justify-content-between mt-3">
              <button
                type="button"
                class="btn btn-outline-danger text-truncate"
                @click="goFindPW"
              >
                비밀번호 찾기
              </button>
              <button
                type="button"
                class="btn btn-outline-primary text-truncate"
                @click="goSignup"
              >
                회원 가입
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import router from '@/router';

const goFindPW = () => {
  router.push('/findPassword');
};
const goSignup = () => {
  router.push('/signUp');
};
</script>

<style scoped>
.header {
  top: 25px;
  font-size: 2em;
  position: absolute;
  z-index: 1;
  background: white;
}
</style>
