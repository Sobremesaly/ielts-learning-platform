<template>
  <div class="con">
    <div class="inputBox">
      <label>请输入该题的分值:
        <input></input>
      </label>
    </div>
    <div class="pro">
      <span class="context">
        输入题干:
      </span>
      <textarea v-model="context">

      </textarea>
    </div>
    <div class="pro">
      <span class="context">
        输入格式:
      </span>
      <textarea v-model="inputFormat">

      </textarea>
    </div>
    <div class="pro">
      <span class="context">
        输出格式:
      </span>
      <textarea v-model="outputFormat">

      </textarea>
    </div>
    <div class="sample">
      <div class="sampleOne">
        <span>
          输入样例:
        </span>
        <input v-model="inputSample"></input>
      </div>
      <div class="sampleOne">
        <span>
          输出样例:
        </span>
        <input v-model="outputSample"></input>
      </div>
    </div>
    <div class="butList">
      <button @click="addCases">
        增加测试
      </button>
      <button @click="deleteCases">
        删除测试
      </button>
      <button @click="submitQuestion">
        提交
      </button>
      <button @click="deleteQuestion">
        删除
      </button>
    </div>

    <div class="testList">
      <div class="testOne">
        <div class="testLeft">
          <span>
          输入
          </span>
          <input class="test"></input>
        </div>
        <div class="testRight">
          <span>
          输出
          </span>
          <input class="answer"></input>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import qs from "qs";

export default {
  name: "TeacherProgrammeQuestion",
  data() {
    return {
      context: '',
      inputFormat: '',
      outputFormat: '',
      inputSample: '',
      outputSample: '',
      topicScore: '',
    }
  },

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
    /*编程题的题干，输入格式，样例等都应该存在题目中*/
    submitQuestion() {
      /*先构造和后端结构相同的题目,答案,选项数据结构*/
      let topic = {
        topicScore: this.topicScore,
        topicContext: this.context + "@" + this.inputFormat + "@" + this.outputFormat + "@" + this.inputSample + "@" + this.outputSample,
        /*考试号,当前题目号都是存在store中的*/
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        topicType: 'programme',
      }
      /*自定义提交到数组的方法*/
      this.$store.commit("addTopic", topic);
      /*编程题可以看做没有选项，输入输出都是答案*/
      let inputList = document.getElementsByClassName("test");
      let outputList = document.getElementsByClassName("answer")
      for (let i = 0; i < inputList.length; i++) {
        let answer = {
          examNum: this.$store.state.teacher.examNum,
          topicNum: this.$store.state.teacher.topicId,
          answerContext: inputList[i].value,
        }
        this.$store.commit("addAnswerList", answer);
      }
      for (let i = 0; i < outputList.length; i++) {
        let answer = {
          examNum: this.$store.state.teacher.examNum,
          topicNum: this.$store.state.teacher.topicId,
          answerContext: outputList[i].value,
        }
        this.$store.commit("addAnswerList", answer);
      }
      this.$store.commit("setTotalScore");
    },
    addCases() {
      let clone = document.getElementsByClassName("testOne")[0].cloneNode(true);
      document.getElementsByClassName("testList")[0].appendChild(clone);
    },
    deleteCases() {
      let father = document.getElementsByClassName("testList")[0];
      if (father.childNodes.length === 1) {
        this.$message.error("只剩一个用例了哦！");
        return;
      }
      father.removeChild(father.childNodes[1]);
    },

  }
}

</script>

<style scoped>
.sampleOne {
  margin-top: 15px;
}

.sampleOne span {
  display: inline-block;
  font-size: 24px;
}

.sampleOne input {
  display: inline-block;
  border: none;
  border-bottom: 2px solid #878383;
  font-size: 24px;
  font-weight: 500;
  color: #3d3c3c;
  background: none;
  outline: none;
}

.accounted input {
  border: none;
  border-bottom: 2px solid slategray;
  width: 80px;
  outline: none;
  font-size: 24px;
}

.accounted span {
  display: inline-block;
  line-height: 38px;
  font-size: 22px;
  font-weight: 450;
  color: #4c4b4b;
  margin-right: 5px;
  letter-spacing: 5px;
}

.testRight span {
  display: inline-block;
  background-color: #e2dada;
  height: 40px;
  font-size: 18px;
  width: 45px;
  color: #333;
  line-height: 38px;
  padding-left: 15px;
  border: 0;
  border-bottom-left-radius: 15px;
  border-top-left-radius: 15px;
}

.testRight {
  display: flex;
  margin-bottom: 10px;
}

.testLeft span {
  display: inline-block;
  background-color: #e2dada;
  height: 40px;
  font-size: 18px;
  width: 45px;
  color: #333;
  line-height: 38px;
  padding-left: 15px;
  border: 0;
  border-bottom-left-radius: 15px;
  border-top-left-radius: 15px;
}

.testLeft {
  display: flex;
  margin-bottom: 10px;
  margin-top: 30px;
}

.answer {
  outline: none;
  height: 35px;
  background-color: #a4a4a4;
  border: 1.5px solid #d7e2e2;
  border-left: 0;
  color: whitesmoke;
}

.test {
  outline: none;
  height: 35px;
  background-color: #a4a4a4;
  border: 1.5px solid #d7e2e2;
  border-left: 0;
  color: whitesmoke;
}

.testOne {
  margin-top: 15px;
  position: relative;
}

.testList {
  position: relative;
}

.butList button:hover {
  box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
  4px 4px 6px 0 rgba(116, 125, 136, .5),
  inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
  inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
}

.butList button {
  cursor: pointer;
  margin-right: 25px;
  height: 45px;
  width: 120px;
  background-color: white;
  font-size: 20px;
  font-weight: 400;
  color: #3d3c3c;
  border: 3px solid #8b5353;
  /*border-image: radial-gradient(#ace1f1, #211799) 40 / 5px;*/
}

.butList {
  margin-top: 20px;
  position: relative;
}

.pro textarea {
  border: 2px solid slategray;
  border-radius: 0;
  width: 100%;
  height: 150px;
  margin-top: 30px;
  outline: none;
}

.context {
  font-size: 24px;
  color: #3d3c3c;
  font-family: "Helvetica Neue", serif;
  font-weight: 550;
}

.pro {
  width: 100%;
  margin-top: 30px;
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
