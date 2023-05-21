import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useSearchStore = defineStore('search', () => {
  const showSearchList = ref(false);
  // 지하철
  const subwayList = ref(null);

  // 아파트
  const aptList = ref(null);
  // const setAptList = async searchInput => {
  //   const response = await getAptList(searchInput);
  //  response.data 비었는지 확인해야함.
  //   aptList.value = [...response.date];
  // };
  // 구
  const gugunList = ref(null);

  const selectedSearchInput = ref(null);

  return { showSearchList, subwayList, aptList, gugunList, selectedSearchInput };
});
