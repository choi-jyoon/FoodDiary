// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import UserRegister from '../components/UserRegister.vue';
import Home from "../components/Home.vue";

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
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
