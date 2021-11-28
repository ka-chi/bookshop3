<template>
  <div class="content shopCart">
    <!-- table -->
    <div class="table">
      <el-table element-loading-text="正在为您拼命加载中..." :data="tableData" ref="multipleTable" style="width: 100%"
        @selection-change="getSelectChange" :close-on-click-modal="false" :close-on-press-escape="false"
        :header-cell-style="{background:'#f8f8f8',color:'#999'}">
        <el-table-column type="selection" width="75" align="center" />
        <el-table-column prop="shopImg" align="center" width="150" label="商品">
          <template slot-scope="scope">
            <img :src="scope.row.log" class="shopImg" alt="">
          </template>
        </el-table-column>
        <el-table-column prop="shop" align="center" label="图书名">
          <template slot-scope="scope">
            <span class="shop">{{scope.row.bookname}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="单价" align="center">
          <template slot-scope="scope">
            <span class="price">¥{{scope.row.price}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="number" label="数量" align="center">
          <template slot-scope="scope">
            <el-input v-model.number="scope.row.num" oninput="value=value.replace(/[^\d]/g,'')" autocomplete="off" style="width:140px" size="mini"
              @change="handleInput(scope.row)">
              <el-button size="mini" slot="prepend" @click="del(scope.row)"><i class="el-icon-minus"></i></el-button>
              <el-button slot="append" size="mini" @click="add(scope.row)"><i class="el-icon-plus"></i></el-button>
            </el-input>
          </template>
        </el-table-column>
        <el-table-column prop="count" label="小计" align="center">
          <template slot-scope="scope">
            <span class="count">¥{{scope.row.goodTotal}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <i class="el-icon-delete" style="font-size:18px;cursor: pointer;" @click="handleDelete(scope.$index, scope.row)"></i>
          </template>
        </el-table-column>
      </el-table>
  <br><br><br>
      <div class="submit">
        <span style="float: right">
          <label style="font-size:18px;color: #f30404;">总价：</label> <span>{{this.totalPrice}}</span>
          <br><br>
          <el-button @click="handleToOrder">提交订单</el-button>
        </span>
<!--        <van-submit-bar :price="totalPrice*100" :disabled='multipleSelection.length<=0' button-color='#bfa548' button-text="去结算" label='产品总额：'>-->
<!--          <p class="submitBar">-->
<!--            <span>继续购物</span>-->
<!--            <span>-->
<!--              共 <b>{{tableData.length}}</b> 件商品,-->
<!--              已选择 <b>{{multipleSelection.length}}</b> 件-->
<!--            </span>-->
<!--          </p>-->
<!--        </van-submit-bar>-->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.getShopcar();
  },
  data () {
    return {
      tableData: [],
      totalPrice: 0,
      multipleSelection: [],
      wait:false
    }
  },
  methods: {
    getShopcar(){
      axios.get("/api/shopcar/getALL/"+this.$store.state.id).then(res=>{
        this.tableData = res.data.data;
        this.totalPrice = 0;

        for(let i =0;i<this.tableData.length;i++){
          this.totalPrice+=this.tableData[i].goodTotal;
        }
      })
    },

    // 删除单个商品
    handleDelete (index, row) {
      this.$confirm('确定删除该商品？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 删除数组中指定的元素
        this.tableData.splice(index, 1)
        const shopcarId = parseInt(row.id);
        axios.delete("/api/shopcar/delete/"+shopcarId).then(res=>{
          console.log(row.id);
          console.log(res.data)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 数量变化触发
    handleInput (value) {

    },
    add (addGood) {

      // 输入框输入值变化时会变为字符串格式返回到js
      // 此处要用v-model，实现双向数据绑定
      if (typeof addGood.num === 'string') {
        addGood.num = parseInt(addGood.num)
      };
      addGood.num += 1
      addGood.goodTotal = (addGood.num * addGood.price).toFixed(2)// 保留两位小数
      this.handleSelectionChange(),
      this.handleNumberChange(addGood)
    },
    del (delGood) {
      if (typeof delGood.num === 'string') {
        delGood.number = parseInt(delGood.num)
      };
      if (delGood.num > 1) {
        delGood.num -= 1
        delGood.goodTotal = (delGood.num * delGood.price).toFixed(2)// 保留两位小数
        this.handleSelectionChange()
        this.handleNumberChange(delGood)
      }
    },
    handleSelectionChange () {
      this.totalPrice = 0
      // 此处不支持forEach循环，只能用原始方法了
      for (var i = 0; i < this.tableData.length; i++) {
        // 判断返回的值是否是字符串
        if (typeof this.tableData[i].goodTotal === 'string') {
          this.tableData[i].goodTotal = parseInt(this.tableData[i].goodTotal)
        };
        this.totalPrice += this.tableData[i].goodTotal
      }
    },
    getSelectChange(val){
      this.multipleSelection = val;
      },
    handleNumberChange(item) {
      if (!this.wait) {
        this.wait = true
        setTimeout(() => {
          // console.log(item);
          axios.post("/api/shopcar/update",{
              custid:item.cid,
              bookid:item.bookId,
              num:item.num
            }).then(res=>{
              // console.log(res.data);
            })
          this.wait=false
        }, 2000)
      }
    },
    handleToOrder(){
    const list = this.multipleSelection;

    axios.post("/api/order/send2order",list).then(res=>{
      if(res.data.code == 20001){
        this.$message({message:res.data.message,type:'error'})
      }else{
        this.$message({message:'下单成功，订单以移到[我的订单]中',type:'success'})
        window.location = location;
      }
    })

      // this.$message({message:'下单成功，订单以移到[我的订单]中',type:'success'})
    }

  }
}
</script>
<style scoped>
.shopCart {
  margin-bottom: 50px;
}
.shopCart .table {
    margin-top: 10px;
    position: relative;
    padding-bottom: 100px;
}
.shopCart .table ::v-deep .el-input__inner {
      text-align: center;
    }
.shopCart .table ::v-deep .el-input-group__append {
      padding: 0 15px;
    }
.shopCart .table ::v-deep .el-input-group__prepend {
      padding: 0 15px;
    }
.shopCart .table .shopImg {
      width: 100%;
    }
.shopCart .table .price,
    .shop {
      color: #000733;
    }
.shopCart .table .count {
      color: #bfa548;
    }
.shopCart .table ::v-deep .el-checkbox__inner {
      width: 20px;
      height: 20px;
      border-radius: 50%;
    }
.shopCart .table ::v-deep .el-checkbox__input.is-checked .el-checkbox__inner {
      background-color: #bfa548;
      border-color: #bfa548;
    }
.shopCart .table ::v-deep .el-checkbox__input.is-indeterminate .el-checkbox__inner {
      background-color: #bfa548;
      border-color: #bfa548;
    }
.shopCart .table ::v-deep .el-checkbox__inner::after {
      left: 7px;
      top: 3px;
    }
.shopCart .table  ::v-deep .el-checkbox__input.is-indeterminate .el-checkbox__inner::before {
      top: 7px;
    }
.shopCart .table .submit {
      position: absolute;
      right: 0;
      left: 0;
}
.shopCart .table .submit ::v-deep .van-submit-bar {
        position: absolute;
        top: 20px;
}
.shopCart .table .submit .van-submit-bar__bar {
          background-color: #f8f8f8;
          padding: 30px 20px 30px 28px;
        }
.shopCart .table .submit .submitBar {
  color: #757575;
  display: flex;
  align-items: center;
}
.shopCart .table .submit .submitBar b {
          color: #bfa548;
          margin: 0 5px;
        }

.shopCart .table .submit .submitBar span {
  display: flex;
  align-items: center;
}
.shopCart .table .submit .submitBar span:first-child {
  cursor: pointer;
}
.shopCart .table .submit .submitBar span:hover {
  color: #bfa548;
}

.shopCart .table .submit .submitBar span::after {
              display: inline-block;
              content: "";
              width: 1px;
              height: 12px;
              background: #ccc;
              margin: 0 10px;
            }
.shopCart .table .submit .submitBar span ::v-deep .van-submit-bar__price {
        color: #bfa548;
      }
.shopCart .table .submit .submitBar span ::v-deep .van-submit-bar__text {
        padding-right: 30px;
        color: #000733;
      }
</style>
