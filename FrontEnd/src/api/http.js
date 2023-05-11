import axios from 'axios';

const instance = axios.create({
  baseURL: import.meta.env.VITE_APP_API_URL,
  responseType: 'json',
  withCredentials: true,
});

instance.interceptors.request.use(
  config => {
    // 요청 보내기 전에 제어할 부분
    return config;
  },
  error => {
    // 요청 시 에러 처리
    return Promise.reject(error);
  },
);

instance.interceptors.response.use(
  response => {
    // 응답이 왔을 때 받기 전에 제어할 부분
    return response;
  },
  error => {
    // 응답 에러시 선 처리 할 부분.
    return Promise.reject(error);
  },
);

export default instance;
