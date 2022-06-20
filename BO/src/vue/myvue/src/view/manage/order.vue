<template>
  <div class="merchantOrderManage" v-loading="loadingAll">
    <el-table :data="tableNewData" :span-method="objectSpanMethod" border style="width: 100%; margin-top: 20px" :cell-class-name="tableRowClassName" :header-cell-style="isCenter">
      <el-table-column prop="orderNo" label="订单号" width="140" align="center" fixed> </el-table-column>
      <el-table-column label="商品信息" width="350" fixed>
        <template slot-scope="scope">
          <div class="info_box">
            <div class="info_box_img" @click="deleteForm(scope.row)">
              <img :src="scope.row.log" alt="" />
            </div>
            <div class="info_describe">
              <p class="text">
                <span>图书名：{{ scope.row.bookname }}</span>
              </p>
              <p class="attr">
                <span v-for="(itm, iid) in scope.row.goodsAttr" :key="iid">
                  <span>{{ itm.attrName }}：{{ itm.attrValue }}</span>
                </span>
              </p>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="price" label="单价(元)" align="center" width="90"> </el-table-column>
      <el-table-column prop="num" label="购买数量" align="center" width="80"> </el-table-column>
      <el-table-column prop="sumMoney" label="支付金额(元)" align="center" width="140"> </el-table-column>
      <el-table-column prop="custName" label="用户名称" align="center" width="110"> </el-table-column>
      <el-table-column prop="tel" label="联系方式" align="center" width="115"> </el-table-column>
      <el-table-column prop="address" label="用户地址" width="160"> </el-table-column>
      <el-table-column prop="payTime" label="下单时间" align="center" width="160"></el-table-column>
      <el-table-column label="订单状态" align="center" width="80">
        <template slot-scope="scope">
          <div class="tip-tag">
            <el-tag type="info" v-if="scope.row.status == 3">已取消</el-tag>
            <el-tag v-if="scope.row.status == 1">待付款</el-tag>
            <el-tag type="primary" v-if="scope.row.status == 0">已付款</el-tag>
            <el-tag type="danger" v-if="scope.row.status == 4">待收货</el-tag>
            <el-tag type="warning" v-if="scope.row.status == 2">已收货</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="140" fixed="right">
        <template slot-scope="scope">
          <el-button type="text" @click="deleteForm(scope.row)" size="primary">发货</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination @current-change="handleCurrentChange" :current-page="currentPage" :page-size="pagesize" layout="total, prev, pager, next, jumper" :total="total"> </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'merchantOrderManage',
  components: {},
  data () {
    return {
      loadingAll:false,
      tableNewData: [],
      hoverOrderArr: [],
      OrderIndexArr: [],
      tableData: [],
      total: 0,
      currentPage: 1, // 初始页
      pagesize: 10 // 每页的数据,

    }
  },
  // mounted () {
  //   setTimeout(() => {
  //     this.getNewTableData()
  //     this.getOrderNumber()
  //   },200)
  //
  // },

  methods: {
    deleteForm(row){
      // console.log(row)
      this.$confirm('确认要发货吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.loadingAll = true
        axios.post("/api/order/sendBooks/"+row.orderNo).then(res=>{
          // this.getTable();

        })
        setTimeout(() => {
          // this.getNewTableData()
          // this.getOrderNumber()

          window.location = location
          this.$notify({
            title: '成功',
            message: '已发货！',
            type: 'success'
          })
        },500)
      }).catch(
        error => {
          this.loadingAll = false
          console.log(error)
        })
    },

    // 初始页currentPage
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    // 表格内容居中显示
    isCenter ({ row, column, rowIndex, columnIndex }) {
      return 'text-align:center'
    },
    // 获取相同编号的数组
    getOrderNumber () {
      const OrderObj = {}
      this.tableNewData.forEach((element, index) => {
        element.rowIndex = index
        if (OrderObj[element.orderNo]) {
          OrderObj[element.orderNo].push(index)
        } else {
          OrderObj[element.orderNo] = []
          OrderObj[element.orderNo].push(index)
        }
      })
      // 将数组长度大于1的值 存储到this.OrderIndexArr（也就是需要合并的项）
      for (const k in OrderObj) {
        if (OrderObj[k].length > 1) {
          this.OrderIndexArr.push(OrderObj[k])
        }
      }
      console.log(this.OrderIndexArr, 'OrderIndexArr')
    },
    // 合并单元格
    objectSpanMethod ({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0  || columnIndex === 5 || columnIndex === 6 || columnIndex === 7 || columnIndex === 8 || columnIndex === 9 || columnIndex === 10) {
        for (let i = 0; i < this.OrderIndexArr.length; i++) {
          const element = this.OrderIndexArr[i]
          for (let j = 0; j < element.length; j++) {
            const item = element[j]
            if (rowIndex === item) {
              if (j === 0) {
                return {
                  rowspan: element.length,
                  colspan: 1
                }
              } else if (j !== 0) {
                return {
                  rowspan: 0,
                  colspan: 0
                }
              }
            }
          }
        }
      }
    },
    tableRowClassName ({ row, rowIndex }) {
      const arr = this.hoverOrderArr
      for (let i = 0; i < arr.length; i++) {
        if (rowIndex === arr[i]) {
          return 'hovered-row'
        }
      }
    },
    getNewTableData () {
      // if(this.tableData.length>0){console.log("sasdad")}
      const arr = this.tableData
      if(!arr){return}
      this.tableData.map((res) => {
        this.tableNewData.push(res.recordList)
      })

      this.tableNewData = this.tableNewData.flat()
    }
    ,
    getTable() {

      axios.post("/api/order/selectByShopper",{
        status:'0',
        custId:this.$store.state.id
      }).then(res=>{
        // console.log(res.data.data)
        this.tableData = res.data.data

      });
  }
  },
  created() {
    this.loadingAll=true
    this.getTable();
      setTimeout(() => {
        this.getNewTableData()
        this.getOrderNumber()
        this.loadingAll = false
      },500)
}
 }


</script>
<style scoped>
.merchantOrderManage {
  background: #fff;
  width: 100%;
  left: 40px;
  right: 40px;
  margin: 0 auto;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 4px;
}
.merchantOrderManage .info_box{
    display: flex;
    align-items: center;
    margin: 0 10px;
}

.merchantOrderManage .info_box .info_box_img{
    cursor: pointer;
      width: 50px;
      flex: 0 0 50px;
      margin-right: 6px;
}
.merchantOrderManage .info_box .info_box_img img{
    width: 50px;
        height: 50px;
}
.merchantOrderManage .info_box .info_describe .text{
margin-bottom: 6px;
}
.merchantOrderManage .info_box .info_describe .attr{
color: #9e9e9e;
}
.merchantOrderManage .info_box .info_describe .attr span{
     margin-right: 6px;
}
.el-tag {
  width: 100%;
  height: 36px;
  line-height: 36px;
}
.tip-tag .el-tag{
    text-align: center;
}
</style>
