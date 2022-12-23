import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import {postRequest} from "./utils/api";
import {putRequest} from "./utils/api";
import {getRequest} from "./utils/api";
import {deleteRequest} from "./utils/api";

Vue.config.productionTip = false
Vue.prototype.postRequest=postRequest
Vue.prototype.putRequest=putRequest;
Vue.prototype.getRequest=getRequest;
Vue.prototype.deleteRequest=deleteRequest;

/// 全局前置守卫
// to: Route: 即将要进入的目标 路由对象
// from: Route: 当前导航正要离开的路由
// next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。
router.beforeEach(((to, from, next) => {
  if (window.sessionStorage.getItem('tokenStr')) {
    //判断用户信息是否存在
    if (!window.sessionStorage.getItem('user')) {
      return getRequest('/user/info').then(resp => {
        if (resp) {
          //存入用户信息
          let userinfo = resp.obj;
          window.sessionStorage.setItem('user', JSON.stringify(userinfo));
          next();
        }
      });
    }
    next();
  } else {
    //若未登录 跳转登录页
    if (to.path == '/') {
      next();
    } else {
      //若直接访问的是某个页面，则重定向页面
      next('/?redirect='+to.path);
    }
  }
}));

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
