<template>
<div class="login-block">
    <div id="login_box">
      <h2>商家注册</h2>
<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px">
  <div class="input">
  <el-form-item label="账号" prop="username">
    <el-input v-model="ruleForm.username"></el-input>
  </el-form-item>
  </div>
  <div class="input">
  <el-form-item label="密码" prop="pass">
    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
  </el-form-item>
  </div>
  <div class="input">
  <el-form-item label="确认密码" prop="checkPass">
    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
  </el-form-item>
  </div>
  <div class="input">
  <el-form-item label="电话号码" prop="tel">
    <el-input v-model.number="ruleForm.tel"></el-input>
  </el-form-item>
  </div>
  <div class="input">
  <el-form-item label="商家名称" prop="shopName">
    <el-input v-model="ruleForm.shopName"></el-input>
  </el-form-item>
  </div>
  <div class="input">
  <el-form-item label="注册地址" prop="shopAddress">
    <el-input v-model="ruleForm.shopAddress"></el-input>
  </el-form-item>
  </div>
  <div class="input">
  <el-form-item label="经营类型" prop="type">
    <el-input v-model="ruleForm.type"></el-input>
  </el-form-item>
  </div>
  <div class="input">
  <el-form-item label="注册资金" prop="registerMoney">
    <el-input v-model.number="ruleForm.registerMoney"></el-input>
  </el-form-item>
  </div>
    <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
    <div class="footer">
    <span>已有账号？</span>

    <router-link to="/Slogin">
      <a>去登录</a>
    </router-link>
    </div>
</el-form>
    </div>
</div>
</template>

<style>
  .login-block {
  position: relative;
  margin-top: -150px;
  height: 716px;
  width: 100%;
  background: url(../assets/1.jpg);
  background-repeat: no-repeat;
  background-size: 100% auto;
}
.el-form-item {
    margin-bottom: 15px;
}
.el-input__inner{
  border-radius: 20px;
}
#login_box {

    width: 20%;
    height: 600px;
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
    margin-bottom: 5%;
    margin-left: 12%;
}
#login_box button {
    margin-top: 40px;
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
    margin-left: -25px;
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
  .el-form-item__label {
    color: #fff;
}
</style>

<script>

export default {
  name: 'mregister',
  data () {
    var checkShopName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('商家名称不能为空'))
      } else {
        callback()
      }
    }
    var checkUser = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('账号不能为空'))
      } else {
        callback()
      }
    }
    var checkRegisterMoney = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('注册资金不能为空'))
      } else if (value < 50000) {
        return callback(new Error('注册资金不能低于50000元'))
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字'))
        } else {
          callback()
        }
      }, 1000)
    }
    var checkShopAddress = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('注册地址不能为空'))
      } else {
        callback()
      }
    }
    var checkType = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('经营类型不能为空'))
      } else {
        callback()
      }
    }
    var checkTel = (rule, value, callback) => {
      const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
      if (!value) {
        return callback(new Error('电话号码不能为空'))
      }
      setTimeout(() => {
      // Number.isInteger是es6验证数字是否为整数的方法,实际输入的数字总是识别成字符串
      // 所以在前面加了一个+实现隐式转换

        if (!Number.isInteger(+value)) {
          callback(new Error('请输入数字值'))
        } else {
          if (phoneReg.test(value)) {
            callback()
          } else {
            callback(new Error('电话号码格式不正确'))
          }
        }
      }, 100)
    }
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass')
        }
        callback()
      }
    }
    return {
      ruleForm: {
        shopName: '',
        username: '',
        registerMoney: '',
        password: '',
        type: '',
        tel: '',
        shopAddress: ''
      },
      rules: {
        shopName: [
          { validator: checkShopName, trigger: 'blur' }
        ],
        username: [
          { validator: checkUser, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        registerMoney: [
          { validator: checkRegisterMoney, trigger: 'blur' }
        ],
        tel: [
          { validator: checkTel, trigger: 'blur' }
        ],
        shopAddress: [
          { validator: checkShopAddress, trigger: 'blur' }
        ],
        type: [
          { validator: checkType, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('/api/Sregister', this.ruleForm)
            .then(res => {
              this.$message({message:'注册成功',type:'success'})
              this.$router.push({ path: '/Slogin' })
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
