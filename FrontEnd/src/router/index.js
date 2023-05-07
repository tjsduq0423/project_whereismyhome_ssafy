import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import NotFound from '@/views/NotFoundView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: HomeView,
    },
    {
      path: '/aptInfo',
      name: 'AptInfo',
      component: () => import('@/views/main/AptInfoView.vue'),
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/auth/LoginView.vue'),
    },
    {
      path: '/findPassword',
      name: 'FindPassword',
      component: () => import('@/views/auth/FindPasswordView.vue'),
    },
    {
      path: '/signUp',
      name: 'SignUp',
      component: () => import('@/views/auth/SignUpView.vue'),
    },
    {
      path: '/notice',
      name: 'Notice',
      component: () => import('@/views/board/NoticeView.vue'),
    },
    {
      path: '/notice/:id',
      name: 'NoticeDetail',
      component: () => import('@/views/board/NoticeDetailView.vue'),
    },
    {
      path: '/qna',
      name: 'QnA',
      component: () => import('@/views/board/QnAView.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      component: NotFound,
    },
  ],
});

export default router;
