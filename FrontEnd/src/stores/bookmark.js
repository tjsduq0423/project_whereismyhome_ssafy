import { ref } from 'vue';
import { defineStore } from 'pinia';
import { getBookmark, deleteBookmark, addBookmark } from '@/api/bookmark';

export const useBookmarkStore = defineStore('bookmark', () => {
  const bookmarkList = ref([]);
  const setBookmarkList = async memberId => {
    const response = await getBookmark(memberId);
    bookmarkList.value = [...response.data];
  };
  const dBookmark = async (memberId, aptCode) => {
    await deleteBookmark(memberId, aptCode);
  };
  const aBookmark = async (memberId, aptCode) => {
    await addBookmark(memberId, aptCode);
  };
  return { bookmarkList, setBookmarkList, dBookmark, aBookmark };
});
