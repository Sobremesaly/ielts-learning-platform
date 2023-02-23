<template>
  <el-container>
    <el-header>
      <teacher-class-header>
      </teacher-class-header>
    </el-header>
    <el-main>
      <div class="con">
        <div class="classOne" v-for="item in testList">
          <div class="title">
            <span class="t_title">
              {{ item.testName }}
            </span>
          </div>
          <div class="time">
            <div class="startTime">
              开始时间:
              <div class="fixed">
                <Time size="18">
                </Time>
              </div>
              <div class="timeNum">
                {{ item.testStartTime | formatDateMore }}
              </div>
            </div>
            <div class="endTime">
              截止时间:
              <div class="fixed">
                <History size="18">

                </History>
              </div>
              <span class="timeNum">
                 {{ item.testEndTime | formatDateMore }}
              </span>
            </div>
            <div class="submitNum">
              <span>提交数:</span>
              <span>0</span>
              <span>/50</span>
            </div>
          </div>
          <div class="foot">
            <span>50份</span>
            <span>待批阅</span>
            <button :id="item.testNum" class="fi_bt" @click="setTestTime($event)">设置时间</button>
            <button class="se_bt">查看</button>
          </div>
        </div>
        <div class="classOne" v-for="item in examList">
          <div class="title">
            <span class="t_title">
              {{ item.examName }}
            </span>
          </div>
          <div class="time">
            <div class="startTime">
              开始时间:
              <div class="fixed">
                <Time size="18">
                </Time>
              </div>
              <div class="timeNum">
                {{ item.examStartTime | formatDateMore }}
              </div>
            </div>
            <div class="endTime">
              截止时间:
              <div class="fixed">
                <History size="18">

                </History>
              </div>
              <span class="timeNum">
                 {{ item.examEndTime | formatDateMore }}
              </span>
            </div>
            <div class="submitNum">
              <span>提交数:</span>
              <span>0</span>
              <span>/50</span>
            </div>
          </div>
          <div class="foot">
            <span>50份</span>
            <span>待批阅</span>
            <button :id="item.examNum" class="fi_bt" @click="setExamTime($event)">设置时间</button>
            <button class="se_bt">查看</button>
          </div>
        </div>
        <div class="classOne" v-for="item in workList">
          <div class="title">
            <span class="t_title">
              {{ item.homeworkTopic }}
            </span>
          </div>
          <div class="time">
            <div class="startTime">
              开始时间:
              <div class="fixed">
                <Time size="18">
                </Time>
              </div>
              <div class="timeNum">
                {{ item.homeworkStartTime | formatDateMore }}
              </div>
            </div>
            <div class="endTime">
              截止时间:
              <div class="fixed">
                <History size="18">

                </History>
              </div>
              <span class="timeNum">
                 {{ item.homeworkEndTime | formatDateMore }}
              </span>
            </div>
            <div class="submitNum">
              <span>提交数:</span>
              <span>0</span>
              <span>/50</span>
            </div>
          </div>
          <div class="foot">
            <span>50份</span>
            <span>待批阅</span>
            <button :id="item.homeworkNum" class="fi_bt" @click="setWorkTime($event)">设置时间</button>
            <button class="se_bt">查看</button>
          </div>
        </div>
      </div>


      <el-dialog
        title="截止时间设置"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <div class="setTime">
          <teacher-set-time>
          </teacher-set-time>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false" class="deleteWork">删 除</el-button>
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="setTime">确 定</el-button>
  </span>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script>
import {Time, History} from '@icon-park/vue'
import TeacherClassHeader from './share/TeacherClassHeader'
import TeacherSetTime from "./share/TeacherSetTime";
import TeacherMainHeader from "./share/TeacherMainHeader";
import qs from "qs";
import {dateFormat} from "../../share/timeFormate";

