<template>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>订单管理</el-breadcrumb-item>
      <el-breadcrumb-item>订单列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card style="line-height: 10px;">
        <el-row style="margin-bottom: 20px;">
        <el-col :span="6">
          <el-input v-model="inputOrder" placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search" @click="selOrder()"></el-button>
          </el-input>
        </el-col>
       </el-row>

       <el-table :data="tableData" border style="width: 100%">
        <el-table-column type="index" label="#"></el-table-column>
         <el-table-column prop="orderNumber" label="订单编号"></el-table-column>
         <el-table-column prop="orderPrice" label="订单价格" width="150px"></el-table-column>
         <el-table-column  label="是否付款" width="100px">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.payStatus === 1">已付款</el-tag>
            <el-tag type="danger" v-if="scope.row.payStatus === 0">未付款</el-tag>
          </template>
         </el-table-column>
         <el-table-column prop="isSend" label="是否发货" width="100px"></el-table-column>
         <el-table-column prop="placeTime" label="下单时间" width="200px"></el-table-column>
         <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="updateAddressClick(scope.row.id)" type="primary" icon="el-icon-edit" size="mini"></el-button>
            <el-button  type="primary" icon="el-icon-location-outline" size="mini"></el-button>
          </template>
         </el-table-column>
      </el-table>

      <el-button-group style="margin-top: 20px; margin-bottom: 10px;">
          <el-button type="primary" @click="firstPage()">首页</el-button>
          <el-button type="primary" icon="el-icon-arrow-left" @click="downPage()">上一页</el-button>
          <el-button type="primary" @click="upPage()">下一页<i class="el-icon-arrow-right el-icon--right" ></i></el-button>
          <el-button type="primary" @click="lastPage()">尾页</el-button>
      </el-button-group>

      <div>
         当前第{{pageNow}}页-共{{pageTotal}}页-数据{{total}}条
      </div>
    </el-card>

    <!--修改用户-->   
    <el-dialog title="修改地址" :visible.sync="dialogVisibleUpdate"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="updateFormRules" :model="updateForm" label-width="100px">
        <el-form-item label="省市区/县" prop="firstAddress" style="text-align: left;">
            <el-cascader :options="cityData" :props="cascaderProps" v-model="updateForm.firstAddress"></el-cascader>
        </el-form-item>
        <el-form-item label="详细地址" prop="secondAddress" style="text-align: left;">
          <el-input v-model="updateForm.secondAddress" style="max-width: 300px;" placeholder="请输入详细地址"></el-input>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleUpdate = false">取 消</el-button>
       <el-button type="primary" @click="updateAddressSubmit()">确 定</el-button>
      </span>

    </el-dialog>   

    </div>
      
  </template>
  
    <script>

    import cityData from '../../../plugins/citydata.js'

    export default {
      name: 'OrderTab',
      data(){
          return {
              cityData: cityData.cityData,
              cascaderProps:{
                value: 'name',
                label: 'name',
                children: 'sub'
              },
              inputOrder:'',
              tableData: [],
              pageNow: 1,
              pageTotal: '',
              total: '',
              updateForm:{
                id:'',
                firstAddress: [],
                secondAddress: ''
              },
              dialogVisibleUpdate: false,
              updateFormRules:{
                firstAddress: [
                     { required: true, message: '请选择省市区/县', trigger: 'blur' }
                ],
                secondAddress: [
                     { required: true, message: '请填写详细地址', trigger: 'blur' }
                ],
              }
          }
      },
      created(){
          this.getOrders()
      },
      methods:{
         selOrder:function(){
            this.$http.post('getOrders', {pageNow:1, orderNumber: this.inputOrder}).then((res)=>{
               console.log(res.data)
             if(res.data.status !== 200)  return this.$message.error('查询订单数据失败') 
                 this.pageNow = res.data.pageNow
                 this.pageTotal = res.data.pageTotal
                 this.total = res.data.total
                 this.tableData = res.data.data
            })
          },
        async getOrders(){
          this.$http.post('getOrders', {pageNow:this.pageNow }).then((res)=>{
               console.log(res.data)
             if(res.data.status !== 200)  return this.$message.error('获取订单数据失败') 
                 this.pageNow = res.data.pageNow
                 this.pageTotal = res.data.pageTotal
                 this.total = res.data.total
                 this.tableData = res.data.data
            })
        },
        firstPage:function(){
          this.pageNow = 1
          console.log(this.pageNow)
          this.getOrders()
        },
        lastPage:function(){
          this.pageNow = this.pageTotal
          console.log(this.pageNow)
          this.getOrders()
        },
        downPage:function(){
          this.pageNow = this.pageNow-1<1?this.pageNow:this.pageNow-1
          console.log(this.pageNow)
          this.getOrders()
        },
        upPage:function(){
          this.pageNow = this.pageNow+1>this.pageTotal?this.pageTotal:this.pageNow+1
          console.log(this.pageNow)
          this.getOrders()
        },
        updateAddressClick:function(id){
           this.dialogVisibleUpdate = true
           this.updateForm.id = id
        },
        async updateAddressSubmit(){
          this.dialogVisibleUpdate = false
          await this.$http.post('updateAddress',{
                  id: this.updateForm.id,
                  firstAddress: this.updateForm.firstAddress, 
                  secondAddress: this.updateForm.secondAddress
               }).then((res)=>{
                  console.log(res)
                  if(res.data.status !== 200)  return this.$message.error('修改失败') 
                  this.$message.success('修改成功')
                  this.getOrders()
            })
        }
      }
    }
    </script>
    
    <style scoped>
      
    </style>
    