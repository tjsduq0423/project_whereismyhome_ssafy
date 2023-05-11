import { ref } from 'vue';
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', () => {
  const memberId = ref('');
  function login() {}

  return { memberId, login };
});
