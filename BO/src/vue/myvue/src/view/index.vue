<template>

  <div class="company-box" style="width: 1200px;margin: 0 auto;">
    <!-- <topnav></topnav> -->
    <div style="position: relative;">
      <div class="book-type">
        <div class="book-type-top">
          <span>图书分类</span>
        </div>
        <el-tree :data="$store.state.typeList" :props="defaultProps" accordion @node-click="handleNodeClick"></el-tree>
      </div>
    </div>

    <div style="margin-left: 50px;margin-top: 40px;margin-left: 250px;">
      <div style="width: 935px;" class="search-div">
        <el-card class="box-card">
          <el-input placeholder="搜索书名、 作者、 出版社" v-model="query.condition" class="input-with-select"
            style="height: 35px;line-height: 35px;">
            <el-button slot="append" icon="el-icon-search" @click="handleSearch"> 搜索</el-button>
          </el-input>
        </el-card>
      </div>

      <div>
        <el-breadcrumb separator-class="el-icon-arrow-right" style="margin: 20px 0;display: inline-block;">
<!--          <el-breadcrumb-item><a href="#" @click="handleBookTypeAll">全部分类</a></el-breadcrumb-item>-->
          <el-breadcrumb-item v-for="item in typeArr" :key="item">{{item}}</el-breadcrumb-item>
        </el-breadcrumb>

<!--        <div style="float: right;margin: 20px 0px;">-->
<!--          <el-input-number v-model="search.priceBegin" placeholder="起始价格" size="small" controls-position="right"-->
<!--            :min="0"></el-input-number>-->
<!--          <span>-</span>-->
<!--          <el-input-number v-model="search.priceEnd" placeholder="中止价格" size="small" controls-position="right" :min="0">-->
<!--          </el-input-number>-->
<!--        </div>-->
      </div>

      <div>
        <el-row :gutter="20">
          <el-col :span="6" v-for="item in bookList" :key="item.id">
            <div class="grid-content bg-purple" style="width: 150px;height: 250px;">
              <a :title="item.name" @click="handleBookInfo(item)">
                <img :src="item.log" style="width: 150px;display: block;height: 150px;" />
                <p
                  style="color: #000;font-size: 12px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;margin: 7px 0;">
                  {{item.name}}
                </p>
                <p style="color: #aaa;font-size: 12px;">{{item.author}}</p>
                <p style="color: #c30;">￥{{item.price}}</p>
              </a>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="pagination">
        <el-pagination
          background
          layout="total, prev, pager, next"
          :current-page="query.pageIndex"
          :page-size="query.pageSize"
          :total="pageTotal"
          @current-change="handleCurrentChange">
        </el-pagination>
      </div>
    </div>

    <el-dialog title="图书详情" :visible.sync="bookDialogVisible" width="60%" :modal="false">

      <div style="display:flex;margin-bottom: 50px;">
        <div style="width: 300px;">
          <img :src="bookInfo.log" style="width: 300px;display: inline-block;" />
        </div>

        <div style="flex:1">
          <span v-if="false">{{bookInfo.id}}</span>
          <span style="color: #323232;font-size: 15px;line-height: 20px;font-weight: 600;">{{bookInfo.bookname}}</span>
          <br />
          <br />
          <span>{{bookInfo.context}}</span>
          <el-divider></el-divider>
          <span style="font-size: 13px;">作者：<span style="color: #00C2B3;">{{bookInfo.author}}</span></span>
          <span style="font-size: 13px;margin-left: 30px;">出版社：<span
              style="color: #00C2B3;">{{bookInfo.publishing}}</span></span>
          <span style="font-size: 13px;margin-left: 30px;">销售额：<span
              style="color: #00C2B3;">{{bookInfo.saleNum}}</span></span>
          <br />
          <br />
          <span style="color: #e52222;">￥<span style="font-size: 26px;">{{bookInfo.price}}</span> </span>
          <br />
          <br />
          <span style="color: #e52222;">简介 <br /><span style="color: #00C2B3;">{{bookInfo.profile}}</span> </span>
          <br />
          <br />
          <el-button type="danger" icon="el-icon-shopping-cart-full" size="small" @click="handleShoppingCart">加入购物车
          </el-button>

        </div>
      </div>

      <el-divider content-position="center">{{bookCommentList.length == 0 ? '暂无评论':'用户评论'}}</el-divider>

      <div v-for="item in bookCommentList" :key="item.id">
        <span style="margin: 15px;font-size: 16px;font-weight: 500;">{{item.custName}}</span>
        <el-rate v-model="item.star" disabled style="display: inline-block;"></el-rate>
        <br />
        <span style="font-size: 10px;margin: 15px;">发布时间：<span>{{item.ctime}}</span></span>
        <br />
        <br />
        <span style="margin:15px;font-size: 14px;color: #414A60;">{{item.comment}}</span>

        <el-divider></el-divider>
      </div>
    </el-dialog>

  </div>
