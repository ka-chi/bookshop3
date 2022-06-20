<template>
  <div class="company-box">
    <!-- <topnav></topnav> -->
    <el-card class="box-card">
      <!-- 导航栏 -->
      <el-menu active-text-color="#5dd5c8" default-active="1" @select="handleSelect">
        <el-menu-item index="1">
          <i class="el-icon-shopping-cart-full"></i>
          <span slot="title">购物车</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-s-order"></i>
          <span slot="title">我的订单</span>
        </el-menu-item>

        <el-menu-item index="3">
          <i class="el-icon-money"></i>
          <span slot="title">充值中心</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-user"></i>
          <span slot="title">个人信息</span>
        </el-menu-item>

      </el-menu>
    </el-card>

    <el-card class="company-body">
      <div v-show="box == '1'">
<!--        <shopping-car></shopping-car>-->
      </div>
      <div v-show="box == '2'">
        <orders></orders>
      </div>
      <div v-show="box == '3'">
        <recharge></recharge>
      </div>
      <div v-show="box == '4'">
        <person></person>
      </div>
    </el-card>

  </div>
</template>

<script>
import orders from './core/orders.vue'
import person from './core/person.vue'
import recharge from './core/recharge.vue'
// import shoppingCar from './core/shoppingCar.vue'
import topnav from '../components/topnav.vue'
/**
   * description  公司页面
   */
export default {
  name: 'core',
  components: {
    orders,
    recharge,
    // shoppingCar,
    person,
    topnav
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
