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
      meta: { requiresAuth: false },
      component: () => import('@/views/main/AptInfoView.vue'),
    },
    {
      path: '/login',
      name: 'Login',
      meta: { requiresAuth: false },
      component: () => import('@/views/auth/LoginView.vue'),
    },
    {
      path: '/findPassword',
      name: 'FindPassword',
      meta: { requiresAuth: false },
      component: () => import('@/views/auth/FindPasswordView.vue'),
    },
    {
      path: '/signUp',
      name: 'SignUp',
      meta: { requiresAuth: false },
      component: () => import('@/views/auth/SignUpView.vue'),
    },
    {
      path: '/notice',
      name: 'Notice',
      meta: { requiresAuth: true },
      component: () => import('@/views/board/NoticeView.vue'),
    },
    {
      path: '/notice/create',
      name: 'NoticeCreate',
      meta: { requiresAuth: true, isAdmin: true },
      component: () => import('@/views/board/NoticeCreateView.vue'),
    },
    {
      path: '/notice/:id',
      name: 'NoticeDetail',
      meta: { requiresAuth: true },
      component: () => import('@/views/board/NoticeDetailView.vue'),
    },
    {
      path: '/notice/edit/:id',
      name: 'NoticeEdit',
      meta: { requiresAuth: true, isAdmin: true },
      component: () => import('@/views/board/NoticeEditView.vue'),
    },
    {
      path: '/qna',
      name: 'QnA',
      meta: { requiresAuth: true },
      component: () => import('@/views/board/QnAView.vue'),
    },
    {
      path: '/qna/create',
      name: 'QnACreate',
      meta: { requiresAuth: true },
      component: () => import('@/views/board/QnACreateView.vue'),
    },
    {
      path: '/qna/:id',
      name: 'QnADetail',
      meta: { requiresAuth: true },
      component: () => import('@/views/board/QnADetailView.vue'),
    },
    {
      path: '/qna/edit/:id',
      name: 'QnAEdit',
      meta: { requiresAuth: true },
      component: () => import('@/views/board/QnAEditView.vue'),
    },
    {
      path: '/myPage',
      name: 'MyPage',
      meta: { requiresAuth: true },
      component: () => import('@/views/main/MyPageView.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      component: NotFound,
      meta: { requiresAuth: false },
    },
  ],
});
import { useAlert } from '@/composables/alert';
import { useAuthStore } from '@/stores/auth';
import { storeToRefs } from 'pinia';

router.beforeEach(to => {
  const authStore = useAuthStore();
  const { vAlert } = useAlert();
  const { userInfo } = storeToRefs(authStore);
  if (to.meta.requiresAuth && userInfo.value === null) {
    vAlert('로그인 해주세요!!!');
    return { name: 'Login' };
  }
  if (to.meta.isAdmin && userInfo.value.name !== '관리자') {
    vAlert('권한이 없습니다!!!');
    return { name: 'Home' };
  }
});

export default router;
