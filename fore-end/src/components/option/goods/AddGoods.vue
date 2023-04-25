<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>商品管理</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/GoodsTab' }">商品列表</el-breadcrumb-item>
            <el-breadcrumb-item>添加商品</el-breadcrumb-item>
       </el-breadcrumb>
      
       <el-card style="line-height: 10px;">
        <el-alert title="添加商品信息" type="info" center show-icon closeable="false">
        </el-alert>

        <el-steps :space="200" :active="activeIndex - 0" finish-status="success" align-center>
            <el-step title="基本信息"></el-step>
            <el-step title="商品参数"></el-step>
            <el-step title="商品属性"></el-step>
            <el-step title="商品图片"></el-step>
            <el-step title="商品内容"></el-step>
            <el-step title="完成"></el-step>
        </el-steps>

        <el-form :model="addForm" :rules="addFormRules" ref="addForm" label-width="100px" label-position="top">
          <el-tabs v-model="activeIndex" :tab-position="'left'" style="text-align: left;" :before-leave="beforeTabLeave" @tab-click="tabClicked()">
            <el-tab-pane label="基本信息" name="0">
                <el-form-item label="商品名称" prop="goodsName">
                     <el-input v-model="addForm.goodsName" :span="6"></el-input>
                </el-form-item>
                <el-form-item label="商品价格" prop="price">
                     <el-input v-model="addForm.price"></el-input>
                </el-form-item>
                <el-form-item label="商品数量" prop="number">
                     <el-input v-model="addForm.number"></el-input>
                </el-form-item>
                <el-form-item label="商品重量" prop="weight">
                     <el-input v-model="addForm.weight"></el-input>
                </el-form-item>
                <el-form-item  label="选择商品分类">
                  <el-cascader expand-trigger="hover" 
                                :options="changeData" 
                                :props="cascaderProps"
                                v-model="changeKey"
                                clearable
                                change-on-select
                                @change="changeKeyAction()"></el-cascader>
                </el-form-item>
            </el-tab-pane>

            <el-tab-pane label="商品参数" name="1">
                     <el-form-item :label="item.attrName" v-for="item in attrList" :key="item.id">
                        <el-checkbox-group v-model="item.children">
                            <el-checkbox v-model="addForm.attrs" :value="child.childName" :label="child.childName" v-for="child in item.children" :key="child.id" border></el-checkbox>
                        </el-checkbox-group>
                     </el-form-item>
            </el-tab-pane>

            <el-tab-pane label="商品属性" name="2">
                <el-form-item :label="item.paramName" v-for="item in paramsList" :key="item.id">
                        <el-checkbox-group v-model="item.children">
                            <el-checkbox v-model="addForm.params" :value="child.childName" :label="child.childName" v-for="child in item.children" :key="child.id" border></el-checkbox>
                        </el-checkbox-group>
                     </el-form-item>
            </el-tab-pane>

            <el-tab-pane label="商品图片" name="3">
                <el-upload 
                  :action="uploadURL"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  list-type="picture">
                  <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>
            </el-tab-pane>

               <el-tab-pane label="商品内容" name="4">
                    <quill-editor v-model="addForm.introduce"></quill-editor>
                    <el-button type="primary" style="margin-top: 15px;" @click="addFormSubmit()">添加商品</el-button>
                </el-tab-pane>
           </el-tabs>
       </el-form>
    
       </el-card>

       <el-dialog title="图片预览" :visible.sync="previewVisible" width="30%" style="line-height: 10px;">
        <hr>
           <img :src="previewPath" alt="" width="300px">
       </el-dialog>

    </div>
      
  </template>
  
    <script>
    export default {
      name: 'AddGoods',
      data(){
        return{
            activeIndex: '0',
            addForm: {
                goodsName: '',
                price:'',
                number:'',
                weight: '',
                attrs:[],
                params:[],
                introduce:''
            },
            addFormRules: {
              goodsName:{
                required: true,message:'请输入商品名称', trigger: 'blur'
              },
              price:{
                required: true,message:'请输入商品价格', trigger: 'blur'
              },
              number:{
                required: true,message:'请输入商品数量', trigger: 'blur'
              },
              weight:{
                required: true,message:'请输入商品重量', trigger: 'blur'
              },
            },
            changeData:[],
            cascaderProps:{
               value: 'id',
               label: 'categoryName',
               children: 'children'
            },
            changeKey: '',
            exportCid: '',
            attrList:[],
            paramsList:[],
            uploadURL: 'http://localhost:8088/upload',
            previewPath:'',
            previewVisible: false,

        }
      },
      created(){
         this.getGoodsCategory()
      },
      methods:{
        async  getGoodsCategory(){
          await this.$http.post('treeGoodsCategory').then((res)=>{
               console.log(res)
               this.changeData = res.data
            })
        },
        changeKeyAction:function(){
             console.log(this.changeKey)
             if(this.changeKey.length !== 3){
                 this.changeKey = []
                 return
             }else{
                this.exportCid = this.changeKey[2]
             }
            
        },
        next() {
          if (this.activeIndex++ > 4) this.activeIndex = 0;
        },
        beforeTabLeave:function(activeName,oldActiveName){
            console.log(oldActiveName);
            if(oldActiveName === '0' && this.changeKey.length !== 3){
              this.$message.error("请选择商品分类！")
              return false
            }
            console.log(activeName)
        },
        tabClicked:function(){
          if(this.activeIndex === '1'){
            this.$http.get('getAttrs/'+this.exportCid).then((res) => {
                  console.log(res)
                  this.attrList = res.data
                  if (res.status !== 200) return this.$message.error('获取商品动态参数失败') 
             })
          }
          if(this.activeIndex === '2'){
            this.$http.get('getParams/'+this.exportCid).then((res) => {
                  console.log(res)
                  this.paramsList = res.data
                  if (res.status !== 200) return this.$message.error('获取商品静态属性失败') 
             })
          }
         
        },
        handlePreview:function(file){
            console.log(file)
            this.previewPath = file.url
            this.previewVisible = true
        },
        handleRemove:function(file){
            console.log(file)
             this.$http.delete('removeIcon/'+file.name).then((res)=>{
                if (res.status !== 200) return this.$message.error('删除失败') 
                this.$message.success('删除成功')
            }) 
        },
        addFormSubmit:function(){
            this.$refs.addForm.validate(valid => {
               if(!valid) return this.$message.error("请填写必要的表单项")
               
           this.$http.post('addGoods', {
                goodsName: this.addForm.goodsName,
                price: this.addForm.price,
                number: this.addForm.number,
                weight: this.addForm.weight,
                catId: this.exportCid,
                attrs: this.addForm.attrs,
                params: this.addForm.params,
                introduce: this.addForm.introduce
            }).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('添加失败') 
               this.$message.success('添加成功')
               this.$router.push('/GoodsTab')
            })
            })
        }
      }
    }
    </script>
    
    <style scoped>
      .el-steps{
          margin: 15px 0px;
      }

      .el-step__totle{
        font-size: 13px;
      }
    </style>
    