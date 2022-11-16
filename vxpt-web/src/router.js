import { createRouter, createWebHashHistory } from "vue-router"

const login = () => import('./components/Login.vue')

const routes = [
    {path: '/login', name: "login", component: login}
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

