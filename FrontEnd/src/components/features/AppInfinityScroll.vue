<template>
  <div>
    <ul ref="listEl">
      <li v-for="(item, idx) in items" :key="idx"></li>
      <p v-show="fetchingData"></p>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useInfiniteScroll } from '@vueuse/core';
const props = defineProps({
  getItems: {
    type: Object,
    required: true,
  },
});

const listEl = ref(null);
const showLength = 8;
// try catch  + 딜레이 시에?
const fetchingData = ref(null);
const items = ref([]);
items.value.push(await props.getItems(showLength, 0));

const getMoreItem = async () => {
  fetchingData.value = true;
  const newItmes = await props.getItems(showLength, items.value.length);
  items.value.push(...newItmes);
  fetchingData.value = null;
};

useInfiniteScroll(
  listEl,
  async () => {
    await getMoreItem();
  },
  { distance: 10 },
);
</script>

<style lang="scss" scoped>
ul {
  background-color: #41b480;
  list-style: none;
  max-height: 600px;
  width: 500px;
  overflow: scroll;
  padding: 1rem 2rem;
  box-shadow: 0 20px 25px -5px rgb(0 0 0 / 0.1), 0 8px 10px -6px rgb(0 0 0 / 0.1);
}
li {
  padding: 12px 0;
  color: #fff;
  font-size: 18px;
}
</style>
