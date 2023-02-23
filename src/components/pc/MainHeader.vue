<template>
  <el-header>
    <div class="header">
      <div class="h_header">
        <img src="../../assets/myLogo.png" class="h_logo" alt="加载失败">
        <div class="h_menu">
          <li>
            <a @click="goMain" href="http://localhost:8088/#/PcMainInterface">首页</a>
          </li>
          <li>
            <a @click="goAll">全部课程</a>
          </li>
          <li>
            <a @click="goCode">代码练习</a>
          </li>
          <li>
            <a>精选课程</a>
          </li>
        </div>
        <div class="flexsearch">
          <div class="flexsearch--wrapper">
            <form class="flexsearch--form" action="#" method="post">
              <div class="flexsearch--input-wrapper">
                <input class="flexsearch--input" type="search" placeholder="search" v-model="searchInput">
              </div>
              <el-button class="flexsearch--submit" type="button" icon="el-icon-search"
                         @click="getCourseByNameOrMajorOrCourseNum"/>
            </form>
          </div>
        </div>
        <!--            <div class="loginButton">-->
        <!--              <div class="login">-->
        <!--                <el-button type="primary" round>登录</el-button>-->
        <!--              </div>-->
        <!--              <div class="register">-->
        <!--                <el-button type="primary" round>注册</el-button>-->
        <!--              </div>-->
        <!--            </div>-->
        <div class="avatar">
          <img :src="this.avatarUrl">
          <el-dropdown split-button type="primary" class="drop">
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item class="dropItem">
                <hover-avatar>
                </hover-avatar>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class="dropDown">
          <el-dropdown :hide-on-click="false">
                <span class="el-dropdown-link">
                  <i class="el-icon-finished"></i>
                </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <button id="login">登录</button>
              </el-dropdown-item>
              <el-dropdown-item>
                <button id="register">注册</button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
  </el-header>
</template>

<script>
import HoverAvatar from './HoverAvatar'
import qs from "qs";

