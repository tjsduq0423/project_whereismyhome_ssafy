import { ref } from 'vue';
import { defineStore } from 'pinia';
import { getSearchSubwayByInput, getSearchHouseByInput, getSearchGugunByInput } from '@/api/search';

export const useSearchStore = defineStore('search', () => {
  const sAptList = ref(null);
  const sGugunList = ref(null);
  const sSubwayList = ref(null);
  const showSearchList = ref(false);
  const selectedSearchInput = ref(null);

  const setAptList = async searchInput => {
    const response = await getSearchHouseByInput(searchInput);
    sAptList.value = [...response.data];
  };
  const setGugunList = async searchInput => {
    const response = await getSearchGugunByInput(searchInput);
    sGugunList.value = [...response.data];
  };
  const setSubwayList = async searchInput => {
    const response = await getSearchSubwayByInput(searchInput);
    sSubwayList.value = [...response.data];
  };

  return {
    showSearchList,
    sSubwayList,
    sAptList,
    sGugunList,
    selectedSearchInput,
    setAptList,
    setGugunList,
    setSubwayList,
  };
});
