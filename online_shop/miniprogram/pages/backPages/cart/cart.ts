// pages/backPages/cart/cart.ts
Page({

  /**
   * 页面的初始数据
   */
  data: {
    dataList: <any>[], // 数据列表
    checkedIds: <any>[], // 选中的id列表,
    checkedAll:false,
    sumPrice: <number>0
  },
  checkboxChange(e: any) { // 复选框change事件
    let id = e.detail.value[0];
    let checkedIds = this.data.checkedIds;
    if (id !==undefined && id !=='') { // 判断是否选中
      checkedIds.push(id);
    }else { // 过滤出选中的复选框
      checkedIds = checkedIds.filter(item=>String(item)!==String(e.currentTarget.dataset.id));
    }
    //调整单选选中状态
    let setArr = this.data.dataList
    for(let i=0; i<this.data.dataList.length; i++){
       for(let j=0; j<this.data.checkedIds.length; j++){
         if(this.data.dataList[i].goods.id == this.data.checkedIds[j]){
             setArr[i].checked = true
             break
         }
       }
    }
    this.setData({
        dataList: setArr
    })
    // 调整全选按钮状态
    if (checkedIds.length == this.data.dataList.length) { 
      this.setData({
        checkedIds:checkedIds,
        checkedAll:true
      })
    }else {
      this.setData({
        checkedIds:checkedIds,
        checkedAll:false
      })
    }
    console.log(this.data.checkedIds);
    this.sumPriceAction()
  },
  selectAll(e:any){ // 全选框
    if (e.detail.value[0] ==="all") {
      console.log("全部选中");
      this.setData({
        checkedIds:this.data.dataList.map(item=>item.goods.id),
        dataList:this.data.dataList.map(item=>{item.checked = true;return item;})
      })
    }else { // 直接清空列表
      console.log("清空");
      this.setData({
        checkedIds:[],
        dataList:this.data.dataList.map(item=>{item.checked = false;return item;})
      });
    }
    console.log(this.data.checkedIds);
    this.sumPriceAction()
  },
  getCartList(){
    wx.request({
      url:"http://localhost:8088/selCartGoods",
      method:"GET",
      success:(res: any) => {
         console.log("cartList:")
          console.log(res)
          this.setData({
             dataList: res.data.cartList
          })
      }
     })
  },
onRefresh:function(){
     //导航条加载动画
     wx.showNavigationBarLoading()
     //loading 提示框
     wx.showLoading({
       title: 'Loading...',
     })
     setTimeout(function () {
       wx.hideLoading();
       wx.hideNavigationBarLoading();
       //停止下拉刷新
       wx.stopPullDownRefresh();
     }, 2000)
     this.getCartList()
  },
  add(e: any){
    var id = e.currentTarget.dataset.id
    var arr = this.data.dataList
    console.log(id)
    console.log(this.data)
    console.log(this.data.dataList)
    for(let i=0; i<this.data.dataList.length; i++){
       if(arr[i].goods.id == id){
           arr[i].number++
           break
       }
    }
    this.setData({
        dataList: arr
    })
    this.sumPriceAction()
},
sub(e: any){
  var id = e.currentTarget.dataset.id
  var arr = this.data.dataList
  console.log(id)
  console.log(this.data)
  console.log(this.data.dataList)
  for(let i=0; i<this.data.dataList.length; i++){
     if(arr[i].goods.id == id){
         arr[i].number =  arr[i].number-1<0 ? 0: arr[i].number-1
         break
     }
  }
  this.setData({
      dataList: arr
  })
  this.sumPriceAction()
},
sumPriceAction(){
  //计算总价
  let sum = 0
  for(let j=0; j<this.data.checkedIds.length; j++){
    for(let i=0; i<this.data.dataList.length; i++){
      if(this.data.dataList[i].goods.id == this.data.checkedIds[j]){
          sum = sum + (this.data.dataList[i].goods.price *  this.data.dataList[i].number)
          this.setData({
            sumPrice: <number>sum
          })
          break
      }
    }
  }
  if(this.data.checkedIds.length == 0){
    this.setData({
      sumPrice: 0
    })
  }
},
//生成订单-结算
toSettlement(){
  if(this.data.checkedIds.length == 0){
      wx.showToast({
        title: '选择要结算的商品',
        icon: 'error',
        duration: 2000
      })
      return
  }
  
   let setArr = new Array()
   for(let i=0; i<this.data.dataList.length; i++){
       if(this.data.dataList[i].checked){
         setArr.push(this.data.dataList[i])
       }
   }
   console.log("购买的商品：")
   console.log(setArr)
   wx.request({
      url:"http://localhost:8088/generateOrder",
      method:"POST",
      data:{
        cart: setArr
      },
      success:(res: any) => {
        console.log("订单生成结果:")
          console.log(res)
          if(res.data.status != 200){
            wx.showToast({
              title: '订单生成失败',
              icon: 'error',
              duration: 2000
            })
            return
          }
          this.getCartList()
          wx.showToast({
            title: '订单已生成',
            icon: 'success',
            duration: 2000
          })
          let orderId = res.data.order.id
          let userId= res.data.order.userId
          wx.navigateTo({
            url:'../../backPages/order/orderDetails/orderDetails?orderId='+orderId+'&userId='+userId
          })
      }
  })
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad() {
     this.getCartList()
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
    this.onRefresh();
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