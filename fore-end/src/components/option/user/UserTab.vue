<template>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户列表</el-breadcrumb-item>
      </el-breadcrumb>

      <el-card style="line-height: 10px;">
        <el-row style="margin-bottom: 20px;">
        <el-col :span="6">
          <el-input v-model="userName" placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search" @click="selUsers()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="3">
          <el-button type="primary" @click="addUserClick()">添加用户</el-button>
        </el-col>
       </el-row>

       <el-table :data="tableData" border style="width: 100%">
        <el-table-column type="index" label="#"></el-table-column>
         <el-table-column prop="username" label="姓名"></el-table-column>
         <el-table-column prop="email" label="邮箱"></el-table-column>
         <el-table-column prop="mobile" label="电话"></el-table-column>
         <el-table-column prop="roleName" label="角色"></el-table-column>
         <el-table-column prop="msState" label="状态">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.msState" active-color="#13ce66"  inactive-color="#DDDDDD" @change="stateChange(scope.row)"></el-switch>
          </template>
         </el-table-column>
         <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="updateUserClick(scope.row.id)" type="primary" icon="el-icon-edit" size="mini"></el-button>
            <el-button @click="removeUser(scope.row.id)" type="danger" icon="el-icon-delete" size="mini"></el-button>
            <el-tooltip class="item" effect="dark" content="分配角色" placement="top-start">
              <el-button @click="updateRole(scope.row)" type="warning" icon="el-icon-setting" size="mini"></el-button>
            </el-tooltip>
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


    <!--添加用户-->
    <el-dialog title="添加用户" :visible.sync="dialogVisibleAdd"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="addFormRules" :model="addForm" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="mobile">
          <el-input v-model="addForm.mobile"></el-input>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleAdd = false">取 消</el-button>
       <el-button type="primary" @click="addUserSubmit()">确 定</el-button>
      </span>

    </el-dialog>

    <!--修改用户-->   
    <el-dialog title="添加用户" :visible.sync="dialogVisibleUpdate"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="addFormRules" :model="addForm" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="updateForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="updateForm.email"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="mobile">
          <el-input v-model="updateForm.mobile"></el-input>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleUpdate = false">取 消</el-button>
       <el-button type="primary" @click="updateUserSubmit()">确 定</el-button>
      </span>

    </el-dialog>   

    <!--分配角色-->
    <el-dialog title="分配角色" :visible.sync="dialogVisibleRole"  width="30%" style="line-height: 10px;">
      
      <el-form ref="form" :rules="roleFormRules" :model="roleForm" label-width="80px">
        <el-form-item label="当前用户  - ">
          <span style="float: left;">{{ roleForm.username }}</span>
        </el-form-item>
        <el-form-item label="当前角色  - ">
          <span style="float: left;">{{ roleForm.orderRole }}</span>
        </el-form-item>
        <el-form-item label="分配新角色 - " style="white-space:nowrap;">
            <el-select style="float: left; margin-left: 10px;" v-model="roleForm.newRoleL" placeholder="请选择">
                <el-option v-for="item in role" :label="item.roleName" :value="item.roleName"></el-option>
            </el-select>
        </el-form-item>
       </el-form>

      <span slot="footer" class="dialog-footer">
       <el-button @click="dialogVisibleRole = false">取 消</el-button>
       <el-button type="primary" @click="updateRoleSubmit()">确 定</el-button>
      </span>

    </el-dialog>

    </div>
      
  </template>

