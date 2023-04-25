<template>
    <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品分类</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card style="line-height: 10px;">

      <el-row style="margin-bottom: 20px;">
        <el-col :span="2">
          <el-button type="primary" @click="addFromAction()">添加分类</el-button>
        </el-col>
       </el-row>

      <tree-table 
           :data="tableData"
           :columns="columns"
           :selection-type="false"
           :expand-type="false"
           show-index
           index-text="#"
           border
           :show-row-hover="false">
           <template slot="isok" slot-scope="scope">
               <i class="el-icon-success" v-if="scope.row.effective === 1" style="color: lightgreen;"></i>
               <i class="el-icon-error" v-else style="color: red;"></i>
           </template>
           <template slot="order" slot-scope="scope">
               <el-tag size="mini" type="success" v-if="scope.row.level === 1">一级</el-tag>
               <el-tag size="mini" v-if="scope.row.level === 2">二级</el-tag>
               <el-tag size="mini" type="info" v-if="scope.row.level === 3">三级</el-tag>
           </template>
           <template slot="opt" slot-scope="scope">
               <el-button @click="updateFromAction(scope.row)" type="primary" icon="el-icon-edit" size="mini">编辑</el-button>
               <el-button @click="removeCategorySubmit(scope.row.id)" type="danger" icon="el-icon-delete" size="mini">删除</el-button>
           </template>

      </tree-table>

    </el-card>

    <!--添加商品分类-->
    <el-dialog title="添加商品分类" :visible.sync="dialogVisibleAdd"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="addFormRules" :model="addFormData" label-width="80px">
        <el-form-item label="用户名" prop="categoryName">
          <el-input v-model="addFormData.categoryName"></el-input>
        </el-form-item>
        <el-form-item  label="父级分类" style="text-align: left;">
             <el-cascader expand-trigger="hover" 
                          :options="changeData" 
                          :props="cascaderProps"
                          v-model="changeKey"
                          clearable
                          change-on-select
                          @change="changeKeyAction()"></el-cascader>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleAdd = false">取 消</el-button>
       <el-button type="primary" @click="addCategorySubmit()">确 定</el-button>
      </span>

    </el-dialog>

    <!--修改商品分类-->
    <el-dialog title="修改商品分类" :visible.sync="dialogVisibleUpdate"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="addFormRules" :model="addFormData" label-width="80px">
        <el-form-item label="用户名" prop="categoryName">
          <el-input v-model="addFormData.categoryName"></el-input>
        </el-form-item>
        <el-form-item  label="父级分类" style="text-align: left;">
             <el-cascader expand-trigger="hover" 
                          :options="changeData" 
                          :props="cascaderProps"
                          v-model="changeKey"
                          clearable
                          change-on-select
                          @change="changeKeyAction()"></el-cascader>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleUpdate = false">取 消</el-button>
       <el-button type="primary" @click="updateCategorySubmit()">确 定</el-button>
      </span>

    </el-dialog>
  
  </div>
  </template>
  
    <script>
    export default {
      name: 'GoodsSort',
      data(){
        return{
          tableData:[],
          changeData:[],
          dialogVisibleAdd:false,
          dialogVisibleUpdate:false,
          changeKey:'',
          updateId:'',
          columns:[
            {
               label: '分类名称',
               prop: 'categoryName'
            },
            {
               label: '是否有效',
               type: 'template',
               template: 'isok'
            },
            {
               label: '排序',
               type: 'template',
               template: 'order'
            },
            {
               label: '操作',
               type: 'template',
               template: 'opt'
            }
          ],
          addFormRules: {
            categoryName: [
                     { required: true, message: '请输入类别名称', trigger: 'blur' }
                ]
          },
          addFormData: {
            categoryName:'',
            level:'',
            pid:''
          },
          cascaderProps:{
             value: 'id',
             label: 'categoryName',
             children: 'children'
          }

        }
      },
      created(){
         this.getGoodsCategory()
      },
      methods:{
       async  getGoodsCategory(){
          await this.$http.post('treeGoodsCategory').then((res)=>{
               console.log(res)
               this.tableData = res.data
            })
        },
        changeKeyAction:function(){
             console.log(this.changeKey)
             if(this.changeKey.length >0){
                this.addFormData.pid = this.changeKey[this.changeKey.length - 1]
                this.addFormData.level = this.changeKey.length+1
                return
             }else{
                this.addFormData.pid = null
                this.addFormData.level = 1
             }
        },
        addFromAction:function(){
            this.addFormData = {
                categoryName:'',
                level:'',
                pid:''
            }
            this.changeKey = ''
            this.dialogVisibleAdd = true
            this.$http.post('treeGoodsCategoryChange').then((res)=>{
               console.log(res)
               this.changeData = res.data
            })
        },
        async addCategorySubmit(){
          this.dialogVisibleAdd = false
          await this.$http.post('addGoodsCategory',{
            categoryName: this.addFormData.categoryName,
            level: this.addFormData.level,
            pid: this.addFormData.pid
          }).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('添加失败') 
               this.$message.success('添加成功')
               this.getGoodsCategory()
            })
        },
        updateFromAction:function(category){
          console.log('updateId:'+category.id)
            this.addFormData.id = category.id
            this.addFormData.categoryName = category.categoryName
            this.addFormData.level = category.level
            this.updateId= category.id
            this.changeKey = category.id
            this.dialogVisibleUpdate = true
            this.$http.post('treeGoodsCategoryChange').then((res)=>{
               console.log(res)
               this.changeData = res.data
            })
        },
        async updateCategorySubmit(){
          this.dialogVisibleUpdate = false
          await this.$http.post('updateGoodsCategory',{
            id: this.updateId,
            categoryName: this.addFormData.categoryName,
            level: this.addFormData.level,
            pid: this.addFormData.pid
          }).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('修改失败') 
               this.$message.success('修改成功')
               this.getGoodsCategory()
          })
        },
        async removeCategorySubmit(id) {
           const confirmResult = await this.$confirm(
            '此操作将永久删除该用户，是否继续？','提示',{
                 confirmButtonText:'确认',
                 cancelButtonText:'取消',
                 type:'warning'
            }).catch(err => err)
            if(confirmResult !== 'confirm'){
              return this.$message.info("已取消删除")
            }
            await this.$http.delete('removeGoodsCategory/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this.getGoodsCategory()
            }) 
       },
      }
    }
    </script>
    
    <style scoped>
      
    </style>
    