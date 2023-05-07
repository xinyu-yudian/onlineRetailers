<template>
    <div class="login_container">
       <div class="login_box">
          <!-- 图片 -->
          <div class="avatar_box">
             <img src="../assets/logo.png" alt="" >
          </div>
          <!-- 主区 -->
          <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules"  label-width="0" class="login_form">
             <!-- 用户名 -->
             <el-form-item prop="username">
                <el-input  prefix-icon="iconfont icon-user" v-model="loginForm.username"></el-input>
             </el-form-item>
             <!-- 密码 -->
             <el-form-item prop="password">
                <el-input type="password" prefix-icon="iconfont icon-3702mima" v-model="loginForm.password"></el-input>
             </el-form-item>
             <!-- 登陆 -->
             <el-form-item class="btns">
                <el-button type="primary" @click="login">登陆</el-button>
             <!-- 重置 -->
                <el-button type="info" @click="resetLoginForm">重置</el-button>
             </el-form-item>
          </el-form>
       </div>
    </div>
 </template>

 <script>

 
 export default {
    name:"Login",
   data () {
     return {
       loginForm: {
         username: 'admin',
         password: '123456'
       },
       loginFormRules: {
         username: [
           { required: true, message: '请输入用户名', trigger: 'blur' },
           { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
         ],
         password: [
           { required: true, message: '请输入密码', trigger: 'blur' },
           { min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur' }
         ]
       }
     }
   },
   methods: {
     resetLoginForm () {
       this.$refs.loginFormRef.resetFields()
     },
     login () {
       this.$refs.loginFormRef.validate(async valid => {
         if (!valid) return false
         // 异步处理
         const { data: result } = await this.$http.post('login', {username:this.loginForm.username, password:this.loginForm.password})
         console.log(result)
         if (result.status !== 200) return this.$message.error('登陆失败-用户不存在/密码错误') 
         this.$message.success('登陆成功')
         console.log(result.token+'--------------------------------')
         window.sessionStorage.setItem('token', result.token)
         // 眺转，编程式导航对象
         this.$router.push('home')
       })
     }
   }
 
 }
 </script>
 <style lang="less" scoped>
    .login_box {
       width: 450px;
       height: 300px;
       background-color: #246d89;
       position: absolute;
       border-radius: 3px;
       left: 50%;
       top: 50%;
       transform: translate(-50%,-50%);
       .avatar_box {
                height: 130px;
                width: 130px;
                border: 1px solid #eee;
                border-radius: 50%;
                padding: 10px;
                box-shadow: 0 0 10px rgba(7, 168, 205, 0.733);
                position: absolute;
                left: 50%;
                transform: translate(-50%,-50%);
                background-color: #fff;
                img {
                   width: 100%;
                   height: 100%;
                   border-radius: 50%;
                   background-color: #eee;
                }
       }
    }
    .btns {
       display: flex;
       justify-content: end;
    }
    .login_form{
       position: absolute;
       bottom: 0;
       width: 100%;
       padding: 0 20px;
       box-sizing: border-box;
    }
 </style>
 