import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useSideBarStore = defineStore('sideBar', () => {
  const isShow = ref(false);
  const changeState = () => {
    isShow.value = !isShow.value;
  };
  const resetIsShow = () => {
    isShow.value = false;
  };
  return { isShow, changeState, resetIsShow };
});
