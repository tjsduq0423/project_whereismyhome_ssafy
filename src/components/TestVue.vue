<template>
	<button @click="incresement">counter : {{ counter }}</button>
</template>

<script setup>
import { ref } from 'vue';

const counter = ref(0);
function incresement() {
	counter.value++;
}
</script>

<style lang="scss" scoped></style>
