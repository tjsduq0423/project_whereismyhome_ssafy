<template>
  <div>
    <Suspense>
      <InfinityScroll></InfinityScroll>
      <template #fallback>
        <p>Loading...</p>
      </template>
    </Suspense>
  </div>
</template>

<script setup>
import InfinityScroll from '@/components/AppInfinityScroll.vue';
</script>

<style lang="scss" scoped></style>
