import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useSideBarStore = defineStore('sideBar', () => {
  const isShow = ref(false);
  const aptInfo = ref(null);
  const aptDealInfo = ref(null);
  const aptRankInfo = ref(null);
  return { isShow, aptInfo, aptDealInfo, aptRankInfo };
});
