import axios from 'axios';

const create = (baseURL, options) => axios.create({ baseURL, ...options });

export const notice = create(`${import.meta.env.VITE_APP_API_URL}/notice`, {
  withCredentials: true,
});

export const board = create(`${import.meta.env.VITE_APP_API_URL}/board`, {
  withCredentials: true,
});

export const member = create(`${import.meta.env.VITE_APP_API_URL}/member`, {
  withCredentials: true,
});

export const bookmark = create(`${import.meta.env.VITE_APP_API_URL}/bookmark`, {
  withCredentials: true,
});

export const amen = create(`${import.meta.env.VITE_APP_API_URL}/amen`, {
  withCredentials: true,
});

export const info = create(`${import.meta.env.VITE_APP_API_URL}/info`, {
  withCredentials: true,
});

export const search = create(`${import.meta.env.VITE_APP_API_URL}/search`, {
  withCredentials: true,
});

// development : http://localhost:8080/posts/
// production : http://productionDomainURL/posts/

// instance.interceptors.request.use(
//   config => {
//     // 요청 보내기 전에 제어할 부분
//     return config;
//   },
//   error => {
//     // 요청 시 에러 처리
//     return Promise.reject(error);
//   },
// );

// instance.interceptors.response.use(
//   response => {
//     // 응답이 왔을 때 받기 전에 제어할 부분
//     return response;
//   },
//   error => {
//     // 응답 에러시 선 처리 할 부분.
//     return Promise.reject(error);
//   },
// );
