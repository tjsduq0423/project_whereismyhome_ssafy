<template>
  <Teleport to="body">
    <div class="modal" v-if="isOpen">
      <div class="container">
        <slot></slot>
      </div>
    </div>
  </Teleport>
</template>

<script setup>
defineProps({
  isOpen: {
    type: Boolean,
    required: true,
    default: false,
  },
});
</script>

<style lang="scss" scoped>
.modal {
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.1);
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #ccc;
  box-shadow: 1.5rem 1.5rem 0.75rem rgba(0, 0, 0, 0.1);
}
</style>
