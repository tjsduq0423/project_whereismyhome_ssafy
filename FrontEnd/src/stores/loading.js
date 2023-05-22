import { defineStore } from 'pinia';
import { ref } from 'vue';

export const useLoadingStore = defineStore('loading', () => {
  const loading = ref(false);

  let timer;
  const vLoading = () => {
    loading.value = true;

    if (timer) {
      clearTimeout(timer);
    }

    timer = setTimeout(() => {
      loading.value = false;
    }, 1000);
  };

  return {
    loading,
    vLoading,
  };
});
