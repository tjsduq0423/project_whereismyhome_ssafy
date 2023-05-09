import axios from 'axios';

const instance = axios.create({
  baseURL: import.meta.env.VITE_APP_API_URL,
  withCredentials: true,
});

instance.interceptors.request.use(config => {
  return config;
});

instance.interceptors.response.use(response => {
  return response;
});

export default instance;
