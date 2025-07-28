// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import UserRegister from '../components/UserRegister.vue';
import MainHome from "../components/MainHome.vue";
import UserLogin from "../components/UserLogin.vue";
import ProfileHome from "../components/ProfileHome.vue";
import ProfileEdit from "@/components/ProfileEdit.vue";
import RefrigeratorManage from "@/components/RefrigeratorManage.vue";
import IngredientCreate from "@/components/IngredientCreate.vue";

const routes = [
    {
        path: '/',
        name: 'MainHome',
        component: MainHome,
        meta: { requiresAuth: false}
    },
    {
        path: '/user/register',
        name: 'UserRegister',
        component: UserRegister,
        meta: { requiresAuth: false }
    },
    {
        path: '/user/login',
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
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem('token')

    if (to.meta.requiresAuth && !token) {
        next('/user/login')
    }
    else {
        next()
    }
})
export default router;
