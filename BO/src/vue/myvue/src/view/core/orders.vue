<template>
  <div class="" style="height: 100%;" v-loading="loadingAll">
    <!-- 搜索框 -->
    <el-form :inline="true">

      <el-form-item label="状态:">
        <el-select @change="changeStatus" v-model="status" placeholder="选择状态" clearable>
          <el-option value="3" label="已取消"></el-option>
          <el-option value="1" label="待付款"></el-option>
          <el-option value="0" label="待发货"></el-option>
          <el-option value="4" label="待收货"></el-option>
          <el-option value="2" label="已收货"></el-option>
          <!-- <el-option value="5" label="完结"></el-option> -->
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" style="background: #5dd5c8;" @click="handleSearch">查询
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table :data="tableData" style="width: 100%">

      <el-table-column label="创建时间" prop="payTime" align="center" width="200">
      </el-table-column>

      <el-table-column prop="sumMoney" label="总价格" width="100" align="center">

      </el-table-column>

      <el-table-column label="状态" width="180" align="center">
        <template >
          <el-button size="mini" type="info" v-if="status == 3">已取消</el-button>
          <el-button size="mini" type="primary" v-if="status == 1">待付款</el-button>
          <el-button size="mini" type="success" v-if="status == 0">待发货</el-button>
          <el-button size="mini" type="success" plain v-if="status == 4">待收货</el-button>
          <el-button size="mini" type="success" v-if=" status== 2">已收货</el-button>
          <!-- <el-button size="mini" type="danger" v-if="scope.row.status == 5">完结</el-button> -->
        </template>
      </el-table-column>

      <el-table-column prop="address" label="收货地址">
      </el-table-column>


      <el-table-column prop="recordList[0].orderNo" label="订单编号">
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" v-if="status == 1" icon="el-icon-money"
            @click="orderPay(scope.row)">
            付款
          </el-button>

          <el-button size="mini" type="text" v-if="status == 0" icon="el-icon-document-delete"
            @click="orderCancel(scope.row)">
            取消订单
          </el-button>

          <el-button size="mini" type="text" v-if="status == 1 || status == 0"
            icon="el-icon-office-building" @click="handleAddressEdit(scope.row)">
            修改地址
          </el-button>

          <el-button size="mini" type="text" v-if="status == 4" icon="el-icon-s-promotion"
            @click="orderReceive(scope.row)">
            确认收获
          </el-button>

          <el-button size="mini" type="text" icon="el-icon-document-copy" @click="handleDetail(scope.row)">订单详情
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <el-pagination @current-change="handleCurrentChange" background layout="prev, pager, next" :total="total"
      :page-size="rows"  :current-page="page" style="margin: 15px;">
    </el-pagination>

    <el-dialog title="图书详情" :visible.sync="detailDialog" width="60%" :modal="false">

      <div v-for="item in detail" :key="item.id" style="margin: 15px;border:2px solid #f6f6f8;padding: 10px;">

        <el-row :gutter="20">
          <el-col :span="4">
            <div>
              <img :src="item.log" style="width: 100px;display: block;height: 100px;" />
            </div>
          </el-col>
          <el-col :span="6">
            <div style="line-height: 11px;margin:5px ;">
              <span style="font-size: 11px;">{{item.name}}</span>
              <br /><br />
              <span style="font-size: 11px;">作者：<span style="color: #00C2B3;"> {{item.author}}</span></span>
            </div>
          </el-col>
          <el-col :span="4">
            <span style="font-size: 13px;font-weight: 600;line-height: 100px;">单价：￥{{item.price}}</span>
          </el-col>
          <el-col :span="3">
            <span style="font-size: 13px;font-weight: 600;line-height: 100px;">数量：{{item.num}}</span>
          </el-col>
          <el-col :span="4">
            <span style="font-size: 13px;font-weight: 600;line-height: 100px;">金额：<span
                style="color: red;font-size: 15px;">￥{{item.sumMoney}}</span></span>
          </el-col>
          <el-col :span="3" style="margin-top: 35px;" v-if="status == 2 && item.isComment ==0">
            <el-button type="primary" icon="el-icon-edit" plain size="small" @click="handleComment(item)" >评论
            </el-button>
          </el-col>
          <el-col :span="3" style="margin-top: 35px;" v-if="status == 2 && item.isComment ==1">
            <el-button type="success" icon="el-icon-edit" plain size="small"  >已评论
            </el-button>
          </el-col>
        </el-row>
      </div>

      <el-dialog width="40%" title="评论" :visible.sync="commentDialog" append-to-body>

        <el-form label-width="80px">
          <el-form-item label="评分:">
            <el-rate v-model="comment.level" style="margin-top: 12px;"></el-rate>
          </el-form-item>

          <el-form-item label="内容:">
            <el-input v-model="comment.context" placeholder="请输入评论内容"></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="commentDialog = false">取 消</el-button>
          <el-button type="primary" @click="pushComment">提 交</el-button>
        </div>
      </el-dialog>

    </el-dialog>

  </div>
