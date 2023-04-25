<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      <el-breadcrumb-item>分类参数</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card style="line-height: 10px;">
      <el-alert title="注意：只允许为第三级分类设置相关参数！" type="warning" show-icon :closable="false" style="margin: 15px 0px;"></el-alert>

    <el-row>
        <el-col>
          <el-form ref="form"  label-width="100px">
           <el-form-item  label="选择商品分类" style="text-align: left;">
             <el-cascader expand-trigger="hover" 
                          :options="changeData" 
                          :props="cascaderProps"
                          v-model="changeKey"
                          clearable
                          change-on-select
                          @change="changeKeyAction()"></el-cascader>
          </el-form-item>
       </el-form>
        </el-col>
    </el-row>

    <el-tabs v-model="activeName" @tab-click="handleClick" style="text-align: left;">
           <el-tab-pane label="动态参数" name="many">
               <el-button type="primary" size="mini" :disabled="isBtnDisabled" style="margin: 10px 0px;" @click="addAttrFormAction()">添加参数</el-button>
               <el-table :data="attrList" border stripe>

                <el-table-column type="expand">
                  <template slot-scope="scope">
                                  <el-tag style="margin: 0px 20px;" 
                                          closable 
                                          type="success" v-for="(item) in scope.row.children" 
                                          :key="item.id" 
                                           @close="removeAttrChildItem(item.id)">
                                        {{item.childName}}
                                 </el-tag>
                            <el-input
                                class="input-new-tag"
                                v-if="inputVisible"
                                v-model="inputValue"
                                ref="saveTagInput"
                                size="small"
                                @keyup.enter.native="handleInputConfirmAttr()"
                                @blur="handleInputConfirmAttr()">
                              </el-input>
                              <el-button v-else class="button-new-tag" size="small" @click="showInput(scope.row.id)">+ New Tag</el-button>
                    </template>
                </el-table-column>

                 <el-table-column type="index"></el-table-column>
                 <el-table-column label="参数名称" prop="attrName"></el-table-column>
                 <el-table-column label="操作">
                    <template slot-scope="scope">
                      <el-button  type="primary" icon="el-icon-edit" size="mini" @click="updateAttrFormAction(scope.row)">编辑</el-button>
                      <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeAttrSubmit(scope.row.id)">删除</el-button>
                    </template>
                 </el-table-column>
               </el-table>
              
           </el-tab-pane>

           <el-tab-pane label="静态属性" name="only">
              <el-button type="primary" size="mini" :disabled="isBtnDisabled" @click="addParamFormAction()">添加属性</el-button>

              <el-table :data="paramsList" border stripe>

            <el-table-column type="expand">
              <template slot-scope="scope">
                              <el-tag style="margin: 0px 20px;" 
                                      closable 
                                      type="success" v-for="(item) in scope.row.children" 
                                      :key="item.id"
                                      @close="removeParamChildItem(item.id)" >
                                        {{item.childName}}     
                              </el-tag>
                            <el-input
                                class="input-new-tag"
                                v-if="inputVisible"
                                v-model="inputValue"
                                ref="saveTagInput"
                                size="small"
                                @keyup.enter.native="handleInputConfirmParam()"
                                @blur="handleInputConfirmParam()">
                              </el-input>
                              <el-button v-else class="button-new-tag" size="small" @click="showInput(scope.row.id)">+ New Tag</el-button>
                </template>
            </el-table-column>

            <el-table-column type="index"></el-table-column>
            <el-table-column label="参数名称" prop="paramName"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button  type="primary" icon="el-icon-edit" size="mini" @click="updateParamFormAction(scope.row)">编辑</el-button>
                  <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeParamSubmit(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
            </el-table>

           </el-tab-pane>

     </el-tabs>
     
    </el-card>

    <!--添加动态参数-->
    <el-dialog title="添加动态参数" :visible.sync="dialogVisibleAttrAdd"  width="30%" style="line-height: 10px;">
      <el-form ref="form" :rules="addFormRules" :model="addAttrFormData" label-width="80px">
        <el-form-item label="参数名" prop="name">
          <el-input v-model="addAttrFormData.name"></el-input>
        </el-form-item>
       </el-form>
      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleAttrAdd = false">取 消</el-button>
       <el-button type="primary" @click="addAttrFormSubmit">确 定</el-button>
      </span>
    </el-dialog>

       <!--修改动态参数-->
       <el-dialog title="修改动态参数" :visible.sync="dialogVisibleAttrUpdate"  width="30%" style="line-height: 10px;">
      <el-form ref="form" :rules="addFormRules" :model="updateAttrFormData" label-width="80px">
        <el-form-item label="参数名" prop="name">
          <el-input v-model="updateAttrFormData.name"></el-input>
        </el-form-item>
       </el-form>
      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleAttrUpdate = false">取 消</el-button>
       <el-button type="primary" @click="updateAttrFormSubmit">确 定</el-button>
      </span>
    </el-dialog>

     <!--添加静态属性-->
     <el-dialog title="添加静态属性" :visible.sync="dialogVisibleParamAdd"  width="30%" style="line-height: 10px;">
      <el-form ref="form" :rules="addFormRules" :model="addParamFormData" label-width="80px">
        <el-form-item label="属性名" prop="name">
          <el-input v-model="addParamFormData.name"></el-input>
        </el-form-item>
       </el-form>
      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleParamAdd = false">取 消</el-button>
       <el-button type="primary" @click="addParamFormSubmit">确 定</el-button>
      </span>
    </el-dialog>

       <!--修改静态属性-->
       <el-dialog title="修改静态属性" :visible.sync="dialogVisibleParamUpdate"  width="30%" style="line-height: 10px;">
      <el-form ref="form" :rules="addFormRules" :model="updateParamFormData" label-width="80px">
        <el-form-item label="属性" prop="name">
          <el-input v-model="updateParamFormData.name"></el-input>
        </el-form-item>
       </el-form>
      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleParamUpdate = false">取 消</el-button>
       <el-button type="primary" @click="updateParamFormSubmit">确 定</el-button>
      </span>
    </el-dialog>

  </div>
      
  </template>
  
    <script>
    export default {
      name: 'SortParam',
      data(){
          return{
            dialogVisibleAttrAdd:false,
            dialogVisibleAttrUpdate:false,
            dialogVisibleParamAdd:false,
            dialogVisibleParamUpdate:false,
            changeData:[],
            changeKey:'',
            activeName:'',
            cascaderProps:{
               value: 'id',
               label: 'categoryName',
               children: 'children'
            },
            attrList:[],
            paramsList:[],
            exportCid:'',
            addFormRules: {
              name: [
                  { required: true, message: '请输入名称', trigger: 'blur' }
              ]
           },
            addAttrFormData:{
              name:''
            },
            updateAttrFormData:{
              id:'',
              name:'',
            },
            addParamFormData:{
              name:''
            },
            updateParamFormData:{
              id:'',
              name:'',
            },
            inputVisible: false,
            inputValue: '',
            addAttrOrParamId:'',
           
        }
      },
      computed:{
        isBtnDisabled(){
          if(this.changeKey.length !== 3){
              return true
          }
          return false
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
                 this.attrList = ''
                 this.paramsList = ''
                 return
             }else{
                this.exportCid = this.changeKey[2]
                this.handleClick()
             }
            
        },
        handleClick:function(){
           console.log(this.activeName)
           const cid = this.exportCid
           if(cid != null && cid != ''){
            if(this.activeName == 'many'){
                    this.$http.get('getAttrs/'+cid).then((res) => {
                       console.log(res)
                       this.attrList = res.data
                   })
                }
                if(this.activeName == 'only'){
                  this.$http.get('getParams/'+cid).then((res) => {
                       console.log(res)
                       this.paramsList = res.data
                   })
                }
           }
           
        },
        addAttrFormAction:function(){
          this.dialogVisibleAttrAdd = true
        },
        addAttrFormSubmit:function(){
          this.dialogVisibleAttrAdd = false
          this.$http.post('addAttr',{attrName: this.addAttrFormData.name, cid: this.exportCid}).then((res)=>{
               console.log(res)
               if(res.data.status !== 200)  return this.$message.error('添加失败') 
               this.$message.success('添加成功')
               this.handleClick()
            })
        },
        updateAttrFormAction:function(attr){
          console.log(attr)
          this.updateAttrFormData.id = attr.id
          this.updateAttrFormData.name = attr.attrName
          this.dialogVisibleAttrUpdate = true
        },
        updateAttrFormSubmit:function(){
          this.dialogVisibleAttrUpdate = false
          this.$http.post('updateAttr',{id: this.updateAttrFormData.id, attrName: this.updateAttrFormData.name}).then((res)=>{
               console.log(res)
               if(res.data.status !== 200)  return this.$message.error('修改失败') 
               this.$message.success('修改成功')
               this.handleClick()
            })
        },
        addParamFormAction:function(){
          this.dialogVisibleParamAdd = true
        },
        addParamFormSubmit:function(){
          this.dialogVisibleParamAdd = false
          this.$http.post('addParam',{paramName: this.addParamFormData.name, cid: this.exportCid}).then((res)=>{
               console.log(res)
               if(res.data.status !== 200)  return this.$message.error('添加失败') 
               this.$message.success('添加成功')
               this.handleClick()
            })
        },
        updateParamFormAction:function(param){
          console.log(param)
          this.updateParamFormData.id = param.id
          this.updateParamFormData.name = param.paramName
          this.dialogVisibleParamUpdate = true
        },
        updateParamFormSubmit:function(){
          this.dialogVisibleParamUpdate = false
          this.$http.post('updateParam',{id: this.updateParamFormData.id, paramName: this.updateParamFormData.name}).then((res)=>{
               console.log(res)
               if(res.data.status !== 200)  return this.$message.error('修改失败') 
               this.$message.success('修改成功')
               this.handleClick()
            })
        },
        async removeAttrSubmit(id) {
           const confirmResult = await this.$confirm(
            '此操作将永久删除该参数，是否继续？','提示',{
                 confirmButtonText:'确认',
                 cancelButtonText:'取消',
                 type:'warning'
            }).catch(err => err)
            if(confirmResult !== 'confirm'){
              return this.$message.info("已取消删除")
            }
            await this.$http.delete('removeAttr/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this.handleClick()
            }) 
       },
       async removeParamSubmit(id) {
           const confirmResult = await this.$confirm(
            '此操作将永久删除该属性，是否继续？','提示',{
                 confirmButtonText:'确认',
                 cancelButtonText:'取消',
                 type:'warning'
            }).catch(err => err)
            if(confirmResult !== 'confirm'){
              return this.$message.info("已取消删除")
            }
            await this.$http.delete('removeParam/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this.handleClick()
            }) 
        },
        showInput(id) {
          this.inputVisible = true;
          this.addAttrOrParamId = id
          this.$nextTick(_ => {
            this.$refs.saveTagInput.$refs.input.focus();
          });
        },
        handleInputConfirmAttr:function(){
          this.inputVisible = false;
          if(this.inputValue !== null && this.inputValue !==''){
            this.$http.post('addAttrChild',{ childName: this.inputValue, aid: this.addAttrOrParamId}).then((res)=>{
                console.log(res)
                if(res.data.status !== 200)  return this.$message.error('添加失败') 
                this.$message.success('添加成功')
                this.handleClick()
              })
          }
          this.inputValue = '' 
         
        },
        handleInputConfirmParam:function(){
          this.inputVisible = false;
          if(this.inputValue !== null && this.inputValue !==''){
            this.$http.post('addParamChild',{ childName: this.inputValue, pid: this.addAttrOrParamId}).then((res)=>{
                console.log(res)
                if(res.data.status !== 200)  return this.$message.error('添加失败') 
                this.$message.success('添加成功')
                this.handleClick()
              })
           }
           this.inputValue = '' 
        },
        async removeParamChildItem(id){
            await this.$http.delete('removeParamChild/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this.handleClick()
            }) 
        },
        async removeAttrChildItem(id){
            await this.$http.delete('removeAttrChild/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this.handleClick()
            }) 
        }
     }
      
    }
    </script>
    
    <style scoped>
        .input-new-tag {
            width: 90px;
          }
    </style>
    