<template>
	<button @click="printEventInfo('hello', $event)">inline event handler</button>
	<hr />
	<input type="text" @keyup="onKeyupHandler" />
</template>

<script setup>
function printEventInfo(msg, event) {
	console.log('message : ', msg);
	console.log('event.target', event.target);
}
function onKeyupHandler(event) {
	console.log('event.key', event.key);
}
</script>

<style lang="scss" scoped></style>
