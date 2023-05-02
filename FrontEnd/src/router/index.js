import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import NotFound from '@/views/NotFound.vue';

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
      component: () => import('@/views/AptInfoView.vue'),
    },
    {
      path: '/auth',
      name: 'auth',
      component: () => import('@/views/AuthenticationView.vue'),
    },

    {
      path: '/:pathMatch(.*)*',
      component: NotFound,
    },
  ],
});

export default router;
