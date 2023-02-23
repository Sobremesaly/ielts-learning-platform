<template>
  <div class="con">
    <div class="body">
      <div class="choiceText">
        <p class="questionTitle">
          |单选题
          <span>
          ({{ this.topicList.topicScore }}分)
        </span>
        </p>
        <p class="questionName">
          {{ this.topicList.topicContext }}
        </p>
        <div class="choiceItem">
          <div v-for="(item,index) in letterList">
            <p class="items" tabindex="0" v-if="selected.indexOf(item) === -1">
            <span class="options" @click="changeSelected($event)">
              {{ item }}
            </span>
              <span class="text">
                {{ contentList[index] }}
              </span>
            </p>
            <p class="items" tabindex="0" v-else>
            <span class="optionSelected" @click="changeSelected($event)">
              {{ item }}
            </span>
              <span class="text">
            {{ contentList[index] }}
            </span>
            </p>
          </div>
        </div>
      </div>
      <div class="answerText">
        <div class="answerTitle">
        <span class="a_title">
          答题区域
        </span>
        </div>
        <div class="answerList">
          <div v-for="item in letterList" style="display: inline-block">
            <span class="c_item_find" v-if="selected.indexOf(item) !== -1">
              {{ item }}
            </span>
            <span class="c_item" v-else>
              {{ item }}
            </span>
          </div>
        </div>
        <div class="answerList">
          <p class="a_true">
            正确答案
          </p>
          <span class="a_item">
            {{ rightAnswer }}
          </span>
        </div>
        <div class="answerList">
          <button class="nextOne" @click="submitAnswer"
                  :disabled="$store.state.student.topicSituation[$store.state.student.currentIndex] != null">
            提交
          </button>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import QuestionHeader from './QuestionHeader'
import ClassHeader from '../ClassHeader'
import qs from "qs";

