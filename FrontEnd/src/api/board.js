import http from '@/api/http';
const boardApi = {
  board: theme => http.post('/board/articles', { theme }),
  myboard: id => http.post('/board/myboard', { id }),
  deletearticle: boardId => http.post('/board/deletearticle', { boardId }),
};

export default boardApi;
