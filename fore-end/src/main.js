import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import element from './plugins/element'
import axios from 'axios'

//npm i vue-table-with-tree-grid -S
import TreeTable from 'vue-table-with-tree-grid'

//npm install vue-quill-editor -S
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'


// axios.defaults.baseURL = "http://49.232.192.152:8082"
// axios.defaults.baseURL = "http://192.168.52.131:8088"
axios.defaults.baseURL = "http://localhost:8088"

// 添加请求拦截器
axios.interceptors.request.use(function(config) {
    // 在发送请求之前做些什么
    // 判断是否存在token,如果存在将每个页面header添加token
    console.log(config.url)
    if (config.url === 'login') {
        return config;
    }
    console.log("token:")
    console.log(window.sessionStorage.getItem("token"))
    if (window.sessionStorage.getItem("token")) {
        config.headers.common = config.headers.common || {}; // initialize object if undefined
        config.headers.common['token'] = window.sessionStorage.getItem("token");
    } else {
        console.error("Token不存在");
        router.push('/login')
    }
    return config
}, function(error) {
    router.push('/login')
    return Promise.reject(error)
})



Vue.prototype.$http = axios



Vue.use(ElementUI)
Vue.use(VueQuillEditor)

Vue.component('tree-table', TreeTable)

Vue.config.productionTip = false
new Vue({
    router,
    element,
    render: h => h(App),
}).$mount('#app')