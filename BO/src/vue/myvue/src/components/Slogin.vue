<template>
  <div class="login-block">
    <div id="login_box">
      <h2>商家登录</h2>
      <el-form id="form" :model="ruleForm" ref="ruleForm">
        <div class="input">
        <el-input v-model="ruleForm.username" prefix-icon="el-icon-user"
        placeholder="用户名" prop="username"></el-input>
        </div>
        <div class="input">
        <el-input v-model="ruleForm.password" show-password prefix-icon="el-icon-lock"
          placeholder="密码" prop="password"></el-input>
        </div>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <div class="footer">
          <span>还没有账号？</span>
          <router-link to="/Sregister">
            <a>点击注册</a>
          </router-link>
        </div>

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
          username: 'shop' + this.ruleForm.username,
          password: this.ruleForm.password
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
        if(res.data.code == 20001){
          this.$message({
            message: res.data.message,
            type: 'error'
          })
        }else{
          this.$router.push({ path: '/manage' })
          this.$message({
            message: '登陆成功',
            type: 'success'
          })
          this.$store.commit('login', res.data.data)
        }
      })
    }
  }
}
</script>

<style scoped>
.login-block {
  position: relative;
  margin-top: -150px;
  height: 716px;
  width: 100%;
  background: url(../assets/1.jpg);
  background-repeat: no-repeat;
  background-size: 100% auto;
}

#login_box {

    width: 20%;
    height: 400px;
    background: #00000060;
    margin: auto;
    margin-top: 10%;
    text-align: center;
    border-radius: 10px;
    padding: 50px 50px;
}

#login_box h2 {
    color: #FFFFFF90;
    margin-top: 5%;
    margin-bottom: 25%;
}
#login_box button {
    margin-top: 70px;
    width: 32%;
    height: 40px;
    border-radius: 10px;
    border: 0;
    color: #FFFFFF;
    font-size: 16px;
    background: linear-gradient(45deg, lightgreen, lightblue, lightgoldenrodyellow);
}
#login_box button:hover{
  background: linear-gradient(45deg, lightgoldenrodyellow, lightblue, lightgreen);
}
.input {
    border-radius: 17px;
    font-size: 16px;
    border-bottom: 2px solid #FFFFFF;
    padding: 5px 10px;
    margin-top: 10px;
  }
  .footer{
    margin-top: 30px;
    margin-left: 30px;
  }
  .footer span{
    color: aquamarine;
  }
  .footer a{
    text-decoration: none;
    color: burlywood;
  }
</style>
