<template>
  <div class="company-box">
    <el-card class="box-card">
      <el-menu active-text-color="#5dd5c8" default-active="1" @select="handleSelect">
        <el-menu-item index="1">
          <i class="el-icon-s-custom"></i>
          <span slot="title">商家审核</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-s-order"></i>
          <span slot="title">商家列表</span>
        </el-menu-item>
      </el-menu>
    </el-card>

    <el-card class="company-body">
      <div v-show="box == '1'">
        <mcheck></mcheck>
      </div>
      <div v-show="box == '2'">
        <mlist></mlist>
      </div>
    </el-card>

  </div>
</template>

<script>
import mcheck from '../view/supe/mcheck.vue'
import mlist from '../view/supe/mlist.vue'

export default {
  name: 'sindex',
  components: {
    mcheck,
    mlist
  },
  computed: {

  },
  data () {
    return {
      box: '1'
    }
  },
  methods: {
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
    margin-top: 50px;
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
