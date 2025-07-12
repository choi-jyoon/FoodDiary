// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import UserRegister from '../components/UserRegister.vue';
import MainHome from "../components/MainHome.vue";
import UserLogin from "../components/UserLogin.vue"

const routes = [
    {
        path: '/',
        name: 'MainHome',
        component: MainHome,
        meta: { requiresAuth: true}
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
    }
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
