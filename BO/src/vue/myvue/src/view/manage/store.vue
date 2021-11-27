<template>
    <!--增删改查-->
  <div>
    <el-button type="success" size="small" @click="add">添加图书</el-button>
    <el-card class="box-card" >
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" align="center" v-if="false">
        </el-table-column>
        <el-table-column prop="bookname" label="书名" align="center">
        </el-table-column>
        <el-table-column prop="author" label="作者" align="center">
        </el-table-column>
        <el-table-column prop="publishing" label="出版社" align="center">
        </el-table-column>
        <el-table-column prop="price" label="价格" align="center">
        </el-table-column>
        <el-table-column prop="inventory" label="库存" align="center">
        </el-table-column>
        <el-table-column prop="typename" label="分类" align="center">
        </el-table-column>

        <el-table-column label="操作" fixed="right" align="center">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="remove(scope.row.id, scope.row)" style="margin-left:0px">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
<!--      <span v-if="this.tableData.length==0">暂时没有图书</span>-->
    </el-card>
    <!--弹窗-->
    <el-dialog :title="dialogTitle" width="50%" :visible.sync="iconFormVisible">
      <el-form :inline="true" :model="bookInfo" class="demo-form-inline">
        <el-form-item label="书名">
          <el-input v-model="bookInfo.bookname" placeholder="书名"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="bookInfo.author" placeholder="作者"></el-input>
        </el-form-item>
        <el-form-item label="出版社">
          <el-input v-model="bookInfo.publishing" placeholder="出版社"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="bookInfo.price" placeholder="书号"></el-input>
        </el-form-item>
        <el-form-item label="图片">
          <el-input v-model="bookInfo.log" placeholder="图片"></el-input>
        </el-form-item>
        <el-form-item label="库存">
          <el-input v-model="bookInfo.inventory" placeholder="库存"></el-input>
        </el-form-item>
        <el-form-item label="分类">
<!--          <el-input v-model="bookInfo.typename" placeholder="分类"></el-input>-->
          <template>
            <el-select v-model="bookInfo.typename" filterable placeholder="请选择">
              <el-option
                v-for="item in typeList"
                :key="item.typename"
                :label="item.typename"
                :value="item.typename">
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="bookInfo.profile" placeholder="简介"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="iconFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitUser()">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name: 'test',
  data () {
    return {
      iconFormVisible: false,
      bookInfo: {},
      dialogTitle: '增加',
      rowIndex: null,
      tableData: [],
      typeList:[],
      value:''
    }
  },
  created () {
    this.getShopBook(this.$store.state.id);
    this.getTypeList();
  },
  methods: {
    // ...mapActions([
    // ]),
    // 增加
    add () {
      this.dialogTitle = '增加'
      this.userInfo = {}
      this.iconFormVisible = true
    },
    // 编辑
    handleEdit (index, row) {
      this.dialogTitle = '编辑'
      this.userInfo = row
      this.iconFormVisible = true
      this.rowIndex = index
    },
    // 弹窗确定
    submitUser () {
      if (this.dialogTitle === '编辑') {
        this.tableData.splice(this.rowIndex, 1, this.bookInfo)
        this.iconFormVisible = false;
        return
      }
      if(this.dialogTitle === '增加'){
        //这里有改
        axios.post("/api/book/add/"+this.$store.state.id,this.bookInfo).then(res=>{
          console.log(res.data);
          this.$message({message:'图书上架成功',type:'success'});
        })
      }
      this.tableData.splice(0, 0, this.bookInfo)
      this.iconFormVisible = false
    },
    // 删除
    remove (index, row) {
      this.$confirm(`确定删除${row.bookname}吗?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'error'
      }).then(() => {
        console.log("===");
        console.log(index)
        axios.post("/api/book/delete/"+index);
        this.$message({message:'删除成功',type:'success'});
        this.tableData.splice(index, 1)
        this.getShopBook(this.$store.state.id);
      }).catch(()=>{})
    },
    getShopBook(id){
      axios.post("/api/book/queryByShopper/"+this.$store.state.id).then(res => {this.tableData  = res.data.data})
    },
    getTypeList(){
      axios.get("/api/type/getTypeByLevel/"+3).then(res => {this.typeList  = res.data.data})
    }
  }
//   computed: {
//     ...mapGetters({
//     })
//   }
}

</script>
