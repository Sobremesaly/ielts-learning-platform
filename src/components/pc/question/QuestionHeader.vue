<template>
  <div class="t_box">
    <div class="q_next">
      <i class="el-icon-arrow-left" @click="preQuestion"></i>
      <span class="current">{{ this.$store.state.student.currentIndex + 1 }}</span>
      /
      <span class="total">{{ this.$store.state.student.topicList.length }}</span>
      <i class="el-icon-arrow-right" @click="nextQuestion"></i>
    </div>
    <el-button type="primary" round @click="finishExam">交卷</el-button>
    <span class="progress">
      已完成{{ this.$store.state.student.finishNum }} / {{ this.$store.state.student.topicList.length }}
    </span>
    <span class="sheet">
      <i class="el-icon-bank-card"></i>
      答题卡
    </span>
  </div>
</template>

<script>
import qs from "qs";

export default {
  name: "QuestionHeader",
  methods: {
    async finishExam() {
      let exam = {
        examNum: this.$store.state.student.examNum,
        courseNum: this.$store.state.student.courseNum,
        examScore: this.$store.state.student.currentScore,
        studentNum: localStorage.getItem("userId"),
      }
      let {data: res} = await this.$http.post("finishExam", qs.stringify(exam), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
      if (res.flag === 'right') {
        this.$message.success("交卷成功!")
        await this.$router.push("/myExamination");
      } else {
        this.$message.error("交卷失败!")
      }
    },
    preQuestion() {
      if (this.$store.state.student.currentIndex - 1 === -1) {
        this.$message.warning("现在已经是第一题了哇!");
      } else {
        this.$store.commit("preQuestion");
      }
    },
    nextQuestion() {
      if (this.$store.state.student.currentIndex + 1 === this.$store.state.student.topicList.length) {
        this.$message.warning("已经是最后一题了嗷!");
      } else {
        this.$store.commit("nextQuestion");
      }

    },
  }
}
</script>

<style scoped>
i:hover {
  cursor: pointer;
  color: #1769fe;
}

.sheet i {
  font-size: 30px;
}

.sheet {
  font-size: 24px;
  color: #333;
  letter-spacing: .5px;
  margin-left: 20px;
}

.progress {
  margin-left: 10%;
  display: inline-block;
  font-size: 16px;
  padding-top: 3px;
}

.q_next {
  display: flex;
  margin-left: 43%;
  width: auto;
  height: auto;
}

.el-icon-arrow-right {
  padding-top: 2px;
  font-size: 24px;
  margin-left: 18px;
}

.total {
  display: inline-block;
  vertical-align: middle;
  position: relative;
  top: 2px;
  font-size: 20px;
  color: #999;
  line-height: 26px;
}

.el-icon-arrow-left {
  padding-top: 2px;
  font-size: 24px;
}

.current {
  display: inline-block;
  vertical-align: middle;
  color: #1769fe;
  font-size: 30px;
  line-height: 26px;
}

.t_box {
  display: flex;
  font-weight: 400;
  border-bottom: 1.5px solid #ebebeb;
  padding: 20px;
  position: relative;
  width: 1000px;
}
</style>
