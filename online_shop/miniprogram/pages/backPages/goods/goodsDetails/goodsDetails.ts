Page({
 
  /**
   * 页面的初始数据
   */
  data: {
    title:'',
    lists: <any>[],
    indexId: 0,
    goodsList:<any>[],
    pageNow: 1
  },
  // 左侧点击事件
  jumpIndex(e: any) {
    let index = e.currentTarget.dataset.menuindex
    console.log(index)
    this.setData({
      indexId: index
    });
    this.getGoodsList()
  },
  getGoodsList(){
    wx.request({
      url:"http://localhost:8088/getGoodsList",
      method:"POST",
      data:{
          pageNow: this.data.pageNow,
          catId: this.data.indexId
      },
      success:(res: any) => {
         console.log("goodsList:")
          console.log(res)
          this.setData({
            goodsList: res.data.data,
          })
      }
    })
  },
  toGoodsView(e: any){
    console.log("点击商品：")
    console.log(e)
    var info = JSON.stringify(e.currentTarget.dataset.info)
    wx.navigateTo({
      url:'../goodsView/goodsView?info='+info
    })
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options: any) {
      var info = JSON.parse(options.info)
      console.log(info)
      var id = 0
      for(let i=0; i<info.children.length; i++){
         if(info.children[i].children!=null && info.children[i].children.length>0){
            for(let j=0; j<info.children[i].children.length; j++){
               if(info.children[i].children[j]!=null){
                  id = info.children[i].children[j].id
                  this.setData({
                    title: info.categoryName,
                    lists: info.children,
                    indexId: id
                  })
                  this.getGoodsList()  
                  return
               }
            }
         }
      }
      
  },
 
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
 
  },
 
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
 
  },
 
  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {
 
  },
 
  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {
 
  },
 
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
 
  },
 
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
 
  },
 
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {
 
  }
})