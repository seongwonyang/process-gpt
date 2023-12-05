import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../pages/Index.vue'),
    },
    {
      path: '/accounts',
      component: () => import('../views/components/ui/AccountGrid.vue'),
    },
    {
      path: '/items',
      component: () => import('../views/components/ui/ItemGrid.vue'),
    },
    {
      path: '/purchases',
      component: () => import('../views/components/ui/PurchaseGrid.vue'),
    },
  ],
})

export default router;
