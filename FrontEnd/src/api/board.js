import http from '@/api/http';
const boardApi = {
  board: theme => http.post('/board/articles', { theme }),
  myboard: (id, title, content) =>
    http.post(`/board/myboard`, { id, title, content }),
  deletearticle: boardId => http.post('/board/deletearticle', { boardId }),
};

export default boardApi;
