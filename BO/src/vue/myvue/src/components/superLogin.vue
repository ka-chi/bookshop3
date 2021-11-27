<template>
  <div class="login-block">
    <ul class="register">
      <router-link to="/login/ulogin" tag="li">超级管理员登录</router-link>
    </ul>
    <div class="login-subject">

      <el-form label-width="80px" style="margin: 30px 50px;" :model="ruleForm" ref="ruleForm">
        <el-input v-model="ruleForm.username" prefix-icon="el-icon-user" class="login-subject-input" placeholder="用户名" prop="username"></el-input>

        <el-input v-model="ruleForm.password" show-password prefix-icon="el-icon-lock" class="login-subject-input"
                  placeholder="密码" prop="password"></el-input>

        <el-button type="primary" style="width: 100%;background: #5dd5c8;" @click="submitForm('ruleForm')">登录</el-button>


      </el-form>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
/**
 * description  登录界面
 */
export default {
  name: 'mlogin',
  data () {
    var validateUsername = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error('账号不能为空'))
      } else {
        callback()
      }
    }
    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    return {
      ruleForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { validator: validateUsername, trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword, trigger: 'blur' }
        ]
      }
      // formdata: {
      //   username: 'm' + this.username,
      //   password: ''
      // }
    }
  },
  methods: {
    submitForm (formName) {

      axios({
        method: 'post',
        url: '/api/login',
        data: {
          username:  this.ruleForm.username,
          password: this.ruleForm.password,
        },
        transformRequest: [
          function (data) {
            let ret = ''
            for (const it in data) {
              ret +=
                encodeURIComponent(it) +
                '=' +
                encodeURIComponent(data[it]) +
                '&'
            }
            return ret
          }
        ],
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      }).then(res => {
        this.$router.push({ path: '/sindex' })
        this.$message({
          message: '登陆成功',
          type: 'success'
        });
        this.$store.commit("login",res.data.data);
      })
    }
  }
}
</script>

<style scoped>
.login-block {
  height: 100%;
}

.login-subject {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 500px;
  height: 350px;
  border-radius: 2%;
  background: #FFFFFF;
  margin: -220px 0 0 -250px;
}

.login-subject-input {
  margin-bottom: 35px;
}
.register {
  position: absolute;
  left: 50%;
  transform: translateX(-48%);
  list-style: none;
  width: 500px;
  display: flex;
  justify-content:center;
  font-size: 20px;
  color:#5dd5c8
}
.register li {
  padding: 5px;
}
</style>
