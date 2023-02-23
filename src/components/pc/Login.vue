<template>
  <div class="background">
    <!-- Form-->
    <div class="form">
      <div class="form-toggle"></div>
      <div class="form-panel one">
        <div class="form-header">
          <h1>Account Login</h1>
        </div>
        <div class="form-content">
          <form>
            <el-form :rules="rules" :model="loginForm" ref="loginForm">
              <div class="form-group">
                <label>id</label>
                <el-form-item prop="Id" class="elItem">
                  <el-input v-model="loginForm.Id"></el-input>
                </el-form-item>
                <!--              <input type="text" id="username" name="username" required="required"/>-->
              </div>
              <div class="form-group">
                <label>Password</label>
                <el-input v-model="loginForm.password" type="password"></el-input>
                <!--              <input type="password" id="password" name="password" required="required"/>-->
              </div>
              <div class="form-group">
                <label class="form-remember">
                  <input type="checkbox"/>Remember Me
                </label><a class="form-recovery" href="#">Forgot Password?</a>
              </div>
              <div class="form-group">
                <el-radio v-model="loginForm.type" label="1">teacher</el-radio>
                <el-radio v-model="loginForm.type" label="2">student</el-radio>
              </div>
              <div class="form-group">
                <button @click="login" type="button">Login In</button>
              </div>
            </el-form>
          </form>
        </div>
      </div>
      <div class="form-panel two">
        <div class="form-header">
          <h1>Register Account</h1>
        </div>
        <div class="form-content">
          <form>
            <el-form :rules="rule" :model="registeredForm" ref="registeredForm">
              <div class="form-group">
                <label>id</label>
                <el-form-item prop="registeredId" class="elItem">
                  <el-input v-model="registeredForm.registeredId"></el-input>
                </el-form-item>
                <!--              <input type="text" id="username" name="username" required="required"/>-->
              </div>
              <div class="form-group">
                <label>Password</label>
                <el-form-item prop="registeredPassword" class="elItem">
                  <el-input v-model="registeredForm.registeredPassword" type="password"></el-input>
                </el-form-item>
                <!--              <input type="password" id="password" name="password" required="required"/>-->
              </div>
              <div class="form-group">
                <label>Confirm Password</label>
                <el-form-item prop="confirmPassword" class="elItem">
                  <el-input v-model="registeredForm.confirmPassword" type="password"></el-input>
                </el-form-item>
                <!--              <input type="password" id="cpassword" name="cpassword" required="required"/>-->
              </div>
              <div class="form-group">
                <label>Phone number</label>
                <el-form-item prop="phoneNumber" class="elItem">
                  <el-input v-model="registeredForm.phoneNumber"></el-input>
                </el-form-item>
                <!--              <input type="email" id="email" name="email" required="required"/>-->
              </div>
              <div class="form-group">
                <label>Verification code</label>
                <el-form-item prop="Verification" class="elItem">
                  <el-input v-model="registeredForm.verification" class="codeInput"></el-input>
                  <el-button class="codeButton" @click="sendMessage">post</el-button>
                </el-form-item>
                <!--              <input type="email" id="email" name="email" required="required"/>-->
              </div>
              <div class="form-group">
                <label>identity:</label>
                <el-tooltip :content="'Switch value: ' + registeredForm.type" placement="top">
                  <el-switch
                    v-model="registeredForm.type"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                    active-value="teacher"
                    inactive-value="student"
                    active-text="teacher"
                    inactive-text="student">
                  </el-switch>
                </el-tooltip>
              </div>
              <div class="form-group">
                <button type="button" @click="register">Register</button>
              </div>
            </el-form>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../../assets/lop.js'
import qs from 'qs'

