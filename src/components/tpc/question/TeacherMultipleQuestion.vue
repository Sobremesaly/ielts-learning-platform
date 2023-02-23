<template xmlns="http://www.w3.org/1999/html">
  <div class="con">
    <div class="score">
      <div class="inputBox">
        <label>请输入该题的分值:
          <input v-model="topicScore"></input>
        </label>
      </div>
      <div class="problem">
        <span>
          请输入题干:
        </span>
        <textarea v-model="topicContext"></textarea>
      </div>
      <div class="add">
        <button @click="addItem">
          增加选项
        </button>
        <button @click="deleteItem">
          删除选项
        </button>
        <button @click="submitQuestion">
          提交
        </button>
        <button @click="deleteQuestion">
          删除
        </button>
      </div>
      <div class="choiceList" id="myList">
        <div class="item" @click="change($event)" v-for="item in checkList">
          <span class="options">{{ item }}</span>
          <input :id="item"></input>
          <p>已选择</p>
        </div>
      </div>
    </div>
    <div class="answer">
      <span>
        正确答案:
      </span>
      <div style="display: inline-block" v-for="item in checkList">
        <input type="checkbox" name="answer" :value="item" v-model="answerList">
        <i>{{ item }}</i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherMultipleQuestion",

  data() {
    return {
      checkList: ['A', 'B', 'C', 'D'],
      radio: 3,
      answerList: [],
      topicContext: '',
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
    /*提交多选题的函数，多选题和单选题的区别就是答案会更多*/
    submitQuestion() {

      /*先构造和后端结构相同的题目,答案,选项数据结构*/
      let topic = {
        topicScore: this.topicScore,
        topicContext: this.topicContext,
        /*考试号,当前题目号都是存在store中的*/
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        topicType: 'multiple',
      }

      /*自定义提交到数组的方法*/
      this.$store.commit("addTopic", topic);
      /*选项有很多且不确定数量，所有遍历选项数组*/
      for (let i = 0; i < this.checkList.length; i++) {
        let option = {
          examNum: this.$store.state.teacher.examNum,
          topicNum: this.$store.state.teacher.topicId,
          /*要拼接成特殊的字符串后端才可以进行处理*/
          optionContext: this.checkList[i] + "@" + document.getElementById(this.checkList[i]).value,
        }
        this.$store.commit("addOptionList", option);
      }
      /*循环遍历答案list*/
      for (let i = 0; i < this.answerList.length; i++) {
        let answer = {
          examNum: this.$store.state.teacher.examNum,
          topicNum: this.$store.state.teacher.topicId,
          answerContext: this.answerList[i],
        }
        this.$store.commit("addAnswerList", answer);
      }
      this.$store.commit("setTotalScore");
    },
    deleteItem() {
      this.checkList.pop();
    },
    addItem() {
      let code = String.fromCodePoint('A'.charCodeAt() + Number(this.checkList.length))
      this.checkList.push(code);
    },

    change(e) {
      if (e.currentTarget.className === 'item') {
        e.currentTarget.className = 'changeItem';
        e.currentTarget.getElementsByTagName('span')[0].className = 'changeOptions';
        e.currentTarget.getElementsByTagName('p')[0].className = 'changeP';
        e.currentTarget.getElementsByTagName('input')[0].className = 'changeInput';
      } else {
        e.currentTarget.className = 'item';
        e.currentTarget.getElementsByTagName('span')[0].className = 'options';
      }
    }
  },


}
</script>

<style scoped>
.answer {
  margin-top: 15px;
  font-size: 24px;
  color: #4c4b4b;
}

.answer input {
  width: 20px !important;
  height: 20px !important;
  margin-right: 10px;
  margin-top: 5px;
}

.answer span {
  display: block;
  font-size: 24px;
  letter-spacing: 2px;
  color: #4c4b4b;
  font-weight: 450;
  margin-bottom: 20px;
}

.add button:hover {
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.5);
  background-color: whitesmoke;
  color: #7ca5e8;
}

.add button {
  outline: none;
  height: 100%;
  width: 150px;
  margin-right: 25px;
  font-size: 24px;
  color: whitesmoke;
  background-color: #66aae2;
  border: 1px slategray solid;
  cursor: pointer;
  border-radius: 5px;
  font-weight: 550;
}

.add {
  margin-top: 5px;
  margin-bottom: 5px;
  height: 50px !important;
}


.problem textarea {
  display: block;
  outline: none;
  width: 99%;
  height: 150px;
  margin-top: 25px;
  margin-bottom: 25px;
  font-size: 20px;
  color: #333;
  border: 2px solid #a2c6fc;
}

.problem span {
  font-size: 24px;
  color: #4c4b4b;
  font-weight: 400;
}

.problem {
  margin-top: 30px;
  margin-bottom: 30px;
}

.changeOptions {
  width: 50px;
  height: 50px;
  font-size: 27px;
  line-height: 46px;
  position: absolute;
  color: #f9f9f9;
  top: 50%;
  left: 0;
  cursor: pointer;
  margin: -26px 0 0;
  border: 3px solid rgba(225, 228, 233, 0.5);
  display: inline-block;
  vertical-align: top;
  text-align: center;
  border-radius: 50%;
  z-index: 3;
  background-color: #4e8ee2;
}

.changeItem {
  position: relative;
  cursor: pointer;
  background-color: #ededed;
  margin-bottom: 10px;
}

.changeInput {
  position: relative;
  font-size: 24px;
  width: 150px;
  height: 50px;
  border: none;
  outline: none;
  border-bottom: 1px solid slategray;
  margin: 20px 0 15px 80px;
  padding: 0 30px 0 50px;
  background-color: #ededed;
}

.changeP {
  display: inline-block;
  font-size: 24px;
  font-weight: 550;
  margin-bottom: 0;
  margin-left: 20px;
  color: #3d3c3c;
}

.item p {
  display: inline-block;
  font-size: 24px;
  font-weight: 550;
  margin-bottom: 0;
  margin-left: 20px;
  color: #3d3c3c;
}

.item input {
  position: relative;
  font-size: 24px;
  width: 150px;
  height: 50px;
  border: none;
  outline: none;
  border-bottom: 1px solid slategray;
  margin: 20px 0 15px 80px;
  background-color: white;
  padding: 0 30px 0 50px;
}

.item {
  cursor: pointer;
  margin-bottom: 10px;
  position: relative;
}

.options {
  width: 50px;
  height: 50px;
  font-size: 27px;
  line-height: 46px;
  position: absolute;
  color: #4285F4;
  top: 50%;
  left: 0;
  cursor: pointer;
  margin: -26px 0 0;
  border: 3px solid rgba(65, 130, 250, .5);
  display: inline-block;
  vertical-align: top;
  text-align: center;
  border-radius: 50%;
  z-index: 3;
}

.choiceList {
  width: 100%;
  margin-top: 10px;
  overflow: auto;
}

.inputBox {
  margin-top: 30px;
  font-size: 22px;
  color: #3d3c3c;
}

.inputBox input {
  width: 50px;
  border: none;
  outline: none;
  border-bottom: 1px solid slategray;
  margin-left: 5px;
}

.con {
  height: 100%;
  width: 100%;
}
</style>
