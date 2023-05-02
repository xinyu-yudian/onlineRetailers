// pages/backPages/goods/goodsView/goodsView.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
      goods: <any>{},
      goodsNumber: 1,
      sumPrice: <any>0
  },
  add(){
      var number = this.data.goodsNumber + 1
      this.setData({
         goodsNumber: number
      })
      var sum = (this.data.sumPrice * this.data.goodsNumber).toFixed(2)
      this.setData({
        sumPrice: sum
      })
  },
  sub(){
    var number = this.data.goodsNumber-1<0 ? 0 :this.data.goodsNumber-1
    this.setData({
       goodsNumber: number
    })
    var sum = (this.data.sumPrice * this.data.goodsNumber).toFixed(2)
    this.setData({
      sumPrice: sum
    })
  },
  addToCart(){
      if(this.data.goodsNumber < 1){
        wx.showToast({
          title: '请选择商品数量',
          icon: 'error',
          duration: 2000
        })
        return
      }
      wx.request({
        url:"http://localhost:8088/addCartGoods",
        method:"POST",
        data:{
            id: this.data.goods.id,
            number: this.data.goodsNumber
        },
        success:(res: any) => {
            console.log(res)
            if(res.data.status == 200){
              wx.showToast({
                title: '商品加入购物车成功',
                icon: 'success',
                duration: 2000
              })
            }
            if(res.data.status == 410){
              wx.showToast({
                title: '该商品已存在购物车',
                icon: 'error',
                duration: 2000
              })
            }

        }
      })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options: any) {
    var info = JSON.parse(options.info)
    this.setData({
       goods: info
    })
    console.log("商品：")
    console.log(this.data.goods)
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