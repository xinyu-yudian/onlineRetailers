<template>
  <div>
    <img src="../../assets/logo.png" alt="logo" class="logo"/>
    <span style="float: left; color: aliceblue;">商城后台管理系统</span>
   <el-button @click="exitAction()" type="info" icon="el-icon-error" plain style="float: right;margin-top: 10px;">退出登录</el-button>
  </div>
    
</template>

  <script>
  export default {
    name: 'NavigationHead',
    props: {
      
    },
    components: {
      
    },
    methods:{
      exitAction:function(){
         window.sessionStorage.clear();
         this.$router.push("/")
      }
    }
  }
  </script>
  
  <style scoped>
     .logo{
       width: 50px;
       border-radius: 50%;
       float: left;
       margin: 5px;
     }
  </style>
  