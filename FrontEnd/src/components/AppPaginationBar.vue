<template>
  <nav aria-label="Page navigation" class="mt-2">
    <ul class="pagination justify-content-center m-0">
      <li class="page-item" :class="isPrevPage">
        <a
          class="page-link"
          aria-label="Previous"
          href="#"
          @click.prevent="$emit('page', currentPage - 1)"
        >
          <span aria-hidden="true">&lt;</span>
        </a>
      </li>
      <li
        v-for="page in PaginationPageList"
        :key="page"
        class="page-item"
        :class="{ active: currentPage === page }"
      >
        <a class="page-link" href="#" @click.prevent="$emit('page', page)">{{
          page
        }}</a>
      </li>
      <li class="page-item" :class="isNextPage">
        <a
          class="page-link"
          aria-label="Next"
          href="#"
          @click.prevent="$emit('page', currentPage + 1)"
        >
          <span aria-hidden="true">&gt;</span>
        </a>
      </li>
    </ul>
  </nav>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  currentPage: {
    type: Number,
    required: true,
  },
  totalContentCount: {
    type: Number,
    required: true,
  },
  showContentCount: {
    type: Number,
    required: true,
  },
  showPaginationBtnCount: {
    type: Number,
    default: 10,
  },
});
defineEmits(['page']);

const maxPage = Math.ceil(props.totalContentCount / props.showContentCount);
const PaginationPageList = computed(() => {
  const start =
    (Math.ceil(props.currentPage / props.showPaginationBtnCount) - 1) *
      props.showPaginationBtnCount +
    1;
  const end = Math.min(maxPage, start + props.showPaginationBtnCount - 1);
  return Array.from({ length: end - start + 1 }, (_, i) => start + i);
});

const isPrevPage = computed(() => ({ disabled: !(props.currentPage > 1) }));
const isNextPage = computed(() => ({
  disabled: !(props.currentPage < maxPage),
}));
</script>

<style scoped>
.pagination {
  --bs-pagination-color: black;
  --bs-pagination-active-bg: #198754;
  --bs-pagination-active-border-color: #198754;
}
</style>
