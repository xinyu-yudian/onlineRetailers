<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card style="line-height: 10px;">

     <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="index" label="#"></el-table-column>
       <el-table-column prop="powerName" label="权限名称"></el-table-column>
       <el-table-column prop="path" label="路径"></el-table-column>
       <el-table-column label="权限等级">
         <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.level===1">一级</el-tag>
            <el-tag v-if="scope.row.level===2">二级</el-tag>
            <el-tag type="info" v-if="scope.row.level===3">三级</el-tag>
         </template>
       </el-table-column>
    </el-table>

    <el-button-group style="margin-top: 20px; margin-bottom: 10px;">
          <el-button type="primary" @click="firstPage()">首页</el-button>
          <el-button type="primary" icon="el-icon-arrow-left" @click="downPage()">上一页</el-button>
          <el-button type="primary"  @click="upPage()">下一页<i class="el-icon-arrow-right el-icon--right"></i></el-button>
          <el-button type="primary" @click="lastPage()">尾页</el-button>
      </el-button-group>

      <div>
         当前第{{pageNow}}页-共{{pageTotal}}页-数据{{total}}条
      </div>

  </el-card>
  
  </div>
    
</template>

<script>
import '../../../assets/css/global.css'

  export default {
    name: 'PowerTab',
    data(){
      return{
        pageNow:1,
        pageTotal:'',
        total:'',
        tableData: [],
      
      }
    },
    created(){
      this.getPowers()
    },
    methods:{
      firstPage:function(){
          this.pageNow = 1
          console.log(this.pageNow)
          this.getPowers()
        },
        lastPage:function(){
          this.pageNow = this.pageTotal
          console.log(this.pageNow)
          this.getPowers()
        },
        downPage:function(){
          this.pageNow = this.pageNow-1<1?this.pageNow:this.pageNow-1
          console.log(this.pageNow)
          this.getPowers()
        },
        upPage:function(){
          this.pageNow = this.pageNow+1>this.pageTotal?this.pageTotal:this.pageNow+1
          console.log(this.pageNow)
          this.getPowers()
        },
        getPowers:function(){
           this.$http.post('getPowers', {pageNow:this.pageNow }).then((res)=>{
               console.log(res.data)
             if(res.data.status !== 200)  return this.$message.error('获取权限数据失败') 
                 this.pageNow = res.data.pageNow
                 this.pageTotal = res.data.pageTotal
                 this.total = res.data.total
                 this.tableData = res.data.data
            })
      }
    }
  }
</script>
  
  <style scoped>

  </style>
  