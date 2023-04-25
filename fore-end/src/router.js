import Vue from 'vue'
import Router from 'vue-router'
import UserTab from './components/option/user/UserTab.vue'
import RoleTab from './components/option/power/RoleTab.vue'
import GoodsTab from './components/option/goods/GoodsTab.vue'
import AddGoods from './components/option/goods/AddGoods.vue'
import SortParam from './components/option/goods/SortParam.vue'
import GoodsSort from './components/option/goods/GoodsSort.vue'
import OrderTab from './components/option/order/OrderTab.vue'
import Report from './components/option/report/Report.vue'
import Login from './components/Login.vue'
import NavigationMain from './components/navigation/NavigationMain.vue'
import Welcome from './components/navigation/Welcome.vue'
import PowerTab from './components/option/power/PowerTab.vue'


Vue.use(Router)

export default new Router({
    routes: [
        { path: '/', redirect: './Login' },
        { path: '/Login', component: Login },
        {
            path: '/home',
            component: NavigationMain,
            redirect: '/Welcome',
            children: [
                { path: '/Welcome', component: Welcome },
                { path: '/UserTab', component: UserTab },
                { path: '/RoleTab', component: RoleTab },
                { path: '/PowerTab', component: PowerTab },
                { path: '/GoodsTab', component: GoodsTab },
                { path: '/AddGoods', component: AddGoods },
                { path: '/SortParam', component: SortParam },
                { path: '/GoodsSort', component: GoodsSort },
                { path: '/OrderTab', component: OrderTab },
                { path: '/Report', component: Report }
            ]
        },
    ]
})