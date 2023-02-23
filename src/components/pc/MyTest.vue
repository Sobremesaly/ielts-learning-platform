<template>
  <el-container>
    <el-header>
      <class-header>

      </class-header>
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
          </div>
          <div class="foot">
            <button :id="item.testNum" class="fi_bt" @click="doTest($event)">去做实验</button>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import classHeader from "./ClassHeader";
import {Time, History} from '@icon-park/vue'
import qs from "qs";

export default {
  name: "MyTest",
  components: {
    classHeader,
    Time,
    History,
  },
  data() {
    return {
      testList: [],
    }
  },
  methods: {
    doTest(event) {
      this.$store.commit("setTestNum", event.currentTarget.id)
      this.$router.push("doTest");
    },
    async getTestByCourseNum() {
      let course = {
        courseNum: this.$store.state.student.courseNum,
      }
      let {data: res} = await this.$http.post("getTestByCourseNum", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.testList = res.list;
    }
  },
  mounted() {
    this.getTestByCourseNum();
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
  margin-top: 55px;
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
  width: 383px;
  height: 300px;
  margin: 20px 40px 20px 30px;
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
