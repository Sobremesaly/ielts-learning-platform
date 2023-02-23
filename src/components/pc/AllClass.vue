<template>
  <el-container>
    <el-header>
      <main-header>
      </main-header>
    </el-header>
    <el-main>
      <div class="content">
        <p class="selectNum">共搜索到{{ this.courseList.length }}门相关课程</p>
        <div class="classList" v-for="item in this.courseList">
          <div :id="item.courseNum" @click="checkEvent($event)" class="classImg"
               :style="{backgroundImage:'url('+item.coursePicture+')'}">
          </div>
          <div class="className">
            {{ item.courseName }}
            <p class="constructor">
              <i class="el-icon-user-solid"></i>
              <span>
                {{ item.teacherName }}
              </span>
              <i class="el-icon-s-home"></i>
              <span>
                {{ item.courseMajor }}
              </span>
              <i class="el-icon-user"></i>
              <span>
                {{ item.courseStudentNum }}人
              </span>
            </p>
            <p class="p_text">
              {{ item.courseRemark }}
            </p>
            <span class="c_time">
              <i class="el-icon-alarm-clock"></i>
              {{ item.courseEndTime|formatDate }}结课
            </span>
          </div>
          <div class="joinClass">
            <button>进入学习</button>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import MainHeader from './MainHeader'

export default {
  name: "AllClass",
  components: {MainHeader},
  data() {
    return {
      courseList: '',
    }
  },
  filters: {//配置过滤器
    formatDate: function (value) {//调用时间戳为日期显示
      let date = new Date(value)
      let y = date.getFullYear()  //获取年份
      let m = date.getMonth() + 1  //获取月份
      m = m < 10 ? "0" + m : m  //月份不满10天显示前加0
      let d = date.getDate()  //获取日期
      d = d < 10 ? "0" + d : d  //日期不满10天显示前加0
      return y + "-" + m + "-" + d
      //也可以获取更精准时间
      //let h = date.getHours(),                   //小时
      //let m = date.getMinutes(),                 //分
      //let s = date.getSeconds(),                 //秒
      //let ls = date.getMilliseconds()            //毫秒
    },
  },
  methods: {
    checkEvent(event) {
      this.$store.commit("setStudentCourseNum", event.currentTarget.id);
      this.$router.push("/joinClass")
    }
  },
  mounted() {
    this.courseList = this.$store.state.courseList;
  }
}
</script>

<style scoped>
.p_text {
  font-size: 20px;
  font-weight: 480;
  color: #6d6d6d;
  overflow: hidden;
  line-height: 28px;
}

.constructor span {
  padding-right: 40px;
}

.constructor {
  font-size: 16px;
  font-weight: 400;
  color: #838383;
}

.joinClass button {
  float: right;
  height: 60px;
  width: 120px;
  background: #1769fe;
  border: 1px solid #1769fe;
  color: whitesmoke;
  border-radius: 4px;
  font-size: 16px;
  text-align: center;
  margin-top: 20%;
}

.joinClass {
  height: auto;
  width: 40%;
  font-size: 24px;
  color: whitesmoke;
}

.className {
  width: 40%;
  font-size: 28px;
  color: #333333;
  padding-left: 3%;
  line-height: 50px;
  font-weight: 600;
}

.classImg {
  width: 30%;
  object-fit: cover;
  background-image: url(../../assets/course/1_png.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  border-radius: 6px;
}

.classList {
  display: flex;
  width: 99%;
  margin-left: auto;
  margin-right: auto;
  height: 260px;
  overflow: hidden;
  margin-bottom: 30px;
  cursor: pointer;
  border-radius: 6px;
  position: relative;
  padding: 20px 30px;
}

.classList:hover {
  box-shadow: 3px 2px 5px 5PX #dbdbdb;
}

.c_time {
  display: block;
  font-size: 16px;
  color: #3d3c3c;
  padding-top: 5%;
}

.selectNum {
  font-size: 24px;
  font-weight: 450;
  padding-left: 35px;
}

.classList {
  position: relative;
}

.content {
  height: 100%;
  max-width: 1200px;
  background: #fff;
  margin: auto;
  padding: 24px 0;
  font-size: 0;
}
</style>