<script>
import '../../../assets/css/global.css'

    export default {
      name: 'UserTab',
      data(){
        var checkEmail = (rule, value, callback) => { }
        var checkMobile = (rule, value, callback) => {}

        return{
            pageNow:1,
            pageTotal:'',
            total:'',
            tableData: [],
            userName:'',
            dialogVisibleAdd: false,
            dialogVisibleUpdate: false,
            dialogVisibleRole: false,
            addForm:{
              username:'',
              password:'',
              email:'',
              mobile:'',
            },
            updateForm:{},
            addFormRules: {
                username: [
                     { required: true, message: '请输入姓名', trigger: 'blur' },
                     { min: 3, max:6, message: '长度在 3 到 6 个字符', trigger: 'blur' }
                ],
                password: [
                     { required: true, message: '请输入密码', trigger: 'blur' },
                     { min: 3, max:6, message: '长度在 3 到 6 个字符', trigger: 'blur' }
                ],
                mobile: [
                     { required: true, message: '请输入电话', trigger: 'blur' },
                ],
                email: [
                     { required: true, message: '请输入邮箱', trigger: 'blur' },
                 ]
            },
            roleFormRules: {
              role: [
                     { required: true, message: '请选择角色', trigger: 'blur' },
                 ]
            },
            roleForm:{
                id: '',
                username: '',
                orderRole: '',
                newRoleL: ''
            },
            role:{}
         }
      },
      created(){
        this.getUserList()
      },
      methods:{
        firstPage:function(){
          this.pageNow = 1
          console.log(this.pageNow)
          this.getUserList()
        },
        lastPage:function(){
          this.pageNow = this.pageTotal
          console.log(this.pageNow)
          this.getUserList()
        },
        downPage:function(){
          this.pageNow = this.pageNow-1<1?this.pageNow:this.pageNow-1
          console.log(this.pageNow)
          this.getUserList()
        },
        upPage:function(){
          this.pageNow = this.pageNow+1>this.pageTotal?this.pageTotal:this.pageNow+1
          console.log(this.pageNow)
          this.getUserList()
        },
        getUserList:function(){
           this.$http.post('getUsers', {pageNow:this.pageNow }).then((res)=>{
               console.log(res.data)
             if(res.data.status !== 200)  return this.$message.error('获取用户数据失败') 
                 this.pageNow = res.data.pageNow
                 this.pageTotal = res.data.pageTotal
                 this.total = res.data.total
                 this.tableData = res.data.data
            })
        },
        selUsers:function(){
          this.$http.post('getUsers', {pageNow:1, username: this.userName}).then((res)=>{
               console.log(res.data)
             if(res.data.status !== 200)  return this.$message.error('获取用户数据失败') 
                 this.pageNow = res.data.pageNow
                 this.pageTotal = res.data.pageTotal
                 this.total = res.data.total
                 this.tableData = res.data.data
            })
        },
        updateUserClick:function(id){
            this.dialogVisibleUpdate = true,
            this.$http.post('getUsers', {pageNow:1, id:id}).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('获取用户数据失败') 
               this.updateForm = res.data.data[0]
            })
        },
        updateUserSubmit:function(){
          this.dialogVisibleUpdate = false,
            this.$http.post('updateUser', {
              id: this.updateForm.id,
              email: this.updateForm.email,
              mobile: this.updateForm.mobile
            }).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('修改失败') 
               this.$message.success('修改成功')
               this.getUserList()
            })
        },
        addUserClick:function(){
          this.dialogVisibleAdd = true
          this.addForm={
              username:'',
              password:'',
              email:'',
              mobile:'',
            }
        },
        addUserSubmit:function(){
          this.dialogVisibleAdd = false,
            this.$http.post('addUser', {
              username: this.addForm.username,
              password: this.addForm.password,
              email: this.addForm.email,
              mobile: this.addForm.mobile
            }).then((res)=>{
               console.log(res.data)
               if(res.data.status !== 200)  return this.$message.error('添加失败') 
               this.$message.success('添加成功')
               this.getUserList()
            })
           
        },
       async removeUser(id) {
           const confirmResult = await this.$confirm(
            '此操作将永久删除该用户，是否继续？','提示',{
                 confirmButtonText:'确认',
                 cancelButtonText:'取消',
                 type:'warning'
            }).catch(err => err)
            if(confirmResult !== 'confirm'){
              return this.$message.info("已取消删除")
            }
            await this.$http.delete('removeUser/'+id).then((res)=>{
               if (res.status !== 200) return this.$message.error('删除失败') 
               this.$message.success('删除成功')
               this.getUserList()
            }) 
       },
       async stateChange(user){
          console.log(user)
          const id = user.id
          const state = user.msState
          await this.$http.put('updateUserState/'+id+'/'+state).then((res)=>{
               if (res.status !== 200) return this.$message.error('状态修改失败') 
               this.$message.success('状态修改成功')
               this.getUserList()
            }) 
       },
       async getRole(){
          await this.$http.post('getRoleAndPower').then((res)=>{
               console.log("role:")
               console.log(res)
               this.role = res.data
            }) 
       },
       updateRole(role){
           this.roleForm.id = role.id
           this.roleForm.username = role.username
           this.roleForm.orderRole = role.roleName
           this.dialogVisibleRole = true
           this.getRole()
       },
       async updateRoleSubmit(){
            this.dialogVisibleRole = false
            await this.$http.put('updateRole/'+this.roleForm.id+'/'+this.roleForm.newRoleL).then((res)=>{
               if (res.status !== 200) return this.$message.error('状态修改失败') 
               this.$message.success('状态修改成功')
               this.getUserList()
            }) 
       }
      }
    }
</script>
    
    <style scoped>

    </style>
    