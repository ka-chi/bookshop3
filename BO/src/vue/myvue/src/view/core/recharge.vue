<template>
  <div class="">
    <!-- 搜索框 -->
    <el-form :inline="true">

      <el-form-item label="账户余额:">
        <span style="color: red;font-weight: 600;">{{this.$store.state.money}}</span>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-refresh-left" style="background: #5dd5c8;" size="small"
          @click="handleSearch">
          刷新
        </el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-circle-plus-outline" style="background: #5dd5c8;" size="small"
          @click="recharge">充值
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
<!--    <el-table :data="tableData" style="width: 100%" v-loading="loading">-->

<!--      <el-table-column label="创建时间" prop="createTime" align="center" width="200">-->
<!--      </el-table-column>-->

<!--      <el-table-column prop="price" label="金额" width="200" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          <span style="color: red;font-weight: 600;">{{scope.row.balance}}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--      <el-table-column prop="context" label="描述" align="left">-->
<!--      </el-table-column>-->

<!--      &lt;!&ndash;    <el-table-column fixed="right" label="操作" width="200" align="center">-->
<!--        <template slot-scope="scope">-->

<!--        </template>-->
<!--      </el-table-column> &ndash;&gt;-->

<!--    </el-table>-->

<!--    <el-pagination @current-change="handleCurrentChange" background layout="prev, pager, next" :total="total"-->
<!--      :current-page="page" :page-size="rows" style="margin: 15px;">-->
<!--    </el-pagination>-->

  </div>
</template>

<script>/**
   * author        cyi
   * description
   */
import axios from "axios";

export default {
  name: 'recharge',
  data () {
    return {
      loading: false,
      tableData: [],
      page: 1,
      rows: 5,
      total: 0,
      search: {},
      sumBlance: '0.00'
    }
  },
  methods: {
    handleSearch () {
      this.page = 1
      this.getTable()
      this.getBalance()
    },
    getBalance () {
      // this.$axios.getBalance().then(res => {
      //   this.sumBlance = this.$common.returnFloat(res.data)
      // })
    },
    getTable () {
      this.loading = true
      const param = {
        ...this.search
      }
      param.page = this.page
      param.rows = this.rows
      // this.$axios.getRechargeList(param).then(res => {
      //   this.tableData = res.data.data
      //   this.total = res.data.total
      //   this.loading = false
      // })
    },
    handleCurrentChange (val) {
      this.page = val
      this.getTable()
    },
    recharge () {
      this.$prompt('请输入充值金额', '充值', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({
        value
      }) => {
        axios.post("/api/recharge/"+value).then(res=>{
          console.log("充值的地方")
          console.log(store.state.username)
        })

      })
    }
  },
  created () {
    this.getBalance()
    this.getTable()
  }
}
</script>

<style scoped>

</style>
