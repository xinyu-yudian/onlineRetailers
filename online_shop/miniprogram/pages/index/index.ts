// index.ts
Page({
  data: {
     loginForm:{
       username:'admin',
       password:'123456'
     }
  },
  //
  login(){
     wx.request({
       url:"http://localhost:8088/login",
       method:"POST",
       data:{
         username: this.data.loginForm.username,
         password: this.data.loginForm.password
       },
       success:(res: any) => {
           console.log(res)
           if(res.data.status !== 200){
              wx.showToast({
                title: '登录失败',
                icon: 'error',
                duration: 2000
              })
              return
           }
           wx.showToast({
              title: '登录成功',
              icon: 'success',
              duration: 2000
          })
          wx.switchTab({
             url: '../backPages/goods/goods'
          })
       }
     })
  },
  inputUsername:function(e:any){
    this.setData({
      "loginForm.username": e.detail.value
    })
      console.log(this.data.loginForm.username)
  },
  inputPassword:function(e:any){
    this.setData({
      "loginForm.password": e.detail.value
    })
      console.log(this.data.loginForm.password)
  }
 
})
