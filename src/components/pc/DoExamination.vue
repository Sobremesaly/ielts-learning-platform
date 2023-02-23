<template>
  <el-container>
    <el-header>
      <class-header>

      </class-header>
    </el-header>
    <el-main>
      <div class="questionHeader">
        <question-header>
        </question-header>
      </div>
      <div class="checkBox">
        <check-question>

        </check-question>
      </div>
      <div class="create">
        <choice-question v-if="topicList[this.$store.state.student.currentIndex].topicType === 'choice'">

        </choice-question>
        <multiple-question v-else-if="topicList[this.$store.state.student.currentIndex].topicType === 'multiple'">

        </multiple-question>
        <judge-question v-else-if="topicList[this.$store.state.student.currentIndex].topicType === 'judge'">

        </judge-question>
        <essay-question v-else-if="topicList[this.$store.state.student.currentIndex].topicType === 'essay'">

        </essay-question>
        <gap-question v-else-if="topicList[this.$store.state.student.currentIndex].topicType === 'gap'">

        </gap-question>
        <question v-else-if="topicList[this.$store.state.student.currentIndex].topicType === 'programme'">

        </question>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import ClassHeader from "./ClassHeader";
import CheckQuestion from "./question/CheckQuestion"
import ChoiceQuestion from "./question/ChoiceQuestion";
import MultipleQuestion from "./question/MultipleQuestion";
import JudgeQuestion from "./question/JudgeQuestion";
import GapQuestion from "./question/GapQuestion";
import EssayQuestion from "./question/EssayQuestion";
import QuestionHeader from "./question/QuestionHeader"
import Question from "./programmeQuestion/Question"
import qs from "qs";

export default {
  name: "DoExamination",
  components: {
    ClassHeader,
    CheckQuestion,
    ChoiceQuestion,
    QuestionHeader,
    MultipleQuestion,
    JudgeQuestion,
    GapQuestion,
    EssayQuestion,
    Question,
  },
  data() {
    return {
      optionList: [],
      answerList: [],
      topicList: [],
    }
  },
  methods: {
    async getTopicByExamNum() {
      let exam = {
        examNum: this.$store.state.student.examNum,
      }
      let {data: res} = await this.$http.post("getTopicByExamNum", qs.stringify(exam), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.topicList = res.topicList;
      this.$store.commit("setStudentTopicList", this.topicList);
      this.$store.commit("setCurrentIndex", 0)
      this.$store.commit("setSubmitNum", 0)
    },
    async getOptionByExamNum() {
      let exam = {
        examNum: this.$store.state.student.examNum,
      }
      let {data: res} = await this.$http.post("getOptionByExamNum", qs.stringify(exam), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.optionList = res.optionList;
      this.$store.commit("setStudentOptionList", this.optionList);
    }
  },
  mounted() {
    this.getTopicByExamNum();
    this.getOptionByExamNum();
  }
}
</script>

<style scoped>
.questionHeader {
  float: right;
  width: 50%;
  padding-right: 12%;
}

.el-main {
  margin-top: 80px;
}

.create {
  float: left;
  display: inline-block;
  width: 1200px;
  height: 1000px;
  margin-left: 375px;
}

.checkBox {
  float: left;
  display: inline-block;
  min-height: 800px;
  width: 400px;
  height: 800px;
}
</style>
