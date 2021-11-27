<template>
  <div class="company-box">
    <!-- <topnav></topnav> -->
    <el-card class="box-card">
      <!-- 导航栏 -->
      <el-menu active-text-color="#5dd5c8" default-active="1" @select="handleSelect">
        <el-menu-item index="1">
          <i class="el-icon-shopping-cart-full"></i>
          <span slot="title">订单管理</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-goods"></i>
          <span slot="title">商品管理</span>
        </el-menu-item>
      </el-menu>
    </el-card>

    <el-card class="company-body">
      <div v-show="box == '1'">
        <order></order>
      </div>
      <div v-show="box == '2'">
        <store></store>
      </div>
    </el-card>

  </div>
</template>

<script>
import order from './manage/order.vue'
import store from './manage/store.vue'
// import topnav from '../components/topnav.vue'
/**
   * description  公司页面
   */
export default {
  name: 'core',
  components: {
    order,
    store
  },
  computed: {

  },
  data () {
    return {
      box: '1'
    }
  },
  methods: {
    // 导航栏按钮点击
    handleSelect (key, keyPath) {
      this.box = key
    }
  },
  created () {}
}
</script>

<style scoped>
  .company-box {
    width: 1200px;
    margin: 0 auto;
    height: 600px;
    /* position: absolute; */
  }

  .box-card {
    width: 160px;
    float: left;
    /* margin-top: 20px; */
  }

  .el-menu {
    border-right: solid 0px #e6e6e6;
  }

  .company-body {
    margin-left: 180px;
    height: 100%;
    overflow: auto;
  }
</style>
