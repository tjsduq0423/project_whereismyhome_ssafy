import { board } from '.';

export const putBoard = (id, title, content) =>
  board.put('/update', { id, title, content });

export const registBoard = (memberId, title, content) =>
  board.post('/regist', { memberId, title, content });

export const answerBoard = (id, responseContent) =>
  board.post('/answer', { id, responseContent });

export const getBoardById = memberId => board.get(`/${memberId}`);

export const getBoardList = () => board.get('/list');

export const getBoardDetail = id => board.get(`/detail/${id}`);

export const deleteBoard = id => board.delete(`/${id}`);
