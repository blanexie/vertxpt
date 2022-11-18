import {createRouter, createWebHashHistory} from "vue-router"

const login = () => import('../../components/Login.vue')

const home = () => import('../../components/Home.vue')

const routes = [
    {path: '/login', name: "login", component: login},
    {path: '/home', name: "home", component: home}
]

export const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})

