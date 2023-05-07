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
      path: '/notice/create',
      name: 'NoticeCreate',
      component: () => import('@/views/board/NoticeCreateView.vue'),
    },
    {
      path: '/notice/:id',
      name: 'NoticeDetail',
      component: () => import('@/views/board/NoticeDetailView.vue'),
    },
    {
      path: '/notice/edit/:id',
      name: 'NoticeEdit',
      component: () => import('@/views/board/NoticeEditView.vue'),
    },
    {
      path: '/qna',
      name: 'QnA',
      component: () => import('@/views/board/QnAView.vue'),
    },
    {
      path: '/qna/create',
      name: 'QnACreate',
      component: () => import('@/views/board/QnACreateView.vue'),
    },
    {
      path: '/qna/:id',
      name: 'QnADetail',
      component: () => import('@/views/board/QnADetailView.vue'),
    },
    {
      path: '/qna/edit/:id',
      name: 'QnAEdit',
      component: () => import('@/views/board/QnAEditView.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      component: NotFound,
    },
  ],
});

export default router;
