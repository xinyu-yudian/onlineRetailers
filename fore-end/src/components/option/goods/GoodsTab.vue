<template>
 <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card style="line-height: 10px;">

      <el-row style="margin-bottom: 20px;">
        <el-col :span="6">
          <el-input v-model="inputGoodsName" placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search" @click="selGoodsName()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="3">
          <el-button type="primary" @click="toAddGoodsVue()">添加商品信息</el-button>
        </el-col>
       </el-row>

      <el-table :data="tableData" border style="width: 100%">
        <el-table-column type="index" label="#"></el-table-column>
         <el-table-column prop="goodsName" label="商品名称" width="600px"></el-table-column>
         <el-table-column prop="price" label="商品价格" width="100px"></el-table-column>
         <el-table-column prop="weight" label="商品重量" width="100px"></el-table-column>
         <el-table-column prop="addTime" label="创建时间" width="200px"></el-table-column>
         <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button  type="primary" icon="el-icon-edit" size="mini"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeGoods(scope.row.id)"></el-button>
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
  
  </div>
      
  </template>
  
    <script>
    export default {
      name: 'GoodsTab',
      data(){
        return{
          tableData: [],
          inputGoodsName: '',
          pageNow:1,
          pageTotal:'',
          total:'',

        }
      },
      created(){
         this.getGoodsList()
      },
      methods:{
       async getGoodsList(){
          await this.$http.post('getGoodsList',{pageNow: this.pageNow}).then((res)=>{
               console.log(res)
               this.tableData = res.data.data
               this.pageNow = res.data.pageNow
               this.pageTotal = res.data.pageTotal
               this.total = res.data.total
            })
        },
        firstPage:function(){
          this.pageNow = 1
          console.log(this.pageNow)
          this.getGoodsList()
        },
        lastPage:function(){
          this.pageNow = this.pageTotal
          console.log(this.pageNow)
          this.getGoodsList()
        },
        downPage:function(){
          this.pageNow = this.pageNow-1<1?this.pageNow:this.pageNow-1
          console.log(this.pageNow)
          this.getGoodsList()
        },
        upPage:function(){
          this.pageNow = this.pageNow+1>this.pageTotal?this.pageTotal:this.pageNow+1
          console.log(this.pageNow)
          this.getGoodsList()
        },
        async selGoodsName(){
          await this.$http.post('getGoodsList', {pageNow: this.pageNow, goodsName: this.inputGoodsName}).then((res)=>{
               console.log(res.data)
             if(res.data.status !== 200)  return this.$message.error('获取商品数据失败') 
               this.tableData = res.data.data
               this.pageNow = res.data.pageNow
               this.pageTotal = res.data.pageTotal
               this.total = res.data.total
            })
        },
        toAddGoodsVue:function(){
          this.$router.push('/AddGoods')
        },
        async removeGoods(id) {
           const confirmResult = await this.$confirm(
            '此操作将永久删除该用户，是否继续？','提示',{
                 confirmButtonText:'确认',
                 cancelButtonText:'取消',
                 type:'warning'
            }).catch(err => err)
            if(confirmResult !== 'confirm'){
              return this.$message.info("已取消删除")
            }
            await this.$http.delete('removeGoods/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this.getGoodsList()
            }) 
       },
      }
    }
    </script>
    
    <style scoped>
      .el-table-column {
        width: fit-content;
      }
    </style>
    