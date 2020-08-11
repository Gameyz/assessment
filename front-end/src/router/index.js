import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "../views/Index";
import FunctionPage from "../views/FunctionPage";
import LogPage from "../views/LogPage";
import StaticPage from "../views/StaticPage";

Vue.use(VueRouter)

const routes = [
    // {
    //   path: '/',
    //   name: 'Index',
    //   redirect: {name: "Blogs"}
    // },
    {
        path: '/',
        name: 'Index',
        component: Index
    }, {
        path: '/FunctionPage',
        name: 'FunctionPage',
        component: FunctionPage
    }, {
        path: '/LogPage',
        name: 'LogPage',
        component: LogPage
    }, {
        path: '/StaticPage',
        name: 'StaticPage',
        component: StaticPage
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