export default {
  name: "MainHeader",
  components: {HoverAvatar},
  data() {
    return {
      avatarUrl: '',
      courseList: '',
      searchInput: '',
    }
  },
  created() {
    this.avatarUrl = localStorage.getItem("avatar");
  },


  methods: {
    goCode() {
      this.$router.push("/Question")
    },
    async goAll() {
      let {data: res} = await this.$http.post("getAllCourse", {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.$store.state.courseList = res.list;
      await this.$router.push("/AllClass")
    },
    goMain() {
      this.$router.push("/PcMainInterface")
    },
    async getCourseByNameOrMajorOrCourseNum() {
      let course = {
        input: this.searchInput
      }
      let {data: res} = await this.$http.post("getCourseByNameOrMajorOrCourseNum", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.courseList = res.list;
      this.$store.state.courseList = this.courseList;
      this.$message.success("搜索成功，共有" + this.courseList.length + "门课程")
      await this.$router.push("/AllClass");
    }
  }
}
</script>

<style scoped>
.dropItem:hover {
  background-color: white;
}

.drop >>> .el-dropdown__caret-button {
  margin-right: 10px !important;
  height: 76px;
  width: 76px;
  border-radius: 50%;
}

.drop >>> .el-button-group button:nth-child(1) {
  display: none;
}

.drop {
  opacity: 0;
  position: absolute;
  z-index: 10;

}

.item_context {
  display: block;
  font-size: 18px;
  margin-top: 16px;
  color: #999;
  text-align: left;
  margin-bottom: 0;
}

.item_title {
  display: block;
  font-size: 24px;
  line-height: 22px;
  margin-top: 0;
  text-align: left;
  color: #333;
  margin-bottom: 0;
}

.item_img1 {
  display: inline-block;
  float: left;
  margin-top: auto;
  margin-bottom: auto;
  height: 100%;
  width: 25%;
  background: url("../../assets/start.png") no-repeat 0 0;
  background-size: 60px;
  box-sizing: border-box;
}

.item_img2 {
  display: inline-block;
  float: left;
  margin-top: auto;
  margin-bottom: auto;
  height: 100%;
  width: 25%;
  background: url("../../assets/time.png") no-repeat 0 0;
  background-size: 60px;
  box-sizing: border-box;
}

.item_img3 {
  display: inline-block;
  float: left;
  margin-top: auto;
  margin-bottom: auto;
  height: 100%;
  width: 25%;
  background: url("../../assets/book.png") no-repeat 0 0;
  background-size: 60px;
  box-sizing: border-box;
}

.list_item {
  display: inline-block;
  float: left;
  width: 25%;
  height: 100%;
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 5%;
}

.tableList {
  display: table;
  width: 100%;
  height: 35%;
  margin-top: auto;
  margin-bottom: auto;
}

.clear {
  display: flex;
  width: 70%;
  height: 180px;
  background: #d8d6d6;;
  margin-top: 30px;
  margin-left: auto;
  margin-right: auto;
}

.shortVideo video {
  width: 100%;
  height: 100%;
}

.shortVideo {
  width: 50%;
  height: 100%;
  float: left;
}

.context {
  font-size: 24px;
  text-align: left;
  width: 60%;
  padding-left: 30px;
  margin-bottom: 20px;
  margin-top: 50px;
  color: #271e1e;
}

.shortIntroduce hr {
  display: block;
  float: left;
  width: 70%;
  opacity: 0.5;
  border: none;
  margin-left: 35px;
  margin-bottom: 10px;
  border-top: 2.4px solid #293551;
}

.Title {
  font-size: 50px;
  text-align: left;
  padding-left: 30px;
  margin-bottom: 20px;
  color: #484646;
}

.shortIntroduce {
  width: 50%;
  float: left;
}

.classPlate {
  width: 70%;
  height: 500px;
  margin-left: auto;
  margin-right: auto;
}

.joinNow:hover {
  cursor: pointer;
  box-shadow: 1px 2px 10px #000;
}

.joinNow {
  position: absolute;
  right: 72px;
  bottom: 25px;
  text-align: center;
  width: 300px;
  height: 100px;
  font-size: 24px;
  color: #333;
  border: 2px solid #25bae9;
}

.classPeople {
  font-size: 21px;
  color: #333;
  overflow: hidden;
  text-align: left;
  margin-top: 60px;
}

.classTime {
  font-size: 24px;
  color: #333;
  overflow: hidden;
  text-align: left;
  margin-top: 40px;
}

.classTeacher {
  font-size: 25px;
  color: #333;
  overflow: hidden;
  text-align: left;
}

.classTitle {
  font-size: 48px;
  color: #333;
  overflow: hidden;
  text-align: left;
  margin-top: 90px;
}

.schoolPic {
  position: absolute;
  top: -30px;
  right: 28px;
  height: 300px;
  width: auto;
}

.classContent {
  position: relative;
  z-index: 2;
  max-width: 1430px;
  margin: auto;
  border: 1px white solid;
  mix-blend-mode: multiply;
}

.joinClass {
  height: 480px;
  width: 100%;
  position: relative;
  z-index: 2;
  min-height: 160px;
  background: url(https://storagecdn.xuetangx.com/public_assets/xuetangx/xuetangX2018/course_new.png) no-repeat 50%;
  background-size: cover;

}

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
  height: 66px;
  text-align: center;
  z-index: 1;
  margin-left: 20px;
  background-size: 100% 100% !important;
  background: url(../../assets/head-bg.png) no-repeat;
  box-sizing: border-box;

}

.numIcon {
  display: inline-block;
  margin: 0;
  float: right;
  width: 40%;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  word-break: keep-all;
  white-space: nowrap;
  overflow: hidden;
  font-size: 24px;
  height: 30px;
}

.teacherName {
  display: inline-block;
  margin: 0;
  float: left;
  width: 35%;
  text-align: left;
  padding-left: 12px;
  height: 30px;
  text-overflow: ellipsis;
  word-break: keep-all;
  white-space: nowrap;
  overflow: hidden;
  font-size: 24px;
  font-family: "Helvetica Neue", serif
}

.className {
  height: 49%;
  margin-bottom: 1%;
  text-align: left;
  font-size: 28px;
  color: #333;
  font-weight: 500;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.classInfo {
  text-align: left;
  height: 49%;
  margin-bottom: 0.5%;
  color: #666;
  font-size: 20px;
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.classImg img {
  display: block;
  height: 100%;
  width: 100%;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.classImg {
  float: top;
  width: 100%;
  height: 62%;
}

.classIntroduction {
  float: top;
  width: 93%;
  height: 15.2%;
  margin-left: auto;
  margin-right: auto;
  padding: 20px 12px 16px;
}

.itemFooter {
  color: #999;
  overflow: hidden;
  height: 30px;
  margin-top: 3%;
  zoom: 1;
}

.checkMore {
  margin-top: 120px;
  width: 100%;
  height: 120px;
}

.el-footer a {
  display: block;
  float: right;
  margin-right: 25px;
  font-family: "Times New Roman", Times, serif;
  font-size: 20px;
  color: #000000;
}

.el-footer hr {
  display: inline-block;
  float: left;
  border-top: 2.4px solid #0f294d;
  width: 70%;
}

.item:hover {
  cursor: pointer;
  box-shadow: 1px 2px 10px #000;
  bottom: 2%;
}

.item {
  position: relative;
  background-color: #fff;
  border: 2px solid #ddd;
}

.selection {
  display: grid;
  height: 950px;
  margin-top: 30px;
  width: 100%;
  grid-template-columns: repeat(4, 25%);
  grid-gap: 20px;
  grid-template-rows: repeat(2, 50%);
}

.el-icon-caret-left {
  display: block !important;
  margin-top: -8px !important;
  margin-left: -14px !important;
}

.el-icon-caret-right {
  display: block !important;
  margin-top: -8px !important;
  margin-left: -14px !important;
}

.scrollButton {
  width: 100%;
  height: auto;
}

.scrollIconRight {
  float: right;
  width: 40px;
  height: 40px;
  margin-bottom: 15px;
  margin-right: 15px;
  font-size: 30px;
}


.scrollIconLeft {
  float: left;
  width: 40px;
  height: 40px;
  margin-bottom: 15px;
  margin-left: 15px;
  font-size: 30px;
}

.classChoice {
  font-size: 0;
  width: auto;
  white-space: nowrap;
  position: relative;

  z-index: 1;
}

.spanList {
  display: flex;
  overflow-y: hidden;
  overflow-x: hidden;
  width: 100%;
  margin-left: auto;
  margin-right: auto;

}

.classSpan:focus {
  color: #fff !important;
  background: #1769fe !important;
  font-weight: 700 !important;
}

.classSpan:hover {
  color: #4285F4;
  background: rgba(8, 39, 102, 0.1) !important;
}

.classSpan {
  display: inline-block;
  position: relative;
  cursor: pointer;
  font-size: 20px;
  line-height: 45px;
  min-width: 112px;
  height: 45px;
  padding: 0 12px;
  padding-bottom: 0;
  text-align: center;
  border-radius: 25px;
  color: #333;
  background: rgba(65, 130, 250, .1);;
  font-weight: 400;
  margin-right: 20px;
}

.classes h3 {
  font-family: inherit;
  font-weight: 400;
  font-size: 40px;
  text-align: left;
}

.classes {
  width: 90%;
  margin-left: auto;
  margin-right: auto;
  margin-top: 60px;
}

.h_menu a:link {
  text-decoration: none;
  color: red;
}

a:visited {
  text-decoration: none;
  color: inherit;
}

.h_menu a {
  text-decoration: none;
  color: inherit;
}

.checkSchool a {
  display: block;
  float: right;
  margin-right: 25px;
  font-family: "Times New Roman", Times, serif;
  font-size: 20px;
  color: #000000;
}

.hr2 {
  display: block;
  float: left;
  width: 70%;
  opacity: 0.5;
  border: none;
  border-top: 2.4px solid #0f294d;
}

.hr1 {
  width: 90%;
  opacity: 0.5;
  border: none;
  border-top: 2.4px solid #0f294d;
}

.school_img img {
  display: block;
  width: 30%;
  height: 100%;
  object-fit: cover;
  margin-left: auto;
  margin-right: auto;
}

.slideshow img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.checkSchool {
  width: 90%;
  height: 25px;
  margin-left: auto;
  margin-right: auto;
  background-color: white;
}

.school_img {
  width: 80%;
  height: 140px;
  margin-left: auto;
  margin-right: auto;
  background-color: white;
}

.el-footer {
  color: #333;
  text-align: center;
  height: auto;
}

.slideshow >>> .el-carousel__container {
  width: 100%;
  height: 100%;
}

.slideshow >>> .el-carousel {
  width: 100%;
  height: 100%;
}

.el-dropdown {
  font-size: 32px !important;
  margin-right: 25px;
}

.dropDown {
  display: none !important;
}

#login {
  background: none;
  border: none;
}

#register {
  background: none;
  border: none;
}

.register {
  float: left;
  margin-right: 10px;
  margin-left: 10px;
}

.login {
  float: left;
  margin-right: 10px;
  margin-left: 10px;
}

.loginButton {
  flex-shrink: 0;
}

.h_logo {
  width: auto;
  height: 90px;
  margin-right: 40px;
}

.h_header {
  display: flex;
  align-items: center;
  vertical-align: middle;
  -webkit-box-align: center;
  justify-content: center;
  margin-left: auto;
  margin-right: auto;
  background-color: #fff;
  height: 100%;
}

.el-header {
  position: fixed;
  z-index: 10;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  background: white;
  color: #333;
  height: 120px !important;
}


.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  width: 100%;
  height: 768px;
  margin-top: 120px;
  padding: 0;
}

.h_menu a:hover {
  cursor: pointer;
  color: #1769fe;
}

.h_menu {

  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  align-items: center;
  color: rgba(77, 84, 86, 0.805);
}

.h_menu li {
  margin-right: 40px;
  list-style: none;
}

.h_menu a {
  position: relative;
  font-size: 22px;
}

.header {
  width: 94%;
  margin: 0 auto 0 auto;
  height: 100%;
}


/***********************
 * Essential Structure *
 ***********************/
.flexsearch--wrapper {
  height: auto;
  width: auto;
  max-width: 100%;
  overflow: hidden;
  background: transparent;
  margin: 0;
  position: static;
}

.flexsearch--form {
  overflow: hidden;
  position: relative;
}

.flexsearch--input-wrapper {
  padding: 0 66px 0 0;
  /* Right padding for submit button width */
  overflow: hidden;
}

.flexsearch--input {
  width: 100%;
}

/***********************
 * Configurable Styles *
 ***********************/
.flexsearch {
  padding: 0;
  /* Padding for other horizontal elements */
}

.flexsearch--input {
  -webkit-box-sizing: content-box;
  -moz-box-sizing: content-box;
  box-sizing: content-box;
  height: 60px;
  padding: 0 46px 0 10px;
  border-color: #888;
  border-radius: 40px;
  /* (height/2) + border-width */
  border-style: solid;
  border-width: 1px;
  color: #333;
  font-family: 'Helvetica', sans-serif;
  font-size: 26px;
  -webkit-appearance: none;
  -moz-appearance: none;
}

.flexsearch--submit {
  position: absolute;
  right: 0;
  top: 0;
  display: block;
  width: 60px;
  height: 60px;
  padding: 0;
  border: none;
  margin-top: 2px;
  /* margin-top + border-width */
  margin-right: 5px;
  /* border-width */
  background: transparent;
  color: #888;
  font-family: 'Helvetica', sans-serif;
  font-size: 30px;
  line-height: 60px;
}

.flexsearch--input:focus {
  outline: none;
  border-color: #333;
}

.flexsearch--input:focus.flexsearch--submit {
  color: #333;
}

.flexsearch--submit:hover {
  color: #333;
  cursor: pointer;
}

::-webkit-input-placeholder {
  color: #888;
}

input:-moz-placeholder {
  color: #888;
}

/* 放大百分之400 */
@media screen and (max-width: 400px) {

  .checkSchool a {
    font-size: 1px;
  }


  h3 {
    font-size: 8px !important;
  }

  .el-header {
    height: 70px !important;
  }


  .flexsearch--input {
    width: 120px !important;
  }

  .h_menu {
    display: none !important;
  }

  .loginButton {
    display: none !important;
  }

  .avatar {
    display: none;
  }

  .classTeacher {
    display: none;
  }

  .classTime {
    display: none;
  }

  .classPeople {
    display: none;
  }

  .joinNow {
    display: none;
  }

  .schoolPic {
    position: relative;

  }

  .classTitle {
    position: relative;
    width: auto;
    text-align: center !important;
    margin-top: 0;
  }

  .list_item {
    display: block;
    width: 40%;
    padding-left: 10%;
    padding-top: 5%;
  }

  .tableList {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    overflow: hidden;
  }

  .clear {
    height: 600px;

  }

  .item_title {
    font-size: 10px !important;
  }

  .item_context {
    margin-left: 5px !important;
  }

  .Title {
    font-size: 20px;
  }

  .context {
    font-size: 16px;
  }

  .classTitle {
    font-size: 16px;
  }

  .el-main {
    margin-top: 10px;
    overflow: hidden;
    height: 1600px;
  }

  .item_img1 {
    width: 45%;
  }

  .item_img2 {
    width: 45%;
  }

  .item_img3 {
    width: 45%;
  }
}

/* 300% */
@media screen and (max-width: 550px) and (min-width: 400px) {

  .list_item {
    padding-left: 5% !important;
  }

  .item_img1 {
    width: 40%;
  }

  .item_img2 {
    width: 40%;
  }

  .item_img3 {
    width: 40%;
  }

  h3 {
    font-size: 8px !important;
  }


  .el-header {
    height: 70px !important;
  }

  .flexsearch--input {
    width: 200px !important;
  }

  .h_menu {
    display: none !important;
  }

  .loginButton {
    display: none !important;
  }

  .dropDown {
    display: flex !important;
  }

  .avatar {
    display: none;
  }

  .classTeacher {
    display: none;
  }

  .classTime {
    display: none;
  }

  .classPeople {
    display: none;
  }

  .joinNow {
    display: none;
  }

  .schoolPic {
    position: relative;

  }

  .classTitle {
    position: relative;
    width: auto;
    text-align: center !important;
    margin-top: 0;
  }

  .list_item {
    display: block;
    width: 40%;
    padding-left: 15%;
    padding-top: 5%;
  }

  .tableList {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .clear {
    height: 400px;
  }

  .item_title {
    margin-left: 80px;
  }

  .item_context {
    margin-left: 80px;
  }

}

/*250%*/
@media screen and (max-width: 650px) and (min-width: 550px) {


  .item_title {
    margin-left: 80px;
  }

  .item_context {
    margin-left: 80px;
  }

  .dropDown {
    display: flex !important;
  }

  .loginButton {
    display: none;
  }


  .h_menu {
    display: none;
  }

  .avatar {
    display: none;
  }

  .classTeacher {
    display: none;
  }

  .classTime {
    display: none;
  }

  .classPeople {
    display: none;
  }

  .joinNow {
    display: none;
  }

  .schoolPic {
    position: relative;

  }

  .classTitle {
    position: relative;
    width: auto;
    text-align: center !important;
    margin-top: 0;
  }

  .list_item {
    display: block;
    width: 40%;
    padding-left: 15%;
    padding-top: 5%;
  }

  .tableList {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .clear {
    height: 400px;
  }

}

/*200%*/
@media screen and (max-width: 775px) and (min-width: 650px) {

  .el-header {
    height: 76px !important;
  }

  .h_menu {
    display: none;
  }

  .h_logo {
    height: 56px;
  }

  .flexsearch--input {
    width: 380px !important;
  }

  .classTeacher {
    display: none;
  }

  .classTime {
    display: none;
  }

  .classPeople {
    display: none;
  }

  .joinNow {
    display: none;
  }

  .schoolPic {
    position: relative;

  }

  .classTitle {
    position: relative;
    width: auto;
    text-align: center !important;
    margin-top: 0;
  }

  .list_item {
    display: block;
    width: 40%;
    padding-left: 15%;
    padding-top: 5%;
  }

  .tableList {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .clear {
    height: 400px;
  }


}

/*175%*/
@media screen and (max-width: 890px) and (min-width: 775px) {
  .avatar {
    display: none;
  }

  .classTeacher {
    display: none;
  }

  .classTime {
    display: none;
  }

  .classPeople {
    display: none;
  }

  .joinNow {
    display: none;
  }

  .schoolPic {
    position: relative;

  }

  .classTitle {
    position: relative;
    width: auto;
    text-align: center !important;
    margin-top: 0;
  }

  .list_item {
    display: block;
    width: 40%;
    padding-left: 15%;
    padding-top: 5%;
  }

  .tableList {
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .clear {
    height: 400px;
  }


}

/*150%*/
@media screen and (max-width: 1100px) and (min-width: 890px) {
  .avatar {
    display: none;
  }
}

@media screen and (min-width: 1600px) {
  .h_header {
    background-color: rgb(174, 213, 195);
    width: 1800px !important;
  }

}


</style>
