// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import UserRegister from '../components/UserRegister.vue';
import Home from "../components/Home.vue";
import UserLogin from "../components/UserLogin.vue"

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
    },
    {
        path: '/user/register',
        name: 'UserRegister',
        component: UserRegister,
    },
    {
        path: '/user/login',
        name: 'UserLogin',
        component: UserLogin
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
