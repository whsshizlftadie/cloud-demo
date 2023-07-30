<template>
  <div class="app-container">

    <el-row :gutter="8">
      <el-row :gutter="90">
        <el-col :span="7">
          <el-button type="primary" icon="el-icon-plus" size="medium" @click="handleAdd()">新增用户</el-button>
          <el-button type="primary" icon="el-icon-delete" size="medium" @click="handleMutilDelete()">删除用户</el-button>
        </el-col>
        <el-col :span="7">
          <el-row style="display: flex">
            <el-input
              placeholder="账号/昵称"
              v-model="PageParam.keyword"
              style="margin-right: 20px"
              clearable>
            </el-input>
            <el-button type="primary" icon="el-icon-search" size="medium" @click="fetchData()">查询</el-button>
          </el-row>

        </el-col>

        <el-col :span="8">
          <el-row style="display: flex">
            <el-button type="primary" icon="el-icon-plus" size="small" @click="handleRoleAdd()">新增角色</el-button>
            <el-button type="primary" icon="el-icon-delete" size="small" @click="handleRoleMutilDelete()">删除角色</el-button>
          </el-row>
        </el-col>
      </el-row>

      <el-col :span="14">
        <el-table
          v-loading="listLoading"
          :data="list"
          element-loading-text="Loading"
          @selection-change="handleSelectionChange"
          border
          fit
          highlight-current-row
        >
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column label="账号" width="110">
            <template slot-scope="scope">
              {{ scope.row.username }}
            </template>
          </el-table-column>
          <el-table-column label="用户名" width="110" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.nickname }}</span>
            </template>
          </el-table-column>
          <el-table-column label="空间背景" width="110" align="center">
            <template slot-scope="scope">
              <el-link type="primary" :href="scope.row.spaceBackground" target="_blank">背景图片连接</el-link>
            </template>
          </el-table-column>
          <el-table-column label="个性签名" width="110" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.personalitySignature}}</span>
            </template>
          </el-table-column>
          <el-table-column class-name="status-col" label="用户状态" width="80" align="center">
            <template slot-scope="scope">
              <el-tag type="success" v-if=" scope.row.status === 1">正常</el-tag>
              <el-tag type="info"v-else>禁用</el-tag>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作" width="120">
            <template slot-scope="scope">
              <div class="tag-group">
                <el-tag type="" size="small" style="cursor: pointer" @click="handleEdit(scope.row)">编辑</el-tag>&nbsp;
                <el-tag type="danger" size="small" style="cursor: pointer" @click="handleDelete(scope.row)">删除</el-tag>&nbsp;
              </div>
            </template>
          </el-table-column>
        </el-table>
        <pagination v-show="total>0" :total="total" :page.sync="PageParam.pageNo" :limit.sync="PageParam.pageSize" @pagination="fetchData" />
      </el-col>

      <el-col :span="10">


        <el-table
          v-loading="listLoading"
          :data="roleSelect"
          element-loading-text="Loading"
          @selection-change="RoleSelectionChange"
          border
          fit
          highlight-current-row
        >
          <el-table-column
            type="selection"
            width="55">
          </el-table-column>
          <el-table-column label="角色代码" width="110">
            <template slot-scope="scope">
              {{ scope.row.code }}
            </template>
          </el-table-column>
          <el-table-column label="角色名称" width="110" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作" width="120">
            <template slot-scope="scope">
              <div class="tag-group">
                <el-tag type="" size="small" style="cursor: pointer" @click="handleRoleEdit(scope.row)">编辑</el-tag>&nbsp;
                <el-tag type="danger" size="small" style="cursor: pointer" @click="handleRoleDelete(scope.row)">删除</el-tag>&nbsp;
              </div>
            </template>
          </el-table-column>
        </el-table>
        <!--<pagination v-show="total>0" :total="total" :page.sync="PageParam.pageNo" :limit.sync="PageParam.pageSize" @pagination="fetchData" />-->
      </el-col>
    </el-row>



    <el-dialog :title="userFormTitle" :visible.sync="userFormShow" width="500px">
      <el-form :model="userForm" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="userForm.username"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="userForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="用户状态">
          <el-switch
            v-model="userForm.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="1"
            :inactive-value="0">
          </el-switch>
        </el-form-item>
        <el-form-item label="角色">
          <el-checkbox-group v-model="userForm.roleIds">
            <el-checkbox v-for="role in roleSelect" :label="role.id" :key="role.id">{{role.name}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userFormShow = false">取 消</el-button>
        <el-button type="primary" @click="saveUserInfoEdit()">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="roleFormTitle" :visible.sync="roleFormShow" width="500px">
      <el-form :model="roleForm" label-width="80px">
        <el-form-item label="角色编码">
          <el-input v-model="roleForm.code"></el-input>
        </el-form-item>
        <el-form-item label="角色名称">
          <el-input v-model="roleForm.name"></el-input>
        </el-form-item>
        <el-form-item :label="匹配路径">
          <el-checkbox-group v-model="roleForm.resourceIds">
            <el-checkbox v-for="resource in resourceSelect" :label="resource.id">{{resource.url}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="roleFormShow = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleInfoEdit()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPage,updateUserInfo,createUser,deletes} from '@/api/user'
import Pagination from '@/components/Pagination'
import {loadRoleIds,createRole,updateRole,deleteRole} from '@/api/role'
import {Rselect} from '@/api/resource'


export default {
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        0: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      resourceSelect:[],
      roleSelect: [],
      checkedIds: [],
      checkRoleIds:[],
      userFormShow: false,
      userFormTitle: '',
      roleFormShow:false,
      roleFormTitle: '',
      list: null,
      listLoading: true,
      total: 0,
      PageParam:{
        pageNo: 1,
        pageSize: 10,
        keyword: ''
      },
      userForm:{
        id: '',
        username: '',
        nickname: '',
        status: 1,
        roleIds: []
      },
      roleForm:{
        id: '',
        code: '',
        name: '',
        resourceIds: []
      }
    }
  },
  created() {
    this.fetchData()
    this.loadRoleIds()
    this.initResourceSelect()
  },
  methods: {
    handleRoleEdit(row){
      this.roleForm=row
      this.roleFormTitle='角色编辑'
      this.roleFormShow=true
    },
    handleMutilDelete(){
      if (this.checkedIds.length === 0){
        this.$message({
          type: 'warning',
          message: '您没有选择要删除的用户'
        })
        return
      }
      this.$confirm('确定要删除您选中的用户吗？','是否继续?', '提示', {
        confirmButtonText: '删除',
        cancelButtonText: '算了吧',
        type: 'warning'
      }).then(() => {
        deletes(this.checkedIds).then(res =>{
          this.$message({
            type: 'success',
            message: '操作成功!'

          });
          this.fetchData()
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    handleRoleMutilDelete(){
      if (this.checkRoleIds.length === 0){
        this.$message({
          type: 'warning',
          message: '您没有选择要删除的角色'
        })
        return
      }
      this.$confirm('确定要删除您选中的角色吗？','是否继续?', '提示', {
        confirmButtonText: '删除',
        cancelButtonText: '算了吧',
        type: 'warning'
      }).then(() => {
        deleteRole(this.checkRoleIds).then(res =>{
          this.$message({
            type: 'success',
            message: '操作成功!'

          });
          this.loadRoleIds()
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    handleEdit(row){
      this.userForm=row
      this.userFormTitle='用户编辑'
      this.userFormShow=true
    },
    handleAdd(){
      this.userFormTitle='用户创建'
      this.userForm.id=''
      this.userForm.nickname=''
      this.userForm.username=''
      this.userForm.roleIds=[]
      this.userFormShow=true
    },
    handleRoleAdd(){
      this.roleFormTitle='新增角色'
      this.roleForm.id=''
      this.roleForm.resourceIds=[]
      this.roleForm.name=''
      this.roleForm.code=''
      this.roleFormShow=true
    },
    fetchData() {
      this.listLoading = true
      listPage(this.PageParam).then(res => {
        this.list = res.data
        this.total=res.total
        this.listLoading = false
        // console.log(this.list,"===========================================")
      })
    },
    loadRoleIds(){
      loadRoleIds().then(res=>{
        this.roleSelect=res.data
        // console.log(this.roleSelect)
      })
    },
    saveUserInfoEdit(){
      if(this.userForm.id){
        updateUserInfo(this.userForm).then(res=>{
          this.$message({
            type: 'success',
            message: '更新成功!'
          });
          this.userFormShow=false
          this.fetchData();
        })
      }else{
        createUser(this.userForm).then(res=>{
          this.$message({
            type: 'success',
            message: '添加成功!'
          });
          this.userFormShow=false
          this.fetchData();
        })
      }
    },
    saveRoleInfoEdit(){
      if(this.roleForm.id){
        updateRole(this.roleForm).then(res=>{
          this.$message({
            type: 'success',
            message: '更新成功!'
          });
          this.roleFormShow=false
          this.loadRoleIds();
        })
      }else{
        createRole(this.roleForm).then(res=>{
          this.$message({
            type: 'success',
            message: '添加成功!'
          });
          this.roleFormShow=false
          this.loadRoleIds();
        })
      }
    },
    handleSelectionChange(values){
      this.checkedIds=[]
      values.map(res => this.checkedIds.push(res.id))
      // console.log(this.checkedIds)
    },
    RoleSelectionChange(values){
      this.checkRoleIds=[]
      values.map(res=>this.checkRoleIds.push(res.id))
      console.log(this.checkRoleIds)
    },
    handleDelete(row){
      this.$confirm('确定要删除'+row.username+'用户吗？','是否继续?', '提示', {
        confirmButtonText: '删除',
        cancelButtonText: '算了吧',
        type: 'warning'
      }).then(() => {
        deletes([row.id]).then(res =>{
          this.$message({
            type: 'success',
            message: '操作成功!'

          });
          this.fetchData()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    handleRoleDelete(row){
      this.$confirm('确定要删除'+row.name+'用户吗？','是否继续?', '提示', {
        confirmButtonText: '删除',
        cancelButtonText: '算了吧',
        type: 'warning'
      }).then(() => {
        deleteRole([row.id]).then(res =>{
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
          this.loadRoleIds()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },
    initResourceSelect(){
      Rselect().then(res=>{
        this.resourceSelect=res.data
        console.log(this.resourceSelect)
      })
    }
  }
}
</script>
