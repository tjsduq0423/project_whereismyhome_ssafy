import { bookmark } from '.';

export const bookmarkUpdate = (id, addList, deleteList) =>
  bookmark.post('/update', { id, addList, deleteList });

export const getBookmark = memberId => bookmark.get(`/${memberId}`);

export const addBookmark = (memberId, aptCode) => bookmark.post('/add', { memberId, aptCode });

export const deleteBookmark = (memberId, aptCode) =>
  bookmark.post('/delete', { memberId, aptCode });