export default {
  name: "TeacherMyRelease",
  components: {
    TeacherSetTime,
    TeacherMainHeader,
    TeacherClassHeader,
    Time,
    History,
  },

  beforeCreate() {
    this.courseNum = this.$store.state.teacher.courseNum;
  },
  mounted() {
    this.$myBus.$on('timeChange', (data) => {
      this.time = data;
    })
    this.getExaminationByCourseNum();
    this.getTestByCourseNum();
    this.getHomeworkByCourseNum();

  },
  data() {
    return {
      dialogVisible: false,
      courseNum: '',
      examList: [],
      workList: [],
      testList: [],
      time: [],
      selectId: '',
      type: '',
    };
  },

  methods: {
    async setTime() {
      this.dialogVisible = false;
      if (this.type === 'exam') {
        let exam = {
          examNum: this.selectId,
          endTime: dateFormat(this.time),
        }
        let {data: res} = await this.$http.post("setExamTime", qs.stringify(exam), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
        if (res.flag === 'right') {
          this.examList = res.list;
          this.$message.success("设置成功");
        } else {
          this.$message.error("设置失败");
        }
      } else if (this.type === 'work') {
        let work = {
          homeworkNum: this.selectId,
          endTime: dateFormat(this.time),
        }
        let {data: res} = await this.$http.post("setWorkTime", qs.stringify(work), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
        if (res.flag === 'right') {
          this.workList = res.list;
          this.$message.success("设置成功");
        } else {
          this.$message.error("设置失败");
        }
      } else if (this.type === 'test') {
        let test = {
          testNum: this.selectId,
          endTime: dateFormat(this.time),
        }
        let {data: res} = await this.$http.post("setTestTime", qs.stringify(test), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
        if (res.flag === 'right') {
          this.testList = res.list;
          this.$message.success("设置成功");
        } else {
          this.$message.error("设置失败");
        }
      }
    },
    /*如果要修改时间的是考试*/
    async setExamTime(event) {
      this.type = 'exam';
      this.selectId = event.currentTarget.id;
      this.dialogVisible = true;
    },

    async setWorkTime(event) {
      this.type = 'work';
      this.selectId = event.currentTarget.id;
      this.dialogVisible = true;
    },

    async setTestTime(event) {
      this.type = 'test';
      this.selectId = event.currentTarget.id;
      this.dialogVisible = true;
    },

    async getTestByCourseNum() {
      let course = {
        courseNum: this.$store.state.teacher.courseNum,
      }
      let {data: res} = await this.$http.post("getTestByCourseNum", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.testList = res.list;
    },
    async getHomeworkByCourseNum() {
      let course = {
        courseNum: this.$store.state.teacher.courseNum,
      }
      let {data: res} = await this.$http.post("getHomeworkByCourseNum", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.workList = res.list;
    },
    /*根据课程号获取考试*/
    async getExaminationByCourseNum() {
      let course = {
        courseNum: this.$store.state.teacher.courseNum,
      }
      let {data: res} = await this.$http.post("getExaminationByCourseNum", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.examList = res.list;
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
.deleteWork:hover {
  color: #8acdcf !important;
}

.deleteWork {
  background-color: #f45757 !important;
}

.el-dialog__wrapper >>> .el-dialog__title {
  display: inline-block;
  font-size: 28px !important;
  color: #333333;
  font-weight: 500;
  margin-left: 30px;
  margin-top: 15px;
}

.el-dialog__wrapper >>> .el-dialog {
  margin-top: 30vh !important;
  width: 30% !important;
  height: 300px !important;
}

.se_bt {
  cursor: pointer;
  font-size: 18px;
  background-color: #dfdfdf;
  height: 30px;
  width: 80px;
  border-radius: 5px;
  outline: none;
  font-weight: 500;
  line-height: 18px;
  margin-top: 23px;
  margin-left: 15px;
}

.fi_bt {
  cursor: pointer;
  font-size: 22px;
  background-color: #1769fe;
  color: whitesmoke;
  height: 50px;
  width: 120px;
  border-radius: 8px;
  outline: none;
  font-weight: 550;
  margin-left: 30px;
  margin-top: 5px;
}

.foot span:nth-child(2) {
  display: inline-block;
  font-size: 20px;
  color: #333333;
  line-height: 68px;
  margin-top: 5px;
}

.foot span:nth-child(1) {
  display: inline-block;
  font-size: 36px;
  color: #e66e6e;
  margin-left: 20px;
  margin-top: 5px;
}

.foot {
  display: flex;
  float: bottom;
  align-content: space-around;
  width: 100%;
  background-color: #929292;
  height: 30%;
  margin-top: 25px;
  flex-wrap: wrap;

}

.submitNum {
  margin-top: 30px;
  padding-left: 20px;
}

.submitNum span:nth-child(3) {
  font-size: 26px;
  color: #151414;
  opacity: 0.9;
  letter-spacing: 2px;
}

.submitNum span:nth-child(2) {
  font-size: 18px;
  font-weight: 550;
  color: #252525;
  opacity: 0.7;
  margin-left: 5px;
}

.submitNum span:nth-child(1) {
  font-size: 20px;
  color: #343434;
  opacity: 0.7;
  padding: 0;
}

.timeNum {
  display: inline-block;
  color: #3d3c3c;
  opacity: 0.8;
  margin-left: 18px;
}

.fixed {
  width: 20px;
  position: relative;
}

.i-icon-history {
  display: block;
  position: absolute;
  margin-left: 10px;
  bottom: -5px;
}

.i-icon-time {
  display: block;
  position: absolute;
  margin-left: 10px;
  bottom: -5px;
}

.endTime {
  display: flex;
  font-size: 20px;
  color: #4c4b4b;
  font-weight: 450;
  margin-top: 25px;
  padding-left: 20px;
  letter-spacing: 1px;
}

.startTime {
  display: flex;
  font-size: 20px;
  color: #4c4b4b;
  font-weight: 450;
  margin-top: 35px;
  padding-left: 20px;
  letter-spacing: 1px;
}

.time {
  position: relative;
}

.title {
  margin-top: 10px;
  padding-left: 20px;
  line-height: 30px;
}

.t_title {
  font-size: 24px;
  font-weight: 550;
  color: #333;
}

.classOne {
  position: relative;
  display: inline-block;
  border: 2.5px solid #a1a1a1;
  width: 430px;
  height: 350px;
  margin: 20px 40px 20px 70px;
  background-color: whitesmoke;
}

.con {
  border-top: 2px slategray solid;
  width: 90%;
  margin-right: auto;
  margin-left: auto;
  background-color: #f4f4f4;
}

.el-main {
  margin-top: 50px;
  width: 90%;
  margin-right: auto;
  margin-left: auto;
}
</style>
