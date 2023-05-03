import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import NotFound from '@/views/NotFoundView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/aptInfo',
      name: 'aptInfo',
      component: () => import('@/views/main/AptInfoView.vue'),
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/auth/LoginView.vue'),
    },
    {
      path: '/findPassword',
      name: 'findPassword',
      component: () => import('@/views/auth/FindPasswordView.vue'),
    },
    {
      path: '/signUp',
      name: 'signUp',
      component: () => import('@/views/auth/SignUpView.vue'),
    },
    {
      path: '/notice',
      name: 'notice',
      component: () => import('@/views/main/NoticeView.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      component: NotFound,
    },
  ],
});

export default router;
