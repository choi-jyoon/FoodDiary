// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import UserRegister from '../components/UserRegister.vue';
import MainHome from "../components/MainHome.vue";
import UserLogin from "../components/UserLogin.vue";
import ProfileHome from "../components/ProfileHome.vue";
import ProfileEdit from "@/components/ProfileEdit.vue";
import RefrigeratorManage from "@/components/RefrigeratorManage.vue";
import IngredientCreate from "@/components/IngredientCreate.vue";
import MainCalendar  from "@/components/MainCalendar.vue";
import DietCalendar from "@/components/DietCalendar.vue";
import DietWrite from "@/components/DietWrite.vue";
import CustomDietWrite from '@/components/CustomDietWrite.vue';

const routes = [
    {
        path: '/',
        name: 'MainHome',
        component: MainHome,
        meta: { requiresAuth: false}
    },
    {
        path: '/register',
        name: 'UserRegister',
        component: UserRegister,
        meta: { requiresAuth: false }
    },
    {
        path: '/login',
        name: 'UserLogin',
        component: UserLogin,
        meta: { requiresAuth: false }
    },
    {
        path: '/profile',
        name: 'ProfileHome',
        component: ProfileHome,
        meta: { requiresAuth: true}
    },
    {
        path: '/profile/edit',
        name: 'ProfileEdit',
        component: ProfileEdit,
        meta: { requiresAuth: true}
    },
    {
        path: '/refrigerator',
        name: 'RefrigeratorManage',
        component: RefrigeratorManage,
        meta: { requiresAuth: true}
    },
    {
        path: '/refrigerator/create',
        name: 'IngredientCreate',
        component: IngredientCreate,
        meta: { requiresAuth: true}
    },
    {
        path: '/refrigerator/update',
        name: 'IngredientUpdate',
        component: IngredientCreate,
        meta: { requiresAuth: true}
    },
    {
        path: "/calendar",
        name: 'MainCalendar',
        component: MainCalendar,
        meta: {requiresAuth: false}
    },
    {
        path: "/diet/:date",
        name: 'DietCalendar',
        component: DietCalendar,
        meta: {requiresAuth: false}
    },
    {
        path: "/diet/:date/write",
        name: 'DietWrite',
        component: DietWrite,
        meta: {requiresAuth: false}
    },
    {
        path: "/diet/:date/write/custom",
        name: 'CustomDietWrite',
        component: CustomDietWrite,
        meta: {requiresAuth: false}
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')

    if (to.meta.requiresAuth && !token) {
        next('/login')
    }
    else {
        next()
    }
})
export default router;
