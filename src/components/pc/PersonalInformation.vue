<template>
  <el-container>
    <main-header>
    </main-header>
    <el-main>
      <div class="personalInfo">
        <div class="personalTip">
          <span>
            个人信息
          </span>
          <p class="edit" v-on:click="changeChoice">
            <i class="el-icon-edit">
              {{ changeMode }}
            </i>
          </p>
        </div>
        <div class="p_context">
          <div class="avatar">
            <img :src="avatarUrl">
            <el-upload
              class="avatar-uploader"
              :action="actionStr"
              :show-file-list="false"
              :headers=headerStr
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <i class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </div>
          <div class="information">
            <div class="boxLeft">
              <div class="b_item">
                <span>
                  学生id:
                </span>
                <span>
                  {{ user.id }}
                </span>

              </div>
              <div class="b_item">
                <span>
                  学生学号:
                </span>
                <span>
                  {{ user.studentNum }}
                </span>

              </div>
              <div class="b_item">
                <span>
                  学生手机号:
                </span>
                <span>
                  {{ user.studentPhoneNum }}
                </span>
              </div>
              <div class="b_item">
                <span>
                  学生姓名:
                </span>
                <span v-if="this.show === '1'">
                  {{ user.studentName }}
                </span>
                <span v-else-if="show === '2'">
                  <input :value="this.changeName"></input>
                </span>
              </div>
            </div>
            <div class="boxRight">
              <div class="b_item">
                <span>
                  学生班级:
                </span>
                <span>
                  {{ user.classNum }}
                </span>
              </div>
              <div class="b_item">
                <span>
                  学生备注:
                </span>
                <span v-if="this.show === '1'">
                  {{ user.studentRemark }}
                </span>
                <span v-else-if="show === '2'">
                  <input :value="this.changeMemo"></input>
                </span>
              </div>
              <div class="b_item changePassword" @click="dialogVisible = true">
                修改密码
              </div>
              <div class="b_item changePassword" @click="changePhone = true">
                修改手机号
              </div>
            </div>
          </div>
        </div>
      </div>
      <el-dialog
        title="修改密码"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
        class="dialog">
        <input type="text" placeholder="输入密码">

        </input>
        <input type="text" placeholder="确认密码">

        </input>
        <input type="text" placeholder="输入手机号">

        </input>
        <div class="Verification">
          <input class="VerificationInput" placeholder="验证码">
          </input>
          <span>
            发送验证码
          </span>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog
        title="绑定手机号"
        :visible.sync="changePhone"
        width="30%"
        class="try"
        :before-close="handleClose">
        <input type="text" placeholder="输入手机号">

        </input>
        <div class="Verification">
          <input class="VerificationInput" placeholder="验证码">
          </input>
          <span>
            获取验证码
          </span>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="changePhone = false">取 消</el-button>
          <el-button type="primary" @click="changePhone = false">确 定</el-button>
        </span>
      </el-dialog>
    </el-main>
  </el-container>
</template>
<script>

import HoverAvatar from './HoverAvatar'
import MainHeader from './MainHeader'
import qs from "qs";

