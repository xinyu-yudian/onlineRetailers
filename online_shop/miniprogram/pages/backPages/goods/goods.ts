// pages/backPages/goods.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
     goodsCategory:[],
     goodsList:[],
     pageNow:1,
     pageTotal:1
  },
  getGoodsCategory(){
    wx.request({
      url:"http://localhost:8088/treeGoodsCategory",
      method:"POST",
      success:(res: any) => {
        console.log("GoodsCategory:")
          console.log(res)
          this.setData({
             "goodsCategory": res.data
          })
      }
    })
  },

  toGoodsDetails(e: any){
     console.log("点击商品类型：")
     console.log(e)
     var info = JSON.stringify(e.currentTarget.dataset.info)
     var infoObj = e.currentTarget.dataset.info
     if(infoObj.children != null && infoObj.children.length>0){
       for(let i=0; i<infoObj.children.length; i++){
          if(infoObj.children[i].children != null && infoObj.children[i].children.length>0){
            wx.navigateTo({
              url:'./goodsDetails/goodsDetails?info='+info
            })
            return
          }
       }
    }
    wx.showToast({
      title: '抱歉！该类别商品暂时尚未开放销售',
      icon: 'none',
      duration: 2000
    })
  },
  toGoodsView(e: any){
      console.log("点击商品：")
      console.log(e)
      var info = JSON.stringify(e.currentTarget.dataset.info)
      wx.navigateTo({
        url:'./goodsView/goodsView?info='+info
      })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
    this.getGoodsCategory()
    wx.request({
      url:"http://localhost:8088/getGoodsList",
      method:"POST",
      data:{
        pageNow: this.data.pageNow
      },
      success:(res: any) => {
          console.log("GoodsList")
          console.log(res)
          this.setData({
             "goodsList": res.data.data,
             "pageTotal": res.data.pageTotal,
          })
      }
    })
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
      this.getGoodsCategory()
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})