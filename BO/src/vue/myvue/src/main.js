// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from "./store";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";



Vue.config.productionTip = false
Vue.use(ElementUI);
global.axios = axios; // 设置一个全局axios便于调用
global.store = store;



axios.interceptors.response.use(
  function(response) {
    switch (response.data.code) {
      case 50000: Vue.prototype.$message(response.data.message);

    }
    return response;
  },
  function(error) {
    return Promise.reject(error);
  }
);
/* eslint-disable no-new */
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
