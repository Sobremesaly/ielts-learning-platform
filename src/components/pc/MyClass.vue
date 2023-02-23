<template>
  <el-container>
    <main-header>

    </main-header>
    <el-main>
      <div class="con">
        <div class="classList" v-for="item in courseList" :id="item.courseNum" @click="clickEvent($event)">
          <div class="classImg"  :style="{backgroundImage: 'url('+item.coursePicture+')'}">
          </div>
          <div class="className">
            {{ item.courseName }}
            <span>
              <i class="el-icon-alarm-clock"></i>
              {{ item.courseEndTime | formatDate }}结课
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
import MainHeader from "./MainHeader";
import qs from "qs";
export default {
  name: "MyClass",
  components: {MainHeader},
  data(){
    return{
      courseList : '',
    }
  },
  mounted() {
    this.getStudentAllCourse();
  },
  methods : {
    clickEvent(event){
      this.$router.push('/ClassStudy');
      this.$store.commit('setStudentCourseNum',event.currentTarget.id);
    },
    async getStudentAllCourse(){
      let user = {
        studentId : localStorage.getItem("userId"),
      }
      let {data:res} = await this.$http.post("getStudentAllCourse", qs.stringify(user), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      if(res.flag === 'right'){
        this.$message.success("获取课程成功!");
        this.courseList = res.list;
        return
      }
      this.$message.error("获取失败!");
    }
  }
}
</script>

<style scoped>
.classList:hover {
  box-shadow: 3px 2px 5px 5PX #dbdbdb;
}
.className span {
  display: block;
  font-size: 16px;
  color: #3d3c3c;
  padding-top: 38%;
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
  width: 30%;
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
  width: 75%;
  margin-left: auto;
  margin-right: auto;
  height: 200px;
  overflow: hidden;
  margin-bottom: 30px;
  cursor: pointer;
  border-radius: 6px;
  position: relative;
  padding: 20px 30px;
}
.con {
  height: 1600px;
  width: 65%;
  margin-top: 120px;
  margin-left: auto;
  margin-right: auto;
  border-top: 2px #f7f1f1 solid;
  overflow: auto;
}
</style>
