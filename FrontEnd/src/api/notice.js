import { notice } from '.';

export const putNotice = (id, title, content) =>
  notice.put('', { id, title, content });

export const registNotice = (title, content) =>
  notice.post('/regist', { title, content });

export const getNoticeAll = () => notice.get('/list');

export const getNoticeById = id => notice.get(`/${id}`);
