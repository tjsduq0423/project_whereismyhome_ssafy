<template>
  <Transition
    enter-active-class="animate__animated animate__fadeIn"
    leave-active-class="animate__animated animate__fadeOut"
  >
    <div class="app-loading" v-show="loading">
      <div class="alert alert-success d-flex align-items-center justify-content-center" role="alert">
        <div class="spinner-border" role="status" style="height: 6vh; width: 6vh; border-width: 0.4rem">
          <span class="visually-hidden">Loading...</span>
        </div>
        <div class="ms-3">Marker Loading ...</div>
      </div>
    </div>
  </Transition>
</template>

<script setup>
import { useLoading } from '@/composables/loading';
const { loading } = useLoading();
</script>

<style scoped>
.app-loading {
  position: absolute;
  bottom: 50%;
  right: 50%;
  font-size: 1.5rem;
  transform: translate(50%, 50%);
}
</style>
