import LoginView from "@/views/LoginView.vue";
import {createRouter, createWebHistory} from "vue-router";
import NotFound from "@/views/NotFound.vue";

const routes = [
    { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
    { path: '/login/:code', name: 'Login', component: LoginView}
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router