</template>

<script>
// import topnav from '../components/topnav.vue'
// import {
//   mapState
// } from 'vuex'
import axios from "axios";

/**
   * author
   * description   首页
   */
export default {
  name: 'index',
  // computed: {
  //   ...mapState(['bookTree'])
  // },
  components: {
    // topnav
  },
  data () {
    return {
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      bookList: [],

      search: {
        keyword: '',
        type: null,
        status: 1
      },
      query:{
        pageIndex:1,
        pageSize:4,
        typename:null,
        condition:null
      },
      pageTotal: 0,
      typeArr: [],
      bookDialogVisible: false,
      bookInfo: {},
      bookCommentList: [],
      // bookComentTotal: 0,
      // bookComentPage: 1
    }
  },
  methods: {
    handleNodeClick (data) {
       this.query.condition = null;
       this.query.typename = data.label;
       this.getData(this.query.pageIndex,this.query.pageSize,this.query.typename,this.query.condition);
    },
    handleSearch () {
      this.query.typename = null;
      this.getData(this.query.pageIndex,this.query.pageSize,this.query.typename,this.query.condition);
    },
    handleBookInfo (book) {
      this.bookInfo = book
      this.bookDialogVisible = true
      this.bookComentPage = 1
      this.getComments()
    },
    handleBookTypeAll () {
      this.typeArr = []
      this.search.type = null
      this.getBooks()
    },

    getData(currentPage,pageSize,typename,condition){
      axios({
        method:'post',
        url:'/api/book/queryByCustomer',
        data:{
          pageIndex:currentPage,
          pageSize:pageSize,
          typename:typename,
          condition:condition
        },
      })
        .then(res =>{
          console.log(res);
          this.bookList = res.data.data.recordList;
          this.pageTotal = res.data.data.count;
        })
    },
    handleCurrentChange(value){
      this.query.pageIndex = value;//获得当前页码
      this.getData(this.query.pageIndex,this.query.pageSize,this.query.typename,this.query.condition);
    },
    getComments () {
      const param = {}
      param.page = this.bookComentPage
      param.bookId = this.bookInfo.id
      axios.get("/api/comment/showComment/"+param.bookId).then(res => {
        this.bookCommentList = res.data.data
        console.log(res.data)
        // this.bookComentTotal = res.data.total
      })
    },
    handleShoppingCart () {
      if(this.$store.state.id != 0){
        axios.post("/api/shopcar/add",{
          bookid:this.bookInfo.id,

          custid:this.$store.state.id,
          num:1
        }).then(res=>{
          this.$message({message:"加入购物车成功",type:"success"})
        })
      }else {
        this.$message({message:"请先登录",type:"warning"})
      }
    }

  },
  created () {
    this.getData(this.query.pageIndex,this.query.pageSize)
  }
}
</script>

<style scoped>
  .book-type {
    width: 200px;
    height: 500px;
    border: #00C2B3 1px solid;
    margin-top: 50px;
    border-radius: 5px;
    margin-bottom: -50px;
    position: absolute;
  }

  .book-type-top {
    height: 40px;
    text-align: center;
    line-height: 40px;
    background: #00C2B3;
    color: white;
    font-weight: 700;
    font-size: 19px;
    /*margin-top: -1px -1px 0 0;*/
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
  }
</style>
