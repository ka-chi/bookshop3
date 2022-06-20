<template>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      fixed
      prop="username"
      label="用户名称"
      width="120">
    </el-table-column>
    <el-table-column
      prop="shopName"
      label="商家名称"
      width="150">
    </el-table-column>
    <el-table-column
      prop="registerMoney"
      label="注册资金"
      width="110">
    </el-table-column>
    <el-table-column
      prop="type"
      label="经营类型"
      width="120">
    </el-table-column>
    <el-table-column
      prop="tel"
      label="电话号码"
      width="120">
    </el-table-column>
    <el-table-column
      prop="shopAddress"
      label="注册地址"
      width="300">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="审核"
      width="100">
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row,'1')" type="text" size="small">通过</el-button>
        <el-button @click="handleClick(scope.row,'0')" type="text" size="small">不通过</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    handleClick (row,result) {
      // console.log(result)
      axios.get("/api/checkShop/"+row.id+"/"+result).then(res=>{
        if(res.data.code == 20001){
         this.$message({message:res.data.message,type:'error'})
        }else{
          this.$message({message:'审核完成',type:'success'})
          window.location = location;
        }
      })

    },

    getTable(){
      axios.get("/api/queryUnchecked").then(res=>{
        this.tableData = res.data.data.recordList;
      })
    }
  },

  data () {
    return {
      tableData: []
    }
  },
  created() {
    this.getTable()
  }
}
</script>
