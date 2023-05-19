import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useLoadingStore = defineStore('loading', () => {
  const loading = ref(false);

  let timer;
  const vLoading = (delay = 500) => {
    loading.value = true;

    if (timer) {
      clearTimeout(timer);
    }

    timer = setTimeout(() => {
      loading.value = false;
    }, delay);
  };

  return {
    loading,
    vLoading,
  };
});
