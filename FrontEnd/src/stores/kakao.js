import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useKakaoStore = defineStore('kakao', () => {
  const kakao = ref(null);
  const changeState = obj => {
    kakao.value = obj;
  };

  return { kakao, changeState };
});
