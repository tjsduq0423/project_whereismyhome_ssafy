<template>
  <div class="app-alert">
    <TransitionGroup
      appear
      mode="out-in"
      enter-active-class="animate__animated animate__bounceInDown animate__faster"
      leave-active-class="animate__animated animate__zoomOutRight"
    >
      <div
        v-for="({ message, type }, index) in alerts"
        :key="index"
        class="alert"
        :class="typeStyle(type)"
        role="alert"
      >
        <i :class="iconStyle(type)"></i>
        {{ message }}
      </div>
    </TransitionGroup>
  </div>
</template>

<script setup>
import { useAlert } from '@/composables/alert';
const { alerts } = useAlert();
const typeStyle = type => (type === 'error' ? 'alert-danger' : 'alert-success');
const iconStyle = type =>
  type === 'error'
    ? 'bi bi-exclamation-triangle-fill'
    : 'bi bi-check-circle-fill';
</script>

<style scoped>
.app-alert {
  position: fixed;
  top: 68px;
  right: 16px;
  font-size: 1.5rem;
}
/* .slide-fade-enter-active {
  transition: all 0.3s ease-out;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
} */
</style>
