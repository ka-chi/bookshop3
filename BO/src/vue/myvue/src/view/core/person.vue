<template>
<div>
  <el-descriptions class="margin-top" title="" :column="3" :size="size" border>

    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-user"></i>
        昵称
      </template>
      {{this.$store.state.name}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-mobile-phone"></i>
        手机号
      </template>
      {{this.$store.state.tel}}
    </el-descriptions-item>
    <el-descriptions-item>
      <template slot="label">
        <i class="el-icon-location-outline"></i>
        收货地址
      </template>
      {{this.$store.state.address}}
    </el-descriptions-item>

  </el-descriptions>
</div>
</template>

<script>
export default {
  data () {
    return {
      size: ''
    }
  }
}
</script>
