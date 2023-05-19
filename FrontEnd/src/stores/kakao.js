import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useKakaoStore = defineStore('kakao', () => {
  const kakao = ref(null);
  const mapCenterLatLng = ref([36.35536036402348, 127.29840381673439]);

  const changeState = obj => {
    kakao.value = obj;
  };

  return { kakao, mapCenterLatLng, changeState };
});
