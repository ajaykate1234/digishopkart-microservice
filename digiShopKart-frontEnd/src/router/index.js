import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DiscountView from '@/views/discount/DiscountView.vue'
import AddDiscountCoupons from '@/views/discount/AddDiscountCoupons.vue'
import EditDiscountCoupon from '@/views/discount/EditDiscountCoupon.vue'
import ProductView from '@/views/product/ProductView.vue'
import AddProduct from '@/views/product/AddProduct.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },

    // Discounts
    {
      path: '/discount',
      name: 'DiscountView',
      component: DiscountView,
    },
    {
      path: '/discount/create',
      name: 'AddDiscountCoupons',
      component: AddDiscountCoupons,
    },
    {
      path: '/discount/:id/edit',
      name: 'EditDiscountCoupon',
      component: EditDiscountCoupon,
    },

    //products
    {
      path:'/products',
      name:'ProductView',
      component: ProductView
    },
    {
      path: '/product/add',
      name: 'AddProduct',
      component: AddProduct
    }
  ],
})

export default router
