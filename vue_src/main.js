import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/global.css'
import axios from 'axios'
import qs from 'qs'



Vue.prototype.$qs = qs


Vue.prototype.$ajax = axios

axios.defaults.baseURL='/api'


Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
