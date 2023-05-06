<template>
  <nav aria-label="Search results pages" class="mt-3">
    <ul class="pagination justify-content-center">
      <li class="page-item">
        <a class="page-link" href="#">Previous</a>
      </li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
  </nav>
</template>

<script setup>
import { computed, ref } from 'vue';
const props = defineProps({
  totalCount: Number, // 페이지네이션 할 요소의 전체 개수
  limit: Number, // 한 페이지 몇 개의 요소를 보여줄 건가?
  pageCount: Number, // 페이지네이션 바의 크기
});
const currentPage = ref(1);
const totalPage = Math.ceil(props.totalCount / props.limit);

const pageGroup = computed(() => {
  return Math.ceil(currentPage.value / props.pageCount);
});
const lastNumber = computed(() => {
  return pageGroup.value * props.pageCount < totalPage
    ? pageGroup.value * props.pageCount
    : totalPage;
});
const fistNumber = computed(() => {
  return lastNumber.value - (props.pageCount - 1);
});
</script>

<style scoped></style>
