<template>
  <div class="header px-4 py-2 border rounded shadow-sm">회원가입</div>
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
            <div class="mb-3">
              <label for="name" class="form-label">Name</label>
              <input
                type="text"
                class="form-control"
                id="name"
                placeholder="Enter your Name"
              />
            </div>
            <div class="mb-3">
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
              class="d-grid col-12 btn btn-primary text-truncate mt-4"
            >
              회원가입
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
