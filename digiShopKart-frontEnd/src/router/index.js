import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DiscountView from '@/views/discount/DiscountView.vue'
import AddDiscountCoupons from '@/views/discount/AddDiscountCoupons.vue'
import EditDiscountCoupon from '@/views/discount/EditDiscountCoupon.vue'
import ProductView from '@/views/product/ProductView.vue'
import AddProduct from '@/views/product/AddProduct.vue'
import EditProduct from '@/views/product/EditProduct.vue'
import CustomerView from '@/views/customer/CustomerView.vue'
import ShoppingDashboard from '@/views/shopping/ShoppingDashboard.vue'
import LeftSideBar from '@/components/LeftSideBar.vue'
import RightSideBar from '@/components/RightSideBar.vue'
import OpenKart from '@/views/kart/OpenKart.vue'
import MainContainer from '@/components/MainContainer.vue'
import LoginComponent from '@/components/LoginComponent.vue'
import RegisterComponent from '@/components/RegisterComponent.vue'
import InterviewPractice from '@/components/InterviewPractice.vue'
import RazorpayPayment from '@/components/RazorpayPayment.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path:'/leftSideBar',
      name:'LeftSideBar',
      component: LeftSideBar
    },
    {
      path:'/rightSideBar',
      name:'RightSideBar',
      component: RightSideBar,
    },
    {
      path:'/mainConainer',
      name:MainContainer,
      component: MainContainer
    },
    {
      path:'/openKart',
      name:'OpenKart',
      component: OpenKart,
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
    },
    {
      path: '/product/:id/edit',
      name: 'EditProduct',
      component: EditProduct
    },

    // Customers
    {
      path: '/customers',
      name: 'CustomerView',
      component: CustomerView
    },

    // Shopping tabs
    {
      path:'/digi/shopping',
      name:'ShoppigDashboard',
      component:ShoppingDashboard
    },
    {
      path: '/digi/userLogin',
      name:'UserLogin',
      component: LoginComponent
    },
    {
      path:'/digi/registerUser',
      name:'RegisterUser',
      component: RegisterComponent
    },

    {
      path:'/digi/interviewPractice',
      name: 'InterviewPractice',
      component: InterviewPractice
    },

    {
      path: '/digi/razorpay',
      name: RazorpayPayment,
      component: RazorpayPayment
    }
  ],
})

export default router
