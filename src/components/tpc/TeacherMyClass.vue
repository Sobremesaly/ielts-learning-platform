<template>
  <el-container>
    <teacher-main-header>
    </teacher-main-header>
    <el-main>
      <div class="con">
        <div class="classList" v-for="(item,index) in this.courseList" :id="item.courseNum" @click="clickEvent($event)">
          <div class="classImg" :style="{backgroundImage: 'url('+item.coursePicture+')'}">
          </div>
          <div class="className">
            {{ item.courseName }}
            <span>
              <i class="el-icon-alarm-clock"></i>
              {{ item.courseEndTime | formatDate }}结课
            </span>
          </div>
          <div class="joinClass">
            <button>进入课程</button>
          </div>
        </div>
      </div>
      <el-dialog
        title="创建课程"
        :visible.sync="$store.state.teacher.CreateClass"
        width="30%"
        :before-close="handleClose">
        <div class="create">
          <teacher-create-class>

          </teacher-create-class>
        </div>
        <span slot="footer" class="dialog-footer">
    <el-button @click="$store.commit('setCreateClass',false)">取 消</el-button>
    <el-button type="primary" @click="$store.commit('setCreateClass',false);confirmCreate()">确 定</el-button>
  </span>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script>
import TeacherMainHeader from "./share/TeacherMainHeader";
import TeacherCreateClass from "./TeacherCreateClass";
import qs from "qs";

export default {
  name: "TeacherMyClass",
  components: {
    TeacherMainHeader,
    TeacherCreateClass,
  },
  data() {
    return {
      dialogVisible: false,
      courseList: '',
    };
  },
  created() {
    this.$myBus.$on('courseListChange', (list) => {
      this.courseList = list;
    })
  },
  mounted() {
    this.getTeacherAllCourseCourse();
  },
  methods: {
    clickEvent(event) {
      this.$router.push('/teacherMyRelease');
      this.$store.commit('setCourseNum', event.currentTarget.id);
      let names = event.currentTarget.getElementsByClassName("className")[0].innerText;
      let name = names.split(" ")[0]
      this.$store.commit("setCourseName", name);
    },
    confirmCreate() {
      this.dialogVisible = false;
      this.$myBus.$emit('confirmCreate')
    },
    async getTeacherAllCourseCourse() {
      let user = {
        userId: localStorage.getItem("userId"),
      }
      let {data: res} = await this.$http.post("getTeacherAllCourseCourse", qs.stringify(user), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.courseList = res.list;
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    }
  }
}
</script>

<style scoped>
.el-dialog__wrapper >>> .el-dialog__body {
  padding-bottom: 0;
}

.create {
  height: 500px !important;
}

.el-dialog__wrapper >>> .el-dialog__title {
  display: inline-block;
  font-size: 28px;
  font-weight: 500;
  padding-left: 20px;
  margin-top: 25px;
}

.el-dialog__wrapper >>> .el-dialog {
  margin-top: 10vh !important;
  width: 30% !important;
  height: 700px !important;
}

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
  cursor: pointer;
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
