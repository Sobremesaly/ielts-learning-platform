<template>
  <div class="con">
    <div class="score">
      <div class="inputBox">
        <label>请输入该题的分值:
          <input v-model="topicScore"></input>
        </label>
      </div>
      <div class="pro">
        <span>请输入题干内容:</span>
        <textarea v-model="topicContext">

        </textarea>
      </div>
      <div class="pro">
        <span>请输入问题答案:</span>
        <textarea v-model="answerContext">

        </textarea>
      </div>
      <div class="butList">
        <button @click="submitQuestion">
          提交
        </button>
        <button @click="deleteQuestion">
          删除
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherEssayQuestion",
  methods: {
    /*删除所选择题目的函数*/
    deleteQuestion() {
      /*当前选择的题目是1往下面排序所有应该减一对应的才是题目号*/
      this.$store.commit("removerOption", this.$store.state.teacher.topicId)
      this.$store.commit("removeAnswer", this.$store.state.teacher.topicId)
      this.$store.commit("removeTopic", this.$store.state.teacher.topicId)
      this.$store.commit("topicNumDelete");
      this.$myBus.$emit("deleteQuestion");
    },
    /*提交问答题，问答题是没有答案和选项的*/
    submitQuestion() {
      /*先构造和后端结构相同的题目,答案,选项数据结构*/
      let topic = {
        topicScore: this.topicScore,
        topicContext: this.topicContext,
        /*考试号,当前题目号都是存在store中的*/
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        topicType: 'essay',
      }

      /*自定义提交到数组的方法*/
      this.$store.commit("addTopic", topic);
      this.$store.commit("setTotalScore");

      let answer = {
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        answerContext: this.answerContext,
      }
      this.$store.commit("addAnswerList", answer);
    },
  },
  data() {
    return {
      topicScore: '',
      topicContext: '',
      answerContext: '',
    }
  },
}
</script>

<style scoped>
.butList button:hover {
  box-shadow: 2px 2px seashell;
  background: #d8d4d4;
}

.butList button {
  cursor: pointer;
  border: 3px solid skyblue;
  background: whitesmoke;
  color: #3b3b3b;
  font-size: 24px;
  margin-left: 170px;
  height: 50px;
  width: 120px;
}

.butList {
  margin-top: 30px;
}

.pro span {
  font-size: 24px;
  color: #3d3c3c;

}

.pro textarea {
  border: 2px solid slategray;
  border-radius: 0;
  width: 100%;
  height: 300px;
  margin-top: 30px;
  outline: none;
  font-size: 30px;
  color: #3b3b3b;
}


.pro {
  margin-top: 30px;
  position: relative;
}

.con {
  width: 100%;
  height: 100%;
}

.inputBox {
  margin-top: 30px;
  font-size: 22px;
  color: #3d3c3c;
  position: relative;
  border-bottom: 2px dashed #585757;
  padding-bottom: 30px;
}

.inputBox input {
  width: 50px;
  border: none;
  outline: none;
  border-bottom: 1px solid slategray;
  margin-left: 5px;
}
</style>
