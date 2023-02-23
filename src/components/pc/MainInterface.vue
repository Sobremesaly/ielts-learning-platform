<template>
  <div>
    <el-container>
      <main-header></main-header>
      <el-main>
        <div class="slideshow">
          <el-carousel>
            <el-carousel-item>
              <img src="../../assets/study.png" alt="">
            </el-carousel-item>
            <el-carousel-item>
              <img src="../../assets/mainA_png.png" alt="">
            </el-carousel-item>
            <el-carousel-item>
              <img src="../../assets/mainB_png.png" alt="">
            </el-carousel-item>
            <el-carousel-item>
              <img src="../../assets/mainC_png.png" alt="">
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-main>
      <hr class="hr1">
      <div class="school_img">
        <img src="../../assets/school_png.png" alt="">
      </div>
      <div class="checkSchool">
        <hr class="hr2">
        <a href="https://www.nchu.edu.cn/">查看南昌航空大学</a>
      </div>
      <el-footer>
        <div class="classes">
          <h3>精选课程</h3>
          <div class="classChoice">
            <div class="scrollButton">
              <el-button class="scrollIconLeft" @click="goLeft">
                <i class="el-icon-caret-left"></i>
              </el-button>
              <el-button class="scrollIconRight" @click="goRight">
                <i class="el-icon-caret-right" style="margin-right:30px"></i>
              </el-button>
            </div>
            <span class="spanList">
              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                软件工程
              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                经济管理

              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                艺术

              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                播音主持

              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                航空航天

              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                食品安全

              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                有机材料

              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                外语

              </span>

              <span tabindex="111" class="classSpan" @click="getCourseByMajor($event)">
                                中文

              </span>

            </span>
          </div>
          <div class="selection">
            <div class="item" :id="item.courseNum" v-for="(item,index) in this.courseList" v-if="index<8"
                 @click="checkEvent($event)">
              <div class="classImg">
                <img :src="item.coursePicture">
              </div>
              <div class="classIntroduction">
                <div class="className">
                  {{ item.courseName }}
                </div>
                <div class="classInfo">
                  {{ item.courseRemark }}
                </div>
              </div>
              <div class="itemFooter">
                <div class="teacherName">
                  <i class="el-icon-s-custom"></i>
                  {{ item.teacherName }}老师
                </div>
                <div class="numIcon">
                  <i class="el-icon-user"></i>
                  {{ item.courseStudentNum }}
                </div>
              </div>
            </div>
          </div>
          <div class="checkMore">
            <hr>
            <a>查看更多</a>
          </div>

        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import HoverAvatar from './HoverAvatar'
import MainHeader from './MainHeader'
import qs from "qs";

export default {
  name: "mainInterface",
  components: {HoverAvatar, MainHeader},
  data() {
    return {
      photoList: [
        "../"
      ],
      courseList: [],
      scrollLeft: 0,
      step: 100,
    };
  },
  mounted() {
    this.getAllCourse();
  },
  methods: {
    async checkEvent(event) {
      this.$store.commit("setStudentCourseNum", event.currentTarget.id);
      let user = {
        studentId: localStorage.getItem("userId"),
        courseNum: event.currentTarget.id,
      }
      let {data: res} = await this.$http.post("checkStudentJoin", qs.stringify(user), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      if (res.join === 'yes') {
        this.$store.commit('setJoinCourse', 'yes');
      } else {
        this.$store.commit('setJoinCourse', 'no');
      }
      this.$store.commit('setStudentCourseNum', user.courseNum)
      await this.$router.push("/joinClass");
    },
    async getCourseByMajor(event) {

      let course = {
        major: event.currentTarget.innerText,
      }
      let {data: res} = await this.$http.post("getCourseByMajor", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.courseList = res.list;
    },
    goRight() {
      let scroll = document.getElementsByClassName("spanList")[0];
      this.scrollLeft += this.step;
      scroll.scrollTo(this.scrollLeft, 0);
    },
    goLeft() {
      let scroll = document.getElementsByClassName("spanList")[0];
      this.scrollLeft = this.scrollLeft - this.step;
      scroll.scrollTo(this.scrollLeft, 0);
    },
    async getAllCourse() {
      let {data: res} = await this.$http.post("getAllCourse", {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      if (res.flag === "error") {
        this.$message.error("获取课程失败");
        return;
      }
      this.courseList = res.list;
    },
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
  margin-left: -68px !important;
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

.slideshow {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
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
  .checkMore {
    margin-top: 200px;
  }

  .selection {
    height: 3600px;
    grid-template-columns: repeat(1, 100%);
    grid-template-rows: repeat(8, 12.5%);
  }

  .checkSchool a {
    font-size: 1px;
  }

  .hr2 {
    width: 50%;
  }

  .classSpan {
    height: 20px;
    min-width: 70px;
    font-size: 5px;
    line-height: 20px;
    padding: 0;
  }

  h3 {
    font-size: 8px !important;
  }

  .el-header {
    height: 70px !important;
  }

  .school_img {
    width: 100%;
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
}

/* 300% */
@media screen and (max-width: 550px) and (min-width: 400px) {
  .selection {
    height: 1800px;
    grid-template-columns: repeat(2, 50%);
    grid-template-rows: repeat(4, 25%);
  }

  .classSpan {
    height: 20px;
    min-width: 70px;
    font-size: 5px;
    line-height: 20px;
    padding: 0;
  }

  h3 {
    font-size: 8px !important;
  }

  .h3 {
    font-size: 10px !important;
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


}

/*250%*/
@media screen and (max-width: 650px) and (min-width: 550px) {
  .selection {
    height: 2000px;
    grid-template-columns: repeat(2, 50%);
    grid-template-rows: repeat(4, 25%);
  }

  .h3 {
    font-size: 10px !important;
  }

  .dropDown {
    display: flex !important;
  }

  .loginButton {
    display: none;
  }

  .school_img {
    height: 90px;
  }

  .h_menu {
    display: none;
  }

  .avatar {
    display: none;
  }


}

/*200%*/
@media screen and (max-width: 775px) and (min-width: 650px) {
  .selection {
    height: 2000px;
    grid-template-columns: repeat(2, 50%);
    grid-template-rows: repeat(4, 25%);
  }

  .el-header {
    height: 76px !important;
  }

  .main {
    margin-top: 76px;
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


}

@media screen and (max-width: 1100px) and (min-width: 775px) {
  .avatar {
    display: none;
  }

  .selection {
    height: 1400px;
    grid-template-columns: repeat(3, 33%);
    grid-template-rows: repeat(3, 33%);
  }
}

@media screen and (min-width: 1600px) {
  .h_header {
    background-color: rgb(174, 213, 195);
    width: 1800px !important;
  }

  .school_img {
    width: 1800px !important;
  }

  .checkSchool {
    width: 1600px !important;
  }

  .slideshow {
    width: 1800px !important;
    margin-left: auto;
    margin-right: auto;
  }

  .classes {
    width: 1600px !important;
  }
}
</style>