export default {
  name: "PersonalInformation",
  components: {HoverAvatar, MainHeader},

  data() {
    return {
      headerStr: {
        token: localStorage.getItem("token"),
      },
      actionStr: 'http://localhost:8090/studentUploadAvatar?userId',
      imageUrl: '',
      show: '1',
      changeMode: '编辑',
      dialogVisible: false,
      changePhone: false,
      avatarUrl: localStorage.getItem("avatar"),
      user: '',
      changeName: '',
      changeMemo: '',
    };
  },
  mounted() {
    this.actionStr = this.actionStr + "=" + localStorage.getItem("userId");
    this.getStudentInformation();
  },
  methods: {
    async getStudentInformation() {
      let user = {
        userId: localStorage.getItem("userId"),
      }
      let {data: res} = await this.$http.post("getStudentInformation", qs.stringify(user), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.user = res.user;
      this.changeName = this.user.studentName;
      this.changeMemo = this.user.studentRemark;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    changeChoice() {
      if (this.changeMode === '编辑') {
        this.changeMode = '保存';
      } else {
        this.changeMode = '编辑';
      }
      if (this.show === '1') {
        this.show = '2';
      } else {
        this.show = '1';
      }
    },
    handleAvatarSuccess(res, file) {
      this.avatarUrl = res.path;
      this.imageUrl = URL.createObjectURL(file.raw);
      this.$message.success("修改头像成功");
      localStorage.setItem("avatar", this.avatarUrl);
      this.$store.state.avatar = this.avatarUrl;
      this.$router.go(0);
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isLt2M;
    }
  }
}

</script>
<style scoped>
.avatar img {
  width: 79%;
  height: 86.9%;
  border-radius: 50%;
  margin-top: 3.8px;
  margin-right: 6.5px;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.avatar {
  width: 74px;
  height: 66px !important;
  text-align: center;
  z-index: 1;
  margin-left: 20px;
  background-size: 100% 100% !important;
  background: url(../../assets/head-bg.png) no-repeat;
  box-sizing: border-box;

}

.try .el-dialog__header {
  padding-top: 50px !important;
}

.try .el-dialog__title {
  font-size: 30px !important;
}

.Verification span {
  cursor: pointer;
  display: block;
  float: right;
  color: #b56666;
  margin-right: 10px;
}

.Verification {
  text-align: left;
  background: #f9f9f9;
  color: #b56666;
  height: 16px;
  width: 91% !important;
  line-height: 16px;
  padding-top: 16px;
  padding-bottom: 16px;
  border: 1px solid #fff;
  margin-left: 0;
  margin-right: auto;
}

.el-dialog input:nth-child(3):focus {
  border: 1px skyblue solid;
}

.el-dialog input:nth-child(2):focus {
  border: 1px skyblue solid;
}

.el-dialog input:nth-child(1):focus {
  border: 1px skyblue solid;
}

.el-dialog input {
  outline: none;
}

.VerificationInput {
  display: inline-block !important;
  background: #f9f9f9 !important;
  color: #333 !important;
  height: 18px !important;
  width: 70% !important;
  line-height: 16px !important;
  padding-top: 0 !important;
  padding-bottom: 16px !important;
  margin-bottom: 15px !important;
  border: 1px solid #fff !important;
}

.el-dialog input:nth-child(3) {
  background: #f9f9f9;
  color: #333;
  height: 16px;
  width: 90% !important;
  line-height: 16px;
  padding-top: 16px;
  padding-bottom: 16px;
  margin-bottom: 15px;
  border: 1px solid #fff;
}

.el-dialog input:nth-child(2) {
  background: #f9f9f9;
  color: #333;
  height: 16px;
  width: 90% !important;
  line-height: 16px;
  padding-top: 16px;
  padding-bottom: 16px;
  margin-bottom: 15px;
  border: 1px solid #fff;
}

.el-dialog input:nth-child(1) {
  margin-bottom: 15px;
  background: #f9f9f9;
  color: #333;
  height: 16px;
  width: 90%;
  line-height: 16px;
  padding-top: 16px;
  padding-bottom: 16px;
  border: 1px solid #fff;
}

.el-dialog {
  margin-top: 30vh !important;
  width: 500px !important;
  height: 450px !important;
}

.b_item input:nth-child(1):focus {
  border: 1px solid skyblue;
}

.b_item input:nth-child(1) {
  border: 1px solid #888888;
  border-radius: 4px;
  outline: none;
  width: 150px;
  height: 30px;
}

.changePassword {
  font-size: 24px;
  cursor: pointer;
  color: #1769fe;
}

.b_item span:nth-child(2) {
  font-size: 24px;
  color: #333;
}

.b_item span:nth-child(1) {
  font-size: 24px;
  color: #999;
  font-weight: 350;
}

.b_item {
  float: top;
  width: 100%;
  height: 50px;
  margin-top: 15%;
  text-align: left;
  padding-left: 120px;
}

.boxRight {
  float: right;
  width: 45%;
}

.boxLeft {
  float: left;
  width: 45%;
}

.information {
  width: 90%;
  padding: 34px 34px 0;
  position: relative;
  margin-right: auto;
  margin-left: auto;
  background: #e9e9e9;
  margin-top: 2%;
  height: 550px;
}

.avatar-uploader >>> .el-upload--text {
  font-size: 40px !important;
}

.avatar-uploader {
  display: inline-block;
  position: absolute;
  width: 50px;
  height: 50px;
  font-size: 40px;
  left: 10px;
  top: 10px;
  opacity: 0;
}

.avatar {
  position: relative;
  height: auto;
}

.p_context {
  width: 90%;
  height: 85%;
  margin-left: auto;
  margin-right: auto;
  padding: 0 !important;
}

.edit {
  display: inline-block;
  float: right;
  font-size: 24px;
  color: #1769fe;
  cursor: pointer;
  font-weight: 400;
  line-height: 43px;
  margin-right: 50px;
}

.personalTip span {
  float: left;
  font-size: 30px;
  font-weight: 500;
  color: #333;
  margin-left: 35px;
}

.personalTip {
  height: 80px;
  line-height: 80px;
  margin-top: 66px;
}

.personalInfo {
  height: 1000px;
  width: 1200px;
  background: #fcfcfc;
  border-radius: 4px;
  padding-top: 20px;
  margin-left: auto;
  margin-right: auto;
}
</style>