export default {
  name: "Login",

  data() {
    let checkId = (rule, value, callback) => {
      if (this.loginForm.Id.length == 0) {
        callback(new Error('请输入ID'))
      }
      let pattern = new RegExp('[^0-9]+')
      if (pattern.test(this.loginForm.Id) || this.loginForm.Id.length !== 8) {
        callback(new Error('请输入正确格式'))
      }
      callback()
    }

    let checkRegisterId = (rule, value, callback) => {
      if (this.registeredForm.registeredId.length == 0) {
        return callback(new Error('请输入ID'))
      }
      let pattern = new RegExp('[^0-9]+')
      if (pattern.test(this.registeredForm.registeredId) || this.registeredForm.registeredId.length !== 8) {
        return callback(new Error('请输入正确格式'))
      }
      callback()
    }
    let confirmPassword = (rule, value, callback) => {
      if (this.registeredForm.registeredPassword !== this.registeredForm.confirmPassword) {
        callback(new Error('两次输入的密码不一致'))
      }
      callback()
    }
    let checkPhoneNumber = (rule, value, callback) => {
      let pattern = new RegExp("^1[345789][0-9]{9}")
      if (!pattern.test(this.registeredForm.phoneNumber)) {
        callback(new Error('请输入正确的手机号码'))
      }
      callback()
    }
    return {
      registeredForm: {
        registeredId: '',
        registeredPassword: '',
        confirmPassword: '',
        phoneNumber: '',
        verification: '',
        type: 'student',
      },
      rule: {
        registeredId: [
          {required: true, trigger: 'blur', validator: checkRegisterId},
        ],
        confirmPassword: [
          {required: true, trigger: 'blur', validator: confirmPassword},
        ],
        phoneNumber: [
          {required: true, trigger: 'blur', validator: checkPhoneNumber}
        ],
      },
      loginForm: {
        Id: '',
        password: '',
        type: '1',
      },
      rules: {
        Id: [
          {required: true, trigger: 'blur', validator: checkId},
        ]
      },
      type: '1'
    }
  },
  methods: {
    async getTeacherAvatar() {
      let user = {
        userId: this.loginForm.Id,
      }
      let {data: rest} = await this.$http.post("getTeacherInformation", qs.stringify(user), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      let res = rest.user;
      localStorage.setItem("avatar", res.teacherPhoto);
      await this.$router.push('/TeacherMyClass');
    },
    async getAvatar() {
      let user = {
        userId: this.loginForm.Id,
      }
      let {data: rest} = await this.$http.post("getStudentInformation", qs.stringify(user), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      let res = rest.user;
      localStorage.setItem("avatar", res.studentPhoto)
      await this.$router.push('/PcMainInterface');
    },
    async sendMessage() {
      let {data: res} = await this.$http.post("sendMessage", qs.stringify(this.registeredForm), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.$message.warning(res.message);
    },
    async login() {
      await this.$refs.loginForm.validate(async (valid) => {
        if (!valid) {
          this.$message.error("请先输入正确信息");//信息提示
        } else {
          let result;
          if (this.loginForm.type === '1') {
            let {data: res} = await this.$http.post("teacherLogin", qs.stringify(this.loginForm), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
            result = res;
            if (result.flag === "right") {
              localStorage.setItem("token", result.token)
              localStorage.setItem("userId", this.loginForm.Id)
              this.$store.commit("setIdentifier", 'teacher')
              await this.getTeacherAvatar();
            }
          } else {
            let {data: res} = await this.$http.post("studentLogin", qs.stringify(this.loginForm), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
            result = res;
            if (result.flag === "right") {
              localStorage.setItem("token", result.token)
              localStorage.setItem("userId", this.loginForm.Id)
              this.$store.commit("setIdentifier", 'student')
              await this.getAvatar();
            }
          }

          if (result.flag === "right") {
            localStorage.setItem("token", result.token)
            this.$message.success("登录成功")
          } else {
            this.$message.error("登录失败");
          }
        }
      });
    },
    async register() {
      await this.$refs.registeredForm.validate(async (valid) => {
        if (!valid) {
          this.$message.error("请重新输入");//信息提示
        } else {
          let result;
          if (this.registeredForm.type === "student") {
            let {data: res} = await this.$http.post("studentRegister", qs.stringify(this.registeredForm), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
            result = res;
          } else {
            let {data: res} = await this.$http.post("teacherRegister", qs.stringify(this.registeredForm), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
            result = res;
          }
          if (result.flag === "error") {
            this.$message.error("验证码错误")
            return;
          } else {
            this.$message.success("注册成功")
          }

        }
      });
    }
  },
}
$(document).ready(function () {
  let panelOne = $('form').height() * 2;
  let panelTwo = $('.form-panel.two')[0].scrollHeight;
  $('.form-panel.two').not('.form-panel.two.active').on('click', function (e) {
    e.preventDefault();
    $('.form-toggle').addClass('visible');
    $('.form-panel.one').addClass('hidden');
    $('.form-panel.two').addClass('active');
    $('.form').animate({
      'height': panelTwo
    }, 200);
  });

  $('.form-toggle').on('click', function (e) {
    e.preventDefault();
    $(this).removeClass('visible');
    $('.form-panel.one').removeClass('hidden');
    $('.form-panel.two').removeClass('active');
    $('.form').animate({
      'height': panelOne
    }, 200);
  });
});

</script>

<style scoped>
.codeButton {
  position: absolute;
  display: inline-block;
  width: 150px !important;
  height: 38px !important;
  text-align: center;
  line-height: 15px !important;
  bottom: 3px;
  right: 0;
}

.codeInput {
  display: inline-block !important;
  width: 260px !important;

}

.background {
  height: 99%;
  width: 99%;
  margin: 8px;
  background: linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);
  color: rgba(0, 0, 0, 0.6);
  font-family: "Roboto", sans-serif;
  font-size: 14px;
  line-height: 1.6em;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.overlay, .form-panel.one:before {
  position: absolute;
  top: 0;
  left: 0;
  display: none;
  background: rgba(0, 0, 0, 0.8);
  width: 100%;
  height: 100%;
}

.form {
  z-index: 15;
  position: relative;
  background: #FFFFFF;
  width: 600px;
  height: 550px;
  top: 175px;
  border-radius: 4px;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
  margin: auto;
  overflow: hidden;
}

.form-toggle {
  z-index: 10;
  position: absolute;
  top: 60px;
  right: 60px;
  background: #FFFFFF;
  width: 60px;
  height: 60px;
  border-radius: 100%;
  transform-origin: center;
  transform: translate(0, -25%) scale(0);
  opacity: 0;
  cursor: pointer;
  transition: all 0.3s ease;
}

.form-toggle:before, .form-toggle:after {
  content: "";
  display: block;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 30px;
  height: 4px;
  background: #4285F4;
  transform: translate(-50%, -50%);
}

.form-toggle:before {
  transform: translate(-50%, -50%) rotate(45deg);
}

.form-toggle:after {
  transform: translate(-50%, -50%) rotate(-45deg);
}

.form-toggle.visible {
  transform: translate(0, -25%) scale(1);
  opacity: 1;
}

.form-group {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 0 0 20px;
}

.form-group:last-child {
  margin: 0;
}

.form-group label {
  display: block;
  margin: 0 0 10px;
  color: rgba(0, 0, 0, 0.6);
  font-size: 12px;
  font-weight: 500;
  line-height: 1;
  text-transform: uppercase;
  letter-spacing: 0.2em;
}

.two .form-group label {
  color: #FFFFFF;
}

.form-group >>> .el-input__inner {
  outline: none;
  display: block;
  background: rgba(0, 0, 0, 0.1);
  width: 100%;
  border: 0;
  border-radius: 4px;
  box-sizing: border-box;
  padding: 12px 20px;
  color: rgba(0, 0, 0, 0.6);
  font-family: inherit;
  font-size: inherit;
  font-weight: 500;
  line-height: inherit;
  transition: 0.3s ease;
}

.form-group input:focus {
  color: rgba(0, 0, 0, 0.8);
}

.two .form-group input {
  color: #FFFFFF;
}

.two .form-group input:focus {
  color: #FFFFFF;
}

.form-group button {
  outline: none;
  background: #4285F4;
  width: 100%;
  border: 0;
  border-radius: 4px;
  padding: 12px 20px;
  color: #FFFFFF;
  font-family: inherit;
  font-size: inherit;
  font-weight: 500;
  line-height: inherit;
  text-transform: uppercase;
  cursor: pointer;
}

.two .form-group button {
  background: #FFFFFF;
  color: #4285F4;
}

.form-group .form-remember {
  font-size: 12px;
  font-weight: 400;
  letter-spacing: 0;
  text-transform: none;
}

.form-group .form-remember input[type=checkbox] {
  display: inline-block;
  width: auto;
  margin: 0 10px 0 0;
}

.form-group .form-recovery {
  color: #4285F4;
  font-size: 12px;
  text-decoration: none;
}

.form-panel {
  padding: 60px calc(5% + 60px) 60px 60px;
  box-sizing: border-box;
}

.form-panel.one:before {
  content: "";
  display: block;
  opacity: 0;
  visibility: hidden;
  transition: 0.3s ease;
}

.form-panel.one.hidden:before {
  display: block;
  opacity: 1;
  visibility: visible;
}

.form-panel.two {
  z-index: 5;
  position: absolute;
  top: 0;
  left: 95%;
  background: #4285F4;
  width: 100%;
  min-height: 100%;
  padding: 60px calc(10% + 60px) 60px 60px;
  transition: 0.3s ease;
  cursor: pointer;
}

.form-panel.two:before, .form-panel.two:after {
  content: "";
  display: block;
  position: absolute;
  top: 60px;
  left: 1.5%;
  background: rgba(255, 255, 255, 0.2);
  height: 30px;
  width: 2px;
  transition: 0.3s ease;
}

.form-panel.two:after {
  left: 3%;
}

.form-panel.two:hover {
  left: 93%;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.form-panel.two:hover:before, .form-panel.two:hover:after {
  opacity: 0;
}

.form-panel.two.active {
  left: 10%;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  cursor: default;
}

.form-panel.two.active:before, .form-panel.two.active:after {
  opacity: 0;
}

.form-header {
  margin: 0 0 40px;
}

.form-header h1 {
  padding: 4px 0;
  color: #4285F4;
  font-size: 24px;
  font-weight: 700;
  text-transform: uppercase;
}

.two .form-header h1 {
  position: relative;
  z-index: 40;
  color: #FFFFFF;
}

.pen-footer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 600px;
  margin: 20px auto 100px;
}

.pen-footer a {
  color: #FFFFFF;
  font-size: 12px;
  text-decoration: none;
  text-shadow: 1px 2px 0 rgba(0, 0, 0, 0.1);
}

.pen-footer a .material-icons {
  width: 12px;
  margin: 0 5px;
  vertical-align: middle;
  font-size: 12px;
}

.cp-fab {
  background: #FFFFFF !important;
  color: #4285F4 !important;
}

.elItem {
  width: 100%;
  margin-bottom: 0;
}
</style>
