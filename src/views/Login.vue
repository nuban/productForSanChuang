<template>
  <div class="firstpage">
    <el-form :rules="rules"
             ref="loginForm"
             :model="loginForm"
             class="loginContainer"
             v-loading="loading"
             element-loading-text="拼命加载中"
             element-loading-spinner="el-icon-loading"
             element-loading-background="rgba(0, 0, 0, 0.8)">
      <h3 class="loginTitle">后台系统</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" autocomplete="off" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" autocomplete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input type="text" v-model="loginForm.code" autocomplete="off"
                  placeholder="点击图片更换验证码" style="width: 250px; margin-right: 10px"></el-input>
        <img :src="captchaUrl" @click="updateCaptcha"/>
      </el-form-item>
      <el-checkbox v-model="checked" class="loginRemember">记住我</el-checkbox>
      <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>


export default {
  name: "Login",
  //data() 双向数据绑定 暂时理解为方法形式的data
  data(){
    return {
      //验证码地址
      captchaUrl: '/captcha?time=' + new Date(),
      loginForm: {
        username: 'admin',
        password: '123456',
        code: ''
      },
      checked: true,
      //规则校验
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
        code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
      },
      //加载
      loading: false
    }
  },
  methods: {
    submitLogin: function () {

      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          //this.loading = true;
          this.postRequest('/login',this.loginForm).then(resp => {

            if (resp) {
              //console.log("xxxxx");
              this.loading = false;
              let tokenStr = resp.obj.tokenHead + ' ' + resp.obj.token;
              console.log(tokenStr);
              window.sessionStorage.setItem('tokenStr', tokenStr);
              //页面跳转
              let path = this.$route.query.redirect;
              this.$router.push((path=='/'||path==undefined)? '/home': path);
            }
          })

        } else {
          this.$message({
            message: '请填写完整信息',
            type: 'warning'
          });
          return false;
        }
      });
    },
    updateCaptcha() {
      //调用验证码接口
      console.log("获取验证码");
      this.captchaUrl = '/captcha?time=' + new Date();
    }
  }
}
</script>

<style>
.firstpage{
  background: url("../assets/bg.jpg");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;

}
.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 200px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: aliceblue;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.loginTitle {
  margin: 0px auto 40px auto;
  text-align: center;
}
.loginRemember {
  text-align: left;
  margin: 0px 0px 20px 0px;
}
.el-form-item__content {
  display: flex;
  align-content: center;
}
</style>