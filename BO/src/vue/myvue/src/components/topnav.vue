<template>
  <div class="top-nav">
    <div style="width: 1200px;margin: 0 auto;">

      <el-menu class="subject-nav" default-active="1" mode="horizontal" background-color="#545c64" text-color="#ffffff"
        active-text-color="#5dd5c8" @select="handleSelect">

        <el-menu-item index="8" disabled>
          <span slot="title" class="title-nav">图书商城</span>
        </el-menu-item>

        <el-menu-item index="1" v-if="this.$store.state.authentic == 1 || this.$store.state.authentic == 9">主页</el-menu-item>


        <el-menu-item index="2"  v-if="this.$store.state.authentic == 2">管理</el-menu-item>
        <el-menu-item index="3" v-if="this.$store.state.authentic == 1 || this.$store.state.authentic == 9">
          个人中心
        </el-menu-item>
        <el-submenu index="0" class="my-blck" v-if="this.$store.state.authentic == 1 || this.$store.state.authentic == 2">

          <template slot="title">
            <div style="display: inline-block;">
              <i class="el-icon-user-solid"></i>
              <!-- {{user.username}} -->
              <!-- <el-avatar size="small" :src="user.avatar"></el-avatar> -->
            </div>
          </template>
          <el-menu-item index="0-1" v-if="this.$store.state.authentic == 1">修改收货地址</el-menu-item>
          <el-menu-item index="0-2">修改密码</el-menu-item>
          <el-menu-item index="0-3">退出登录</el-menu-item>
        </el-submenu>

        <el-menu-item index="9" class="my-blck" style="float:right" v-if="this.$store.state.authentic == 9">
          <router-link to="/login">
            <el-button type="info" round size="mini" style="background: #5dd5c8;">登录</el-button>
          </router-link>
        </el-menu-item>
        <el-menu-item index="10" class="my-blck" style="float:right" v-if="this.$store.state.authentic == 9">
          <router-link to="/Slogin">
            <el-button type="info" round size="mini" style="background: #5dd5c8;">我是商家</el-button>
          </router-link>
        </el-menu-item>
        <el-menu-item index="5" style="float:right" v-if="!isLogin"><i class="el-icon-s-custom"></i>{{showUserName}}</el-menu-item>

      </el-menu>
    </div>

    <el-dialog title="个人信息" :visible.sync="dialogInfo" width="30%">
      <el-form label-position="left" label-width="75px">

        <el-form-item label="收货地址:">
          <el-input v-model="address" prefix-icon="el-icon-s-custom" class="login-subject-input"
            placeholder="请输入你的收货地址"></el-input>
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editUserInfo()">修改</el-button>
      </span>
    </el-dialog>

    <el-dialog title="修改密码" :visible.sync="dialogPassword" width="30%">
      <el-form label-position="left" label-width="75px">
        <el-form-item label="原密码:">
          <el-input v-model="pw.oldPw" show-password prefix-icon="el-icon-lock" class="login-subject-input"
            placeholder="请输入原密码"></el-input>
        </el-form-item>

        <el-form-item label="新密码:">
          <el-input v-model="pw.newPw" show-password prefix-icon="el-icon-lock" class="login-subject-input"
            placeholder="请输入新密码"></el-input>
        </el-form-item>

        <el-form-item label="确认密码:">
          <el-input v-model="pw.newPwAgain" show-password prefix-icon="el-icon-lock" class="login-subject-input"
            placeholder="请确认新密码"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editUserPassword()">修改</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import Cookie from 'js-cookie'
/**
   * description  顶部导航栏
   */
export default {
  name: 'topnav',
  // computed: {
  //   ...mapState(['user'])
  // },

  data () {
    return {
      dialogInfo: false,
      dialogPassword: false,
      activeIndex: '1',
      address:'q',
      pw: {
        oldPw: '',
        newPw: '',
        newPwAgain: ''
      },
      isLogin: true
    }
  },
  created () {
    this.Login()
  },
  // mounted () {
  //   // window.location.reload()
  // },
  computed: {
    showUserName () {
      return Cookie.get('username')
    }
  },
  methods: {
    //   ...mapMutations({
    //     changeUser: 'changeUser'
    //   }),

    // nav点击触发
    Login () {
      if (Cookie.get('username')) {
        this.isLogin = false
      } else {
        this.isLogin = true
      }
      console.log(this.isLogin)
    },
    handleSelect (key, keyPath) {
      switch (key) {
        case '1':
          if (this.$route.path !== '/index') {
            this.$router.push({
              path: '/'
            })
          }
          break
        case '2':
          if (this.$route.path !== '/manage') {
            this.$router.push({
              path: '/manage'
            })
          }
          break
        case '3':
          if(this.$store.state.username!=null){
          if (this.$route.path !== '/core') {
            this.$router.push({
              path: '/core'
            })
          }}else{
            this.$message({
              message: '请先登录'
            })
          }
          break
        case '0-1':
          this.dialogInfo = true
          this.info.address = this.user.address
          this.info.username = this.user.username
          break
        case '0-2':
          this.dialogPassword = true
          break
        case '0-3':
          this.logOut()
          break
      }
    },
    // 登出
    logOut () {
      Cookie.remove('username')
      this.$store.commit("logout")
      this.$router.push({
        path: '/'
      })
    },
////修改地址
    editUserInfo () {
      this.$confirm('此操作将修改地址, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const  add = this.address
        axios.post("/api/updateAddress/"+add).then(res => {
          this.$store.commit("updateAddress",add);
          this.$message({
            type: 'success',
            message: '修改成功!'
          })

          this.dialogInfo = false
        })
      })
    },
    // 修改用户密码
    editUserPassword () {
      if (this.pw.oldPw === '') {
        this.$message('请输入原密码')
        return
      }
      if (this.pw.newPw === '') {
        this.$message('请输入新密码')
        return
      }
      if (this.pw.newPwAgain === '') {
        this.$message('请输入确认密码')
        return
      }

      this.$confirm('此操作将修改个人密码, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {
          ...this.pw
        }
        this.$axios.editUserPassword(param).then(res => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.dialogPassword = false
        })
      })
    }
  }
}
</script>

<style scoped>
  .el-menu.el-menu--horizontal {
    border-bottom: solid 0px #e6e6e6;
  }

  .el-menu-item.is-disabled {
    opacity: 1;
  }

  .title-nav {
    color: #72fbec;
    font-weight: 900;
    font-size: 20px;
    letter-spacing: 4px;
  }

  .top-nav {
    height: 60px;
    width: 100%;
    background: #545c64;
  }

  .el-menu--horizontal>.el-menu-item {
    height: 50px;
    line-height: 50px;
  }

  .subject-nav {
    margin: 0 auto;
  }

  .el-menu-item.is-disabled {
    cursor: pointer;
  }

  .avatar {
    width: 90px;
    height: 90px;
    display: block;
  }
</style>
