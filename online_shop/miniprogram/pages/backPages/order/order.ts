// pages/backPages/order/order.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabs: ['全部', '未支付', '已支付'],
    current:0,
    orderList:[],
    pageNow:1,
    pageTotal:1
  },
  tabSelect:function(e: any){
    var current = e.currentTarget.dataset.id
    this.setData({
      current:current,
    })
    this.getOrders()
  },
  getOrders(){
    wx.request({
      url:"http://localhost:8088/getOrders",
      method:"POST",
      data:{
        payStatus: this.data.current-1,
        pageNow: this.data.pageNow
      },
      success:(res: any) => {
         console.log("getOrders:")
          console.log(res)
          this.setData({
             orderList: res.data.data,
             pageTotal: res.data.pageTotal
          })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
       this.getOrders()
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
    var page = this.data.pageNow+1
    this.setData({
      "pageNoew": page
    })
    if(this.data.pageNow > this.data.pageTotal){
      wx.showToast({
        title: '没有更多数据了',
        icon:'none',
        duration: 2000
      })
      return
    }
    wx.showToast({
      title: '加载中',
      icon: 'loading',
      duration: 2000
    })
    this.getOrders()
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})