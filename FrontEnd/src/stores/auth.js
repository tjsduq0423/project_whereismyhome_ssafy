import { ref, watch } from 'vue';
import { defineStore } from 'pinia';
import { login, logout } from '../api/member';

export const useAuthStore = defineStore('auth', () => {
  const userInfo = ref(null);
  if (localStorage.getItem('userInfo')) {
    userInfo.value = JSON.parse(localStorage.getItem('userInfo'));
  }
  watch(
    userInfo,
    userValue => {
      localStorage.setItem('userInfo', JSON.stringify(userValue));
    },
    { deep: true },
  );

  const validateMember = async (id, password) => {
    const response = await login(id, password);
    userInfo.value = { ...response.data };
  };

  const invalidateMember = async () => {
    await logout();
    localStorage.clear();
    userInfo.value = null;
  };

  return { userInfo, validateMember, invalidateMember };
});