</template>

<script>
/**
   * author        cyi
   * description
   */
export default {
  name: 'orders',
  data () {
    return {
      loading: false,
      loadingAll: false,
      tableData: [],
      page: 1,
      rows: 1,
      total: 0,
      status:"1",
      detail: [],
      detailDialog: false,
      commentDialog: false,
      isComment: false,
      comment: {},
      row: null
    }
  },
  methods: {

    changeStatus(value){
      this.getTable();
      },

    handleSearch () {
      this.page = 1
      this.getTable()
    },
    getTable () {
      this.loading = true
      axios.post("/api/order/selectByCustomer",{
        status:this.status,
        custId:this.$store.state.id
      }).then(res=>{
        console.log(res)
        this.tableData = res.data.data;
        // console.log(this.tableData);
      })

    },


    handleCurrentChange (val) {
      this.page = val
      this.getTable()
    },
    orderPay (row) {
      const orderNo = row.recordList[0].orderNo;
      console.log(row)
      this.$confirm('确认要支付该订单吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loadingAll = true
        axios.post("/api/order/payOrder/"+orderNo).then(res => {
          this.getTable()
          this.$notify({
            title: '成功',
            message: '订单支付成功！',
            type: 'success'
          })
          this.loadingAll = false
        }).catch(error => {
          this.loadingAll = false
          console.log(error)
        })
      })
    },
    orderReceive (row) {
      console.log(row)
      this.$confirm('确认要收获该订单吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loadingAll = true
        const orderNo = row.recordList[0].orderNo;
        axios.post("/api/order/afterGetBook/"+orderNo).then(res => {
          this.getTable()
          this.$notify({
            title: '成功',
            message: '订单收获成功！',
            type: 'success'
          })
          this.loadingAll = false
        }).catch(error => {
          this.loadingAll = false
          console.log(error)
        })
      })
    },
    orderCancel (id) {
      console.log(id)
      this.$confirm('确认要取消该订单吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loadingAll = true
        axios.post("/api/order/cancelOrder/"+row.orderNo).then(res => {
          this.getTable()
          this.$notify({
            title: '成功',
            message: '订单已取消！',
            type: 'success'
          })
          this.loadingAll = false
        }).catch(error => {
          this.loadingAll = false
          console.log(error)
        })
      })
    },
    handleAddressEdit (row) {
      this.$prompt('请输入地址', '修改地址', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValue: row.address
      }).then(({
        value
      }) => {
        const param = {}
        param.id = row.id
        param.address = value
        const orderNo = row.recordList[0].orderNo;

        axios.post("/api/order/updateAddress/"+orderNo+'/'+param.address,).then(res => {
          this.getTable()
          this.$message({
            message: '修改成功！！',
            type: 'success'
          })
        })
      })
    },
    handleDetail (row) {
      console.log(row);
      this.detail = row.recordList;
      this.detailDialog = true
      // this.isComment = row.status === 4
      // this.$axios.getOrderDetail(row.id).then(res => {
      //   res.data.forEach(item => { item.totalPrice = item.price * item.number })
      //   this.detail = res.data
      //   this.detailDialog = true
      // })
    },
    handleComment (row) {
      this.comment = {}
      this.comment.bookId = row.bookId
      this.comment.ordersDetailId = row.ordersDetailId
      this.row = row
      this.commentDialog = true

    },
    pushComment () {
      console.log("==");
      console.log(this.comment.bookId)
      console.log(this.$store.state.id)
      axios.post("/api/comment/addComment",{
         bookId:this.comment.bookId,
         custId:this.$store.state.id,
         star:this.comment.level,
         comment:this.comment.context
      }).then(res => {
        this.$message({
          message: '评论成功！！',
          type: 'success'
        })
        this.row.isComment = 1
        this.commentDialog = false
      })
    }
  },

  created () {
    this.getTable()
  }
}
</script>

<style scoped>

</style>
