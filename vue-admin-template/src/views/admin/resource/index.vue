<template>
  <div class="app-container">
    <el-row :gutter="8">
        <el-col :span="16">
          <el-button type="primary" icon="el-icon-plus" size="medium" @click="handleAdd()">新增</el-button>
          <el-button type="primary" icon="el-icon-delete" size="medium" @click="handleMutilDelete()">删除</el-button>
        </el-col>
        <el-col :span="8">
          <el-row style="display: flex">
            <el-input
              placeholder="url"
              v-model="PageParam.keyword"
              style="margin-right: 20px"
              clearable>
            </el-input>
            <el-button type="primary" icon="el-icon-search" size="medium" @click="fetchData()">查询</el-button>
          </el-row>

        </el-col>
    </el-row>
    <el-table
      v-loading="listLoading"
      :data="resourceList"
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
      <el-table-column label="名称" width="160">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="url" width="160">
        <template slot-scope="scope">
          {{ scope.row.url }}
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="状态" width="80" align="center">
        <template slot-scope="scope">
          <el-tag type="success" v-if=" scope.row.status === 1">正常</el-tag>
          <el-tag type="info"v-else>禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="160">
        <template slot-scope="scope">
          <div class="tag-group">
            <el-tag type="" size="small" style="cursor: pointer" @click="handleEdit(scope.row)">编辑</el-tag>&nbsp;
            <el-tag type="danger" size="small" style="cursor: pointer" @click="handleDelete(scope.row)">删除</el-tag>&nbsp;
          </div>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="this.total>0" :total="this.total" :page.sync="this.PageParam.pageNo" :limit.sync="this.PageParam.pageSize" @pagination="this.fetchData" />

    <el-dialog :title="rtitle" :visible.sync="rshow" width="500px">
      <el-form :model="rform" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="rform.name"></el-input>
        </el-form-item>
        <el-form-item label="url">
          <el-input v-model="rform.url"></el-input>
        </el-form-item>
        <el-form-item label="是否禁用">
          <el-switch
            v-model="rform.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="1"
            :inactive-value="0">
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="rshow = false">取 消</el-button>
        <el-button type="primary" @click="saveInfoEdit()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import {listPage,createR,updateR,deleteR} from '@/api/resource'
  import Pagination from '@/components/Pagination'
    export default {
      components:{ Pagination },
      filters: {
        statusFilter(status) {
          const statusMap = {
            1: 'success',
            0: 'danger'
          }
          return statusMap[status]
        }
      },
      data(){
        return{
          checkedIds:[],
          resourceList:[],
          resourceForm:{

          },
          total: 0,
          PageParam:{
            pageNo: 1,
            pageSize: 10,
            keyword: ''
          },
          listLoading: true,
          rshow:false,
          rtitle:'',
          rform:{
            id:'',
            name:'',
            url:'',
            status:1,
            sort:1
          }
        }
      },
      created(){
        this.fetchData()
      },
      methods:{
        handleMutilDelete(){
          if (this.checkedIds.length === 0){
            this.$message({
              type: 'warning',
              message: '您没有选择要删除的角色'
            })
            return
          }
          this.$confirm('确定要删除您选中的用户吗？','是否继续?', '提示', {
            confirmButtonText: '删除',
            cancelButtonText: '算了吧',
            type: 'warning'
          }).then(() => {
            deleteR(this.checkedIds).then(res =>{
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
        handleDelete(row){
          this.$confirm('确定要删除'+row.name+'用户吗？','是否继续?', '提示', {
            confirmButtonText: '删除',
            cancelButtonText: '算了吧',
            type: 'warning'
          }).then(() => {
            deleteR([row.id]).then(res =>{
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
        handleEdit(row){
          this.rform=row
          this.rtitle='编辑'
          this.rshow=true
        },
        handleAdd(){
          this.rtitle='新增'
          this.rform.url=''
          this.rform.name=''
          this.rform.id=''
          this.rform.status=1
          this.rshow=true
        },
        fetchData(){
          listPage(this.PageParam).then(res=>{
            this.resourceList = res.data
            this.total=res.total
            this.listLoading = false
          })
        },
        handleSelectionChange(values){
          this.checkedIds=[]
          values.map(res => this.checkedIds.push(res.id))
        },
        saveInfoEdit(){
          if(this.rform.id){
            updateR(this.rform).then(res=>{
              this.$message({
                type: 'success',
                message: '操作成功!'

              });
              this.fetchData()
              this.rshow=false
            })
          }else{
            createR(this.rform).then(res=>{
              this.$message({
                type: 'success',
                message: '操作成功!'
              });
              this.fetchData()
              this.rshow=false
            })
          }

        }
      }
    }
</script>


