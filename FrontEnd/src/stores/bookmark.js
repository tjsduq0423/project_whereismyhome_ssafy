import { ref } from 'vue';
import { defineStore } from 'pinia';
import { getBookmark } from '@/api/bookmark';

export const useBookmarkStore = defineStore('bookmark', () => {
  const bookmarkList = ref([]);
  const setBookmarkList = async memberId => {
    const response = await getBookmark(memberId);
    bookmarkList.value = [...response.data];
  };
  // const deleteBookmark = async (memberId, aptCode) => {
  //   await deleteBookmark(memberId, aptCode);
  // };
  // const addBookmark = async (memberId, aptCode) => {
  //   await addBookmark(memberId, aptCode);
  // };
  return { bookmarkList, setBookmarkList };
});
