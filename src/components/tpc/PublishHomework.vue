<template>
  <el-container>
    <el-header>
      <teacher-class-header>
      </teacher-class-header>
    </el-header>
    <el-main>
      <div class="checkBox">
        <teacher-check-question>
        </teacher-check-question>
      </div>
      <div class="create">
        <div class="butList">
          <button @click="choose = 'choice'">
            选择题
          </button>
          <button @click="choose = 'judge'">
            判断题
          </button>
          <button @click="choose = 'gap'">
            填空题
          </button>
          <button @click="choose = 'essay'">
            问答题
          </button>
          <button @click="choose = 'multiple'">
            多选题
          </button>
          <button @click="choose = 'programme'">
            编程题
          </button>
          <button @click="addQuestion">
            继续创建
          </button>
          <button @click="finish">
            完成
          </button>
        </div>
        <div class="con" id="checkBox">
          <teacher-choice-question v-if="choose === 'choice'">
          </teacher-choice-question>
          <teacher-gap-question v-else-if="choose === 'gap'">
          </teacher-gap-question>
          <teacher-essay-question v-else-if="choose === 'essay'">
          </teacher-essay-question>
          <teacher-judge-question v-else-if="choose === 'judge'">
          </teacher-judge-question>
          <teacher-multiple-question v-else-if="choose === 'multiple'">
          </teacher-multiple-question>
          <teacher-programme-question v-else-if="choose === 'programme'">
          </teacher-programme-question>
        </div>
      </div>
      <el-dialog
        title="创建考试"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <input class="examName" placeholder="请输入考试名" v-model="examName"></input>
        <span slot="footer" class="dialog-footer">
    <el-button @click="cancel">取 消</el-button>
    <el-button type="primary" @click="teacherCreateExamination">确 定</el-button>
  </span>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script>
import TeacherClassHeader from "./share/TeacherClassHeader";
import TeacherCheckQuestion from "./question/TeacherCheckQuestion";
import TeacherChoiceQuestion from "./question/TeacherChoiceQuestion";
import TeacherMultipleQuestion from "./question/TeacherMultipleQuestion";
import TeacherGapQuestion from "./question/TeacherGapQuestion";
import TeacherJudgeQuestion from "./question/TeacherJudgeQuestion";
import TeacherEssayQuestion from "./question/TeacherEssayQuestion";
import TeacherProgrammeQuestion from "./question/TeacherProgrammeQuestion";
import qs from "qs";

export default {
  name: "PublishHomework",
  components: {
    TeacherMultipleQuestion,
    TeacherClassHeader,
    TeacherCheckQuestion,
    TeacherChoiceQuestion,
    TeacherGapQuestion,
    TeacherJudgeQuestion,
    TeacherEssayQuestion,
    TeacherProgrammeQuestion,
  },
  data() {
    return {
      choose: 'choice',
      dialogVisible: true,
      examName: '',
    }
  },
  created() {
    this.$myBus.$on('changeQuestion', (data) => {
      this.choose = data;
    })
  },
  methods: {
    async finish() {
      let exam = {
        topicList: JSON.stringify(this.$store.state.teacher.topicList),
        optionList: JSON.stringify(this.$store.state.teacher.optionList),
        answerList: JSON.stringify(this.$store.state.teacher.answerList),
      }
      let {data: res} = await this.$http.post("teacherSuppleExamination", qs.stringify(exam), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.$store.commit("resetExam");
      await this.$router.push("/teacherMyRelease")
    },

    addQuestion() {
      /*向总线发送消息表示要增加题目了*/
      this.$myBus.$emit("addQuestion");
      this.$store.commit("topicNumAdd");
      this.$store.commit("setTotalScore", answer);
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    async teacherCreateExamination() {
      this.dialogVisible = false
      let exam = {
        examName: this.examName,
        teacherNum: localStorage.getItem("userId"),
        courseNum: this.$store.state.teacher.courseNum,
      }
      let {data: res} = await this.$http.post("teacherCreateExamination", qs.stringify(exam), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
      if (res.flag === 'right') {
        this.$message.success("创建成功");
        this.$store.commit("setExamNum", res.examNum);
      } else {
        this.$message.error("创建失败");
      }
    },
    cancel() {
      this.$router.push("/teacherMyRelease");
    }
  },
  beforeDestroy() {
    this.$myBus.$off('addQuestion')
  }
  ,
}

</script>

<style scoped>
.examName {
  height: 40px;
  width: 200px;
  border: 2.5px solid #DCDFE6;;
  outline: none;
  border-radius: 0;
  margin-left: 150px;
  font-size: 20px;
  color: #3b3b3b;
}

.con {
  width: 90%;
  height: 60%;
  margin-left: auto;
  margin-right: auto;
}

.butList button:hover {
  box-shadow: 2px 3px 4px 5px #ddeef3;
}

.butList button {
  cursor: pointer;
  width: 120px;
  height: 40px;
  border-radius: 5px;
  background-color: #4e8ee2;
  color: whitesmoke;
  font-size: 20px;
  font-weight: bold;
  margin-right: 20px;
  margin-bottom: 30px;
}

.butList {
  width: 94%;
  margin-left: auto;
  margin-right: auto;
  border-bottom: #d7d6d6 solid 2px;
}

.create {
  float: left;
  display: inline-block;
  width: 800px;
  height: 1000px;
  margin-left: 150px;
}

.checkBox {
  float: left;
  display: inline-block;
  min-height: 800px;
  width: 400px;
  height: 800px;
}

.el-main {
  margin-top: 60px;
  min-height: 1400px;
  padding-left: calc(50% - 700px);
}
</style>
