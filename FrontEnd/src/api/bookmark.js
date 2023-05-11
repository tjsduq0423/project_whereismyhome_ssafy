import { bookmark } from '.';

export const bookmarkUpdate = (id, addList, deleteList) =>
  bookmark.post('/update', { id, addList, deleteList });

export const getBookmark = memberId => bookmark.get(`/${memberId}`);
