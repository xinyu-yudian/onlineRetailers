<template>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
      </el-breadcrumb>

      <el-card style="line-height: 10px;">
        <el-row style="margin-bottom: 20px;">
          <el-col :span="2">
             <el-button type="primary" @click="addRoleAction()">添加角色</el-button>
          </el-col>
        </el-row>

        <el-table :data="tableData" border stripe>
           <!-- 展开列 -->
           <el-table-column type="expand">
                    <!-- 作用域插槽 -->
                    <template slot-scope="scope">
                        <el-row v-for="(item1,i1) in scope.row.children" :key="item1.id" :class="['bd-bottom',i1===0?'bd-top':'','v-center']">
                            <!-- 一级权限 -->
                            <el-col :span="5" >
                                <el-tag closable> {{item1.powerName}}</el-tag><i style="position: relative; left: 95px;"  class="el-icon-caret-right"></i>
                            </el-col>
                            <!-- 二级和三级权限 -->
                            <el-col :span="19">
                                <el-row v-for="(item2,i2) in item1.children" :key="item2.id" :class="[i2===0?'':'bd-top','v-center']">
                                    <el-col :span="6">
                                        <el-tag  closable type="success" >{{item2.powerName}}</el-tag><i style="position: relative; left: 95px;" class="el-icon-caret-right"></i>
                                    </el-col>
                                    <el-col :span="15">
                                        <el-tag 
                                           closable
                                           type="warning" v-for="(item3) in item2.children" :key="item3.id">
                                            {{item3.powerName}}
                                        </el-tag>
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>

                    </template>
                </el-table-column>
         <el-table-column type="index" label="#"></el-table-column>
         <el-table-column prop="roleName" label="角色名称"></el-table-column>
         <el-table-column prop="roleDesc" label="角色描述"></el-table-column>
         <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="updateRoleAction(scope.row)"  type="primary" icon="el-icon-edit" size="mini">修改</el-button>
            <el-button @click="removeRole(scope.row.id)"  type="danger" icon="el-icon-delete" size="mini">删除</el-button>
            <el-button @click="getTreePowers(scope.row)" type="warning" icon="el-icon-setting" size="mini">分配权限</el-button>
          </template>
         </el-table-column>
      </el-table>

    </el-card>


    <!-- 分配权限的对话框 -->
    <el-dialog title="分配权限" :visible.sync="setRightDialogVisable"  width="40%" style="line-height: 10px;">
        <el-tree :data="allRole" 
                 :props="haveRole" 
                  show-checkbox 
                  node-key="id" 
                  default-expand-all
                  :default-checked-keys="defKeys" 
                  ref="treeRef" style="margin-left: 20px;">
         </el-tree>
             <span slot="footer" class="dialog-footer" style="line-height: 10px;">
                 <el-button @click="setRightDialogVisable = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </span>
         </el-dialog>

      <!--添加角色-->
    <el-dialog title="添加角色" :visible.sync="dialogVisibleAdd"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="addFormRules" :model="addRole" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="addRole.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="addRole.roleDesc"></el-input>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleAdd = false">取 消</el-button>
       <el-button type="primary" @click="addRoleSubmit()">确 定</el-button>
      </span>

    </el-dialog>

      <!--修改角色-->
      <el-dialog title="修改角色" :visible.sync="dialogVisibleUpdate"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="addFormRules" :model="updateRole" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="updateRole.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="updateRole.roleDesc"></el-input>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleUpdate = false">取 消</el-button>
       <el-button type="primary" @click="updateRoleSubmit()">确 定</el-button>
      </span>

    </el-dialog>

    </div>   
  </template>
  
    <script>
    export default {
      name: 'RoleTab',
      data(){
      return{
        tableData: [],
        allRole:[],
        defKeys:[],
        haveRole:{
          label:'powerName',
          children: 'children'
        },
        setRightDialogVisable: false,
        dialogVisibleAdd: false,
        dialogVisibleUpdate: false,
        addRole:{
          roleName: '',
          roleDesc: ''
        },
        updateRole:{
          id:'',
          roleName: '',
          roleDesc: ''
        },
        addFormRules: {
            roleName: [
                { required: true, message: '角色名称', trigger: 'blur' },
            ],
            roleDesc: [
                { required: true, message: '请简单描述该角色', trigger: 'blur' },
            ]
        }
      }
    },
      created(){
        this. getRoleAndPower()
      },
       methods:{
        async getRoleAndPower(){
          await this.$http.post('getRoleAndPower').then((res)=>{
               console.log(res)
                 this.tableData = res.data
            })
        },
        addRoleClick:function(){

        },
        async getTreePowers(role){
          await this.$http.post('treePowers').then((res)=>{
               console.log(res)
                 this.allRole = res.data
            })
            this.defKeys = []
            this.getLeafKeys(role, this.defKeys)
            
            this.setRightDialogVisable = true
        },
        getLeafKeys (node, arr) {
          if (!node.children) {
               return arr.push(node.id)
          }
          node.children.forEach(item => {
               this.getLeafKeys(item, arr)
          })
         },
         addRoleAction:function(){
           this.dialogVisibleAdd = true
         },
         addRoleSubmit:function(){
           this.dialogVisibleAdd = false
           this.$http.post('addRole', {
              roleName: this.addRole.roleName,
              roleDesc: this.addRole.roleDesc,
            }).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('添加失败') 
               this.$message.success('添加成功')
               this. getRoleAndPower()
            })
         },
         updateRoleAction:function(role){
           this.updateRole = role
           this.dialogVisibleUpdate = true
         },
         updateRoleSubmit:function(){
           this.dialogVisibleUpdate = false
           this.$http.post('updateRole', {
              id: this.updateRole.id,
              roleName: this.updateRole.roleName,
              roleDesc: this.updateRole.roleDesc,
            }).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('修改失败') 
               this.$message.success('修改成功')
               this. getRoleAndPower()
            })
         },
         async removeRole(id) {
           const confirmResult = await this.$confirm(
            '此操作将永久删除该角色，是否继续？','提示',{
                 confirmButtonText:'确认',
                 cancelButtonText:'取消',
                 type:'warning'
            }).catch(err => err)
            if(confirmResult !== 'confirm'){
              return this.$message.info("已取消删除")
            }
            await this.$http.delete('removeRole/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this. getRoleAndPower()
            }) 
       },
       }
    }
    </script>
    
    <style scoped>
        .el-tag{
          margin: 7px;
          position: relative;
          left: 100px;
        }

        .bdtop{
          border-top: 1px solid #eee;
        }

        .bdbottom{
          border-bottom: 1px solid #eee;
        }
    </style>
    