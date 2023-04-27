<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <NavigationHead/>
      </el-header>
      <el-container>
        <el-aside :width="isCollapse ? '64px' :  '200px'">
          <div @click="toggleCollapse()" 
                style="height:15px; 
                       padding: 5px;
                       line-height: 10px; 
                       background-color: #545c64; 
                       color: aliceblue;
                       font-size: 25px">
            <i :class="isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'" ></i></div>
          <el-row class="tac">
  
  <el-col :span="12" style="width: 200px; height: 680px;">
      <!-- <i class="el-icon-s-grid"></i> -->
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b" 
      style="text-align: left;" 
      unique-opened
      :collapse="isCollapse"
      :collapse-transition="false">

      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-user"></i>
          <span>用户管理</span>
        </template>
          <el-menu-item index="1-1">
            <i class="el-icon-s-grid"></i>
            <router-link to="/UserTab">用户列表</router-link>
          </el-menu-item>
      </el-submenu>

      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-coordinate"></i>
          <span>权限管理</span>
        </template>
          <el-menu-item index="2-1">
            <i class="el-icon-s-grid"></i>
            <router-link to="/RoleTab">角色列表</router-link>
          </el-menu-item>
          <el-menu-item index="2-2">
            <i class="el-icon-s-grid"></i>
            <router-link to="/PowerTab">权限列表</router-link>
          </el-menu-item>
      </el-submenu>

      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-shopping-cart-2"></i>
          <span>商品管理</span>
        </template>
          <el-menu-item index="3-1">
            <i class="el-icon-s-grid"></i>
            <router-link to="/GoodsTab">商品列表</router-link>
          </el-menu-item>
          <el-menu-item index="3-2">
            <i class="el-icon-s-grid"></i>
            <router-link to="/SortParam">分类参数</router-link>
          </el-menu-item>
          <el-menu-item index="3-3">
            <i class="el-icon-s-grid"></i>
            <router-link to="/GoodsSort">商品分类</router-link>
          </el-menu-item>
      </el-submenu>

      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-truck"></i>
          <span>订单管理</span>
        </template>
          <el-menu-item index="4-1">
            <i class="el-icon-s-grid"></i>
            <router-link to="/OrderTab">订单列表</router-link>
          </el-menu-item>
      </el-submenu>

      <el-submenu index="5">
        <template slot="title">
          <i class="el-icon-s-marketing"></i>
          <span>数据统计</span>
        </template>
          <el-menu-item index="5-1">
            <i class="el-icon-s-grid"></i>
            <router-link to="/Report">数据报表</router-link>
          </el-menu-item>
      </el-submenu>



    </el-menu>
  </el-col>
</el-row>
        </el-aside>
        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
        </el-container>
      </el-container>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>


<script>
import NavigationHead from './NavigationHead.vue'



export default {
  name: 'NavigationMain',
  props: {
   
  },
  components: {
    NavigationHead
  },
  data(){
    return {
      isCollapse: false,
    }
  },
  methods:{
    toggleCollapse: function(){
        this.isCollapse = !this.isCollapse
    }
  }
 
}

</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #545c64;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  .el-menu{
      border-right: none;
    }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  a{
    color: white;
    text-decoration:none
  }
</style>
