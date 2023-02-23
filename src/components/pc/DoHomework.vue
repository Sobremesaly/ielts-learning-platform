<template>
  <el-container>
    <el-header>
      <class-header>

      </class-header>
    </el-header>
    <el-main>
      <div class="con">
        <p class="title">{{ homework.homeworkTopic }}</p>
        <div class="workText">
          <div>
            <p class="context">作业内容:</p>
            <p class="require">{{ homework.homeworkContent }}</p>
          </div>
          <a class="linkText" :href="homework.homeworkWord">下载作业文档</a>
        </div>

        <div class="answerList">
          <editor-vue></editor-vue>
        </div>
        <div class="but">
          <button @click="sendHomework">提交</button>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import EditorVue from "../tpc/editor/editorVue";
import ClassHeader from "./ClassHeader";
import qs from "qs";

export default {
  name: "doHomework",
  components: {EditorVue, ClassHeader},
  data() {
    return {
      homework: '',
      wordUrl: '',
    }
  },
  methods: {

    async getHomeworkByNum() {
      let homework = {
        homeworkNum: this.$store.state.student.homeworkNum,
      }
      let {data: res} = await this.$http.post("getHomeworkByNum", qs.stringify(homework), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.homework = res.homework;
      this.wordUrl = homework.homeworkWord;
    },
    sendHomework() {
      this.$myBus.$emit('sendHomework');
    },

  },
  mounted() {
    this.getHomeworkByNum();
  },
  beforeDestroy() {
    this.$myBus.$off('sendHomework');
  }
}
</script>

<style scoped>
.but button:hover {
  color: #1b4179;
  border: 2px #848080 solid;
  background-color: #a8a6a6;
}

.but button {
  cursor: pointer;
  font-size: 24px;
  color: #3b3b3b;
  border: 2px #aeabab solid;
  width: 120px;
  height: 60px;
}

.but {
  position: relative;
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  width: 70%;
}

.answerList {
  position: relative;
  height: auto;
  min-height: 300px;
  margin-left: auto;
  margin-right: auto;
  width: 70%;
  margin-top: 75px;
}

.linkText {
  font-size: 24px;
  color: #2c2c2c;
}

.workText {
  padding-left: 150px;
}

.require {
  font-size: 25px;
  color: #858585;
}

.context {
  font-size: 28px;
  color: #3b3b3b;
}

.con {
  width: 70%;
  margin-left: auto;
  margin-right: auto;
}

.title {
  display: block;
  font-size: 30px;
  font-weight: 500;
  font-family: "Helvetica", serif;
  color: #3d3c3c;
  text-align: center;
  margin-top: 50px;
  border-bottom: 2px solid #3d3c3c;
  padding-bottom: 20px;
}
</style>
