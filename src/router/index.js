import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import Home from "@/views/Home";
import AddCommodity from "@/views/AddCommodity";
import CheckCommodity from "@/views/CheckCommodity";
import DeleteCommdity from "@/views/DeleteCommdity";
import UserInfo from "@/views/UserInfo";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    hidden: true
  },
  {
    path: '/home',
    name: '商品管理',
    component: Home,
    children:[
      {
        path: '/addCommodity',
        name: '添加商品',
        component: AddCommodity,
      },
      {
        path: '/checkCommodity',
        name: '商品审核',
        component: CheckCommodity
      },
      {
        path: '/deleteCommodity',
        name: '商品列表',
        component: DeleteCommdity
      }
    ]
  },
  {
    path: '/home',
    name: '用户管理',
    component: Home,
    children:[
      {
        path: '/userInfo',
        name: '用户信息',
        component: UserInfo
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
