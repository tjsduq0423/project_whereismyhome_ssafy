import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useAptInfoStore = defineStore('aptInfo', () => {
  const aptInfo = ref(null);
  const aptRankInArea = ref(null);
  const aptDealInfo = ref([]);
  return { aptInfo, aptRankInArea, aptDealInfo };
});
