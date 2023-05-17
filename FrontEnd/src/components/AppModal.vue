<template>
  <div class="root row m-0">
    <Teleport to="body">
      <div class="modal" v-if="isOpen">
        <div class="container">
          <slot></slot>
          <div class="row">
            <button class="btn btn-success text-truncate" @click="$emit('closeModal')">닫기</button>
          </div>
        </div>
      </div>
    </Teleport>
  </div>
</template>

<script setup>
defineProps({
  isOpen: {
    type: Boolean,
    required: true,
    default: false,
  },
});
defineEmits(['closeModal']);
</script>

<style lang="scss" scoped>
.root {
  position: relative;
}
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
  > div {
    background-color: #fff;
    padding: 50px;
    border-radius: 10px;
  }
}
</style>
