import { ref } from 'vue';
import { defineStore } from 'pinia';
import { getBookmark, deleteBookmark, addBookmark } from '@/api/bookmark';

export const useBookmarkStore = defineStore('bookmark', () => {
  const bookmarkList = ref([]);
  const centerlatlng = ref([36.35536036402348, 127.29840381673439]);
  const setBookmarkList = async memberId => {
    const response = await getBookmark(memberId);
    bookmarkList.value = [...response.data];
    const { lat, lng } = bookmarkList.value[0];
    centerlatlng.value = [lat, lng];
  };
  const dBookmark = async (memberId, aptCode) => {
    await deleteBookmark(memberId, aptCode);
  };
  const aBookmark = async (memberId, aptCode) => {
    await addBookmark(memberId, aptCode);
  };
  return { bookmarkList, setBookmarkList, dBookmark, aBookmark, centerlatlng };
});
