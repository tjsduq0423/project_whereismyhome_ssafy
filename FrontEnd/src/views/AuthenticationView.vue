<template>
  <div class="row mt-5 text-center">
    <div class="col">
      <div class="card">
        <div class="card-body">
          <h5 class="card-title">Contact Us</h5>
          <form>
            <div class="mb-3">
              <label for="name" class="form-label">Name</label>
              <input
                type="text"
                class="form-control"
                id="name"
                placeholder="Enter your name"
              />
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Email address</label>
              <input
                type="email"
                class="form-control"
                id="email"
                placeholder="name@example.com"
              />
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
