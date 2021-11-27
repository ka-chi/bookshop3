<template>
<div class="login-block">
    <div id="login_box">
      <h2>用户注册</h2>
<el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="昵称" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="用户名" prop="username">
    <el-input v-model="ruleForm.username"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password">
    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="性别" prop="sex">
  <el-radio-group v-model="ruleForm.sex" >
    <el-radio label="1">男</el-radio>
    <el-radio label="2">女</el-radio>
  </el-radio-group>
  </el-form-item>
  <el-form-item label="电话号码" prop="tel">
    <el-input v-model="ruleForm.tel"></el-input>
  </el-form-item>

  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>

    <div class="footer">
      <span>已有账号？</span>
    <router-link to="/login">
      <a>去登录</a>
    </router-link>
    </div>
  </el-form-item>
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
    margin-left: -40px
}
.el-input__inner{
  border-radius: 20px;
}
#login_box {
    width: 20%;
    height: 450px;
    background: #00000060;
    margin: auto;
    margin-top: 10%;
    text-align: center;
    border-radius: 10px;
    padding: 50px 50px;
}

#login_box h2 {
    color: #FFFFFF90;
    margin-top: -4%;
    margin-bottom: 8%;
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
// import http from '../../../util/http'
export default {
  name: 'uregister',
  data () {
    var checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('昵称不能为空'))
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
        name: '',
        username: '',
        sex: '1',
        password: '',
        tel: '17374596260'
      },
      rules: {
        name: [
          { validator: checkName, trigger: 'blur' }
        ],
        username: [
          { validator: checkUser, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        tel: [
          { validator: checkTel, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('/api/Cregister', this.ruleForm, { headers: { 'Content-Type': 'application/json;charset=UTF-8' } })
            .then(res => {
             if(res.data.code == 20001){
               this.$message({message:res.data.message,type:'error'})
             }else{
               this.$message({message:'注册成功',type:'success'})
               this.$router.push({ path: '/login' })
             }
            })

          console.log(this.ruleForm)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 重置表单
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>