export default {
  name: "ChoiceQuestion",
  components: {
    QuestionHeader,
    ClassHeader,
  },
  data() {
    return {
      checkFinish: false,
      selected: '',
      topicList: '',
      optionList: '',
      /*用来保存选项字符A,B...*/
      letterList: [],
      /*用来保存选项内容*/
      contentList: [],
      rightAnswer: '?',
    }
  },
  methods: {
    async submitAnswer() {
      let answer = {
        examNum: this.$store.state.student.examNum,
        topicNum: this.topicList.topicNum,
        answer: this.selected,
      }
      let {data: res} = await this.$http.post("verifyAnswer", qs.stringify(answer), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      this.rightAnswer = res.answer;
      /*提交一下做题情况*/
      this.$store.commit("setTopicSituation", res.flag);
      this.$store.commit("setCurrentScore");
      this.$myBus.$emit("scoreChange");
    },
    changeSelected(event) {
      if (this.selected === event.currentTarget.innerHTML) {
        this.selected = '';
      } else {
        this.selected = event.currentTarget.innerText;
      }
    }
  },
  mounted() {
    this.$store.commit("setCurrentOption")
    this.optionList = this.$store.state.student.currentOption;
  },
  created() {
    /*每次需要更新下当前的选项集合*/
    this.$store.commit("setCurrentOption");
    let index = this.$store.state.student.currentIndex;
    this.topicList = this.$store.state.student.topicList[index];
    this.optionList = this.$store.state.student.currentOption;
    for (let i = 0; i < this.optionList.length; i++) {
      let mediator = this.optionList[i].optionContext.split("@");
      this.letterList.push(mediator[0]);
      this.contentList.push(mediator[1]);
    }
  }
}
</script>

<style scoped>

.c_itemSelected {
  position: relative;
  padding: 0 28px 0 60px;
  margin: 40px 0;
  line-height: 30px;
  font-weight: 400;
  cursor: pointer;
  color: whitesmoke;

}

.optionSelected {
  width: 50px;
  height: 50px;
  font-size: 27px;
  line-height: 46px;
  position: absolute;
  top: 50%;
  left: 0;
  cursor: pointer;
  margin: -26px 0 0;
  border: 2px solid rgba(65, 130, 250, .3);
  display: inline-block;
  vertical-align: top;
  text-align: center;
  border-radius: 50%;
  background-color: #4e8ee2;
  color: white;
}

.con {
  height: 100%;
  width: 100%;
}

.nextOne:hover {
  box-shadow: 2px 2px 2px rgba(110, 34, 34, 0.5);
}

.nextOne {
  background: #1769fe;
  position: absolute;
  left: 32%;
  top: 220px;
  height: 50px;
  width: 120px;
  border-radius: 4px;
  border: 1px solid #1769fe;
  cursor: pointer;
  color: whitesmoke;
  font-size: 24px;
}

.answerList {
  position: relative;
  height: auto;
  min-height: 100px;
}

.a_true {
  font-size: 22px;
  color: #252525;
  letter-spacing: 1px;
  line-height: 68px;
  border-bottom: 2px solid #ebebeb;
  margin: 0;
  font-weight: 500;
  position: absolute;
  top: 0;
  left: 30px;
  width: 320px;
}

/*.c_item:focus {*/
/*  color: whitesmoke;*/
/*  background-color: #4e8ee2;*/
/*}*/

.a_item {
  border: 2px solid rgba(65, 130, 250, .3);
  width: 50px;
  height: 50px;
  font-size: 36px;
  line-height: 48px;
  text-align: center;
  border-radius: 50%;
  display: inline-block;
  vertical-align: top;
  position: absolute;
  cursor: pointer;
  top: 100px;
  left: 35%;
  margin: 16px 18px;
  padding-left: 0px;
  color: #1769fe;
}

.c_item_find {
  border: 2px solid rgba(65, 130, 250, .3);
  width: 50px;
  height: 50px;
  font-size: 36px;
  line-height: 48px;
  text-align: center;
  border-radius: 50%;
  display: inline-block;
  vertical-align: top;
  position: relative;
  cursor: pointer;
  margin: 16px 18px;
  padding-left: 0;
  color: whitesmoke;
  background-color: #4e8ee2;
}

.c_item {
  border: 2px solid rgba(65, 130, 250, .3);
  width: 50px;
  height: 50px;
  font-size: 36px;
  line-height: 48px;
  text-align: center;
  border-radius: 50%;
  display: inline-block;
  vertical-align: top;
  position: relative;
  cursor: pointer;
  margin: 16px 18px;
  padding-left: 0px;
  color: #1769fe;

}

.answerTitle {
  font-size: 24px;
  color: #3b3b3b;
  letter-spacing: 1px;
  line-height: 52px;
  border-bottom: 2px solid #d0cece;
  margin: 0;
  font-weight: 500;
  position: absolute;
  top: 0;
  left: 30px;
  width: 340px;
}

.a_title {

}

.answerText {
  background: #fcfcfc;
  border: 2px solid hsla(0, 0%, 81.2%, .31);
  border-radius: 6px;
  display: inline-block;
  vertical-align: top;
  width: 410px;
  margin-top: 36px;
  height: 400px;
  text-align: center;
  padding: 72px 0 80px;
  position: relative;
}

/*.items:focus .options {*/
/*  background-color: #4e8ee2;*/
/*  color: white;*/
/*}*/

.text:hover {
  background-color: #f8f7f7;
}

.text {
  position: relative;
  padding: 0 30px 0 65px;
  margin: 40px 0;
  color: #333;
  font-weight: 400;
  cursor: pointer;
  font-family: sans-serif;
  font-size: 24px;
  line-height: normal;
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
  border: 2px solid rgba(65, 130, 250, .3);
  display: inline-block;
  vertical-align: top;
  text-align: center;
  border-radius: 50%;
}

.items {
  position: relative;
  padding: 0 28px 0 60px;
  margin: 40px 0;
  line-height: 30px;
  color: #333;
  font-weight: 400;
  cursor: pointer;
}

.questionName {
  font-size: 24px;
  margin-bottom: 0;
  height: 60px;
  font-weight: 300;
  color: black;
}

.questionTitle span {
  font-size: 18px;
  color: #999;
  letter-spacing: 0;
}

.questionTitle {
  font-size: 32px;
  color: #333;
  line-height: 40px;
  letter-spacing: 0;
  font-weight: 500;
  margin: 0;
}

.choiceText {
  padding-top: 36px;
  display: inline-block;
  vertical-align: top;
  width: 640px;
}

.body {
  margin-left: auto;
  margin-right: auto;
  height: 830px;
  width: 1070px;
}
</style>
