<template>
    <div>
      <div>
        <el-row :gutter="20">
          <el-col :span="7" v-for="item in mList" :key="item.id">
              <a :title="item.username" @click="handleListInfo(item)">
                <p class="list">
                  {{item.shopName}}
                </p>
              </a>
          </el-col>
        </el-row>
      </div>
      <el-dialog title="商家详情" :visible.sync="DialogVisible" width="50%" :modal="false">

      <el-descriptions class="margin-top" title="" :column="3" :size="size" border>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        用户名
      </template>
      {{mInfo.username}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
      {{mInfo.tel}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-s-shop"></i>
        店铺名称
      </template>
      {{mInfo.shopName}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        注册地址
      </template>
      {{mInfo.shopAddress}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-tickets"></i>
        注册类型
      </template>
      {{mInfo.type}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-money"></i>
        注册资金
      </template>
      {{mInfo.registerMoney}}
    </el-descriptions-item>
  </el-descriptions>
    </el-dialog>
    </div>
</template>

<style>
.list{
  width:249px;
  height:80px;
  line-height:80px;
  background-color:#DCDFE6;
  color: #000;
  font-size: 20px;
  white-space: nowrap;
  overflow: hidden;
}
</style>

<script>
export default {
  data () {
    return {
      mList: [],
      DialogVisible: false,
      mInfo: {},
      size: ''
    }
  },
  methods: {
    handleListInfo (list) {
      this.mInfo = list
      this.DialogVisible = true
    },
    getMList(){
      axios.get("/api/getShopper").then(res=>{
        this.mList = res.data.data;
      })
    }
  },
  created() {
    this.getMList();
  }
}
</script>
