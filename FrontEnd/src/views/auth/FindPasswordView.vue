<template>
  <div class="header px-4 py-2 border rounded shadow-sm">비밀번호 찾기</div>
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
            <div class="mb-4">
              <label for="email" class="form-label">Email</label>
              <input
                type="email"
                class="form-control"
                id="email"
                placeholder="Ex - example@naver.com"
              />
            </div>
            <button
              type="button"
              class="d-grid col-12 btn btn-primary text-truncate"
            >
              비밀번호 찾기
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.header {
  top: 25px;
  font-size: 2em;
  position: absolute;
  z-index: 1;
  background: white;
}
</style>
