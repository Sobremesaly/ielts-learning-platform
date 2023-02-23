<template>
  <div class="con">
    <div class="score">
      <div class="inputBox">
        <label>请输入该题的分值:
          <input v-model="topicScore"></input>
        </label>
      </div>
    </div>
    <div class="pro">
      <p>请输入题干:</p>
      <textarea v-model="topicContext">

      </textarea>
    </div>
    <div class="option">
      <div class="single">
        <span>
          <i class="el-icon-check tryIcon"></i>
        </span>
        <input v-model="right">

        </input>
      </div>
      <div class="single">
        <span>
          <i class="el-icon-close tryIcon"></i>
        </span>
        <input v-model="error">

        </input>
      </div>
    </div>
    <div class="answer">
      <p>
        正确答案:
      </p>
      <div class="bs">
        <div style="display: inline-block" @click="answer = true">
          <button class="selected" @click="change($event)">
            <i class="el-icon-check noIcon"></i>
          </button>
        </div>
        <div style="display: inline-block" @click="answer = false">
          <button class="noSelected" @click="change($event)">
            <i class="el-icon-close noIcon"></i>
          </button>
        </div>
      </div>
      <div class="frame">
        <button class="custom-btn btn-3" @click="submitQuestion"><span>提交</span></button>
        <button class="custom-btn btn-3" @click="deleteQuestion"><span>删除</span></button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherJudgeQuestion",
  data() {
    return {
      answer: true,
      topicScore: '',
      topicContext: '',
      right: '',
      error: '',
    }
  },
  methods: {
    change(event) {
      if (event.currentTarget.className === 'noSelected') {
        document.getElementsByClassName('selected')[0].className = 'noSelected';
        event.currentTarget.className = 'selected';
      }
    },
    /*删除所选择题目的函数*/
    deleteQuestion() {
      /*当前选择的题目是1往下面排序所有应该减一对应的才是题目号*/
      this.$store.commit("removerOption", this.$store.state.teacher.topicId)
      this.$store.commit("removeAnswer", this.$store.state.teacher.topicId)
      this.$store.commit("removeTopic", this.$store.state.teacher.topicId)
      this.$store.commit("topicNumDelete");
      this.$myBus.$emit("deleteQuestion");
    },
    /*提交单选题的函数*/
    submitQuestion() {

      /*先构造和后端结构相同的题目,答案,选项数据结构*/
      let topic = {
        topicScore: this.topicScore,
        topicContext: this.topicContext,
        /*考试号,当前题目号都是存在store中的*/
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        topicType: 'judge',
      }

      /*自定义提交到数组的方法*/
      this.$store.commit("addTopic", topic);
      /*选项有正确和错误两种*/
      let optionRight = {
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        /*要拼接成特殊的字符串后端才可以进行处理*/
        optionContext: this.right,
      }
      let optionError = {
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        /*要拼接成特殊的字符串后端才可以进行处理*/
        optionContext: this.error,
      }
      this.$store.commit("addOptionList", optionRight);
      this.$store.commit("addOptionList", optionError);
      /*单选题只有一个答案，所有只需要提交一次*/
      let answer = {
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        answerContext: this.answer,
      }
      this.$store.commit("addAnswerList", answer);
      /*提交的话就让题目号加一，题目号是从0依次排列*/
      this.$store.commit("setTotalScore");
    },
  }
}
</script>

<style scoped>
.noIcon {
  position: relative !important;
}

.tryIcon {
  position: absolute !important;
}

.single span {
  font-size: 24px;
}

.single {
  margin-top: 25px;
}

.single input {
  border: none;
  outline: none;
  border-bottom: 2px solid #c6bdb5;
  font-size: 24px;
  color: #3b3b3b;
  margin-left: 40px;
}

.frame {
  width: 90%;
  margin: 40px auto;
  text-align: center;
}

button {
  margin: 20px;
}

.custom-btn {
  width: 130px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, .5),
  7px 7px 20px 0px rgba(0, 0, 0, .1),
  4px 4px 5px 0px rgba(0, 0, 0, .1);
  outline: none;
}

.btn-3 {
  background: rgb(0, 172, 238);
  background: linear-gradient(0deg, rgba(0, 172, 238, 1) 0%, rgba(2, 126, 251, 1) 100%);
  width: 130px;
  height: 40px;
  line-height: 42px;
  padding: 0;
  border: none;

}

.btn-3 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
}

.btn-3:before,
.btn-3:after {
  position: absolute;
  content: "";
  right: 0;
  top: 0;
  background: rgba(2, 126, 251, 1);
  transition: all 0.3s ease;
}

.btn-3:before {
  height: 0%;
  width: 2px;
}

.btn-3:after {
  width: 0%;
  height: 2px;
}

.btn-3:hover {
  background: transparent;
  box-shadow: none;
}

.btn-3:hover:before {
  height: 100%;
}

.btn-3:hover:after {
  width: 100%;
}

.btn-3 span:hover {
  color: rgba(2, 126, 251, 1);
}

.btn-3 span:before,
.btn-3 span:after {
  position: absolute;
  content: "";
  left: 0;
  bottom: 0;
  background: rgba(2, 126, 251, 1);
  transition: all 0.3s ease;
}

.btn-3 span:before {
  width: 2px;
  height: 0%;
}

.btn-3 span:after {
  width: 0%;
  height: 2px;
}

.btn-3 span:hover:before {
  height: 100%;
}

.btn-3 span:hover:after {
  width: 100%;
}

.selected {
  width: 50px;
  height: 50px;
  font-size: 24px;
  font-weight: 500;
  background-color: #87f099;
  color: whitesmoke;
  border: 2px solid rgb(176, 202, 244);
  border-radius: 50%;
  cursor: pointer;
  margin-right: 50px;
}

.bs button:hover {
  background-color: whitesmoke;
  color: #4e8ee2;
  border: 2px solid #21484f;
  transition-delay: 0.1s;
}

.noSelected {
  width: 50px;
  height: 50px;
  font-size: 24px;
  font-weight: 500;
  background-color: #fe1717;
  color: whitesmoke;
  border: 2px solid rgb(38, 101, 157);
  border-radius: 50%;
  cursor: pointer;
  margin-right: 50px;
}

.bs {
  display: inline-block;
  margin-left: 20%;
}

.answer p {
  display: inline-block;
  font-size: 24px;
  color: #3f3e3e;
}

.answer {
  position: relative;
  width: 100%;
  border-top: 2px #696767 dashed;
  margin-top: 35px;
}

.pro p {
  font-size: 24px;
  color: #3f3e3e;
}

.pro textarea {
  font-size: 24px;
  height: 200px;
  color: #292929;
  font-weight: 400;
  width: 100%;
  border-radius: 0;
  outline: none;
  border: 3px solid slategray;
  resize: none;
}

.pro {
  position: relative;
  width: 100%;
  border-top: 2px #696767 dashed;
  margin-top: 35px;
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
}

.inputBox input {
  width: 50px;
  border: none;
  outline: none;
  border-bottom: 1px solid slategray;
  margin-left: 5px;
}
</style>
