// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from './utils/intercept'
import store from './store'

axios.defaults.baseURL = "http://localhost:8090"
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
//挂载axios
Vue.prototype.$http = axios;
//设置访问根路径
import {publicFunction} from './share/timeFormate'

Vue.mixin(publicFunction);
import {Message} from 'element-ui';


Vue.prototype.$store = store
import VueQuillEditor from 'vue-quill-editor'

Vue.use(VueQuillEditor);
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'

Vue.use(ElementUI);

Vue.config.productionTip = false
import bus from '../src/utils/bus'

Vue.prototype.$myBus = bus;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})


/*路由卫士*/
router.beforeEach((to, from, next) => {
  // 如果没有token值 那么就跳转到'/login
  if (!localStorage.getItem("token")) {
    if (to.path === '/') {
      return next();
    }
    Message({
      message: '登录状态过期，请重新登录',
      type: 'warning'
    });
    return next('/');
  }
  if (store.getters.getIdentifier === 'student' && to.path.indexOf("Teacher") !== -1) {
    Message({
      message: '您无权访问该页面',
      type: 'warning'
    })
    next(from);
  }
  if (to.path === '/') {  //  如果用户访问的是登录页面 直接放行
    localStorage.clear();
    return next();
  }

  // 如果有token则放行
  next();
})


