// pages/backPages/order/orderDetails/orderDetails.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
     orderId:0,
     userId:0,
     orderDetails:[],
     order:{},
     user:{}
  },
  getOrderDetails(){
    wx.request({
      url:"http://localhost:8088/getOrderDetails",
      method:"GET",
      data:{
        orderId: this.data.orderId
      },
      success:(res: any) => {
         console.log("orderDetails:")
          console.log(res)
          this.setData({
             orderDetails: res.data.data,
          })
      }
    })
  },
  getOrder(){
    wx.request({
      url:"http://localhost:8088/getOrders",
      method:"POST",
      data:{
        id: this.data.orderId,
        pageNow: 1
      },
      success:(res: any) => {
         console.log("orders:")
          console.log(res)
          this.setData({
            order: res.data.data[0],
          })
      }
    })
  },
getUser(){
    wx.request({
      url:"http://localhost:8088/getUsers",
      method:"POST",
      data:{
        id: this.data.userId,
        pageNow: 1
      },
      success:(res: any) => {
         console.log("user:")
          console.log(res)
          this.setData({
             user: res.data.data[0],
          })
      }
    })
  },
  toPay(){
    wx.request({
      url:"http://localhost:8088/payStatus",
      method:"POST",
      data:{
          id:this.data.orderId
      },
      success:(res: any) => {
         console.log("pay:")
          console.log(res)
          wx.showToast({
            title: 'Loading',
            icon: 'loading',
            duration: 2000
          })
          setTimeout(()=>{
            if(res.data.status != 200){
              wx.showToast({
                title: '支付失败',
                icon: 'error',
                duration: 2000
              })
              return
            }
            wx.showToast({
              title: '支付成功',
              icon: 'success',
              duration: 2000
            })
            this.getOrderDetails()
            this.onReady() 
            }, 2500)
          
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(optins: any) {
      console.log(optins)
       this.setData({
           orderId: optins.orderId,
           userId: optins.userId
       })
       this.getOrderDetails()
       this.getOrder()
       this.getUser()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})