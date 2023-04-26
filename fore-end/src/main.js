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


// axios.defaults.baseURL = "http://192.168.52.128:8088"
axios.defaults.baseURL = "http://localhost:8088"
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