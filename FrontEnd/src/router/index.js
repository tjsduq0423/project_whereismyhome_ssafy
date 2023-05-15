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
      meta: { requiredAuth: true },
      component: () => import('@/views/board/NoticeView.vue'),
    },
    {
      path: '/notice/create',
      name: 'NoticeCreate',
      meta: { requiredAuth: true },
      component: () => import('@/views/board/NoticeCreateView.vue'),
    },
    {
      path: '/notice/:id',
      name: 'NoticeDetail',
      meta: { requiredAuth: true },
      component: () => import('@/views/board/NoticeDetailView.vue'),
    },
    {
      path: '/notice/edit/:id',
      name: 'NoticeEdit',
      meta: { requiredAuth: true },
      component: () => import('@/views/board/NoticeEditView.vue'),
    },
    {
      path: '/qna',
      name: 'QnA',
      meta: { requiredAuth: true },
      component: () => import('@/views/board/QnAView.vue'),
    },
    {
      path: '/qna/create',
      name: 'QnACreate',
      meta: { requiredAuth: true },
      component: () => import('@/views/board/QnACreateView.vue'),
    },
    {
      path: '/qna/:id',
      name: 'QnADetail',
      meta: { requiredAuth: true },
      component: () => import('@/views/board/QnADetailView.vue'),
    },
    {
      path: '/qna/edit/:id',
      name: 'QnAEdit',
      meta: { requiredAuth: true },
      component: () => import('@/views/board/QnAEditView.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      component: NotFound,
    },
  ],
});
import { useAlert } from '@/composables/alert';
import { useAuthStore } from '@/stores/auth';
import { storeToRefs } from 'pinia';
const authStore = useAuthStore();
const { userInfo } = storeToRefs(authStore);

const { vAlert, vSuccess } = useAlert();

router.beforeEach(to => {
  if (to.meta.requiredAuth && userInfo === null) {
    vAlert('로그인 해주세요!!!');
    vSuccess();
    return { name: 'Login' };
  }
  // 알람. 권한 ,리다이렉트
});
export default router;
