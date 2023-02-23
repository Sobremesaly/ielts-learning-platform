<template>
  <div class="con">
    <div class="score">
      <div class="inputBox">
        <label>请输入该题的分值:
          <input v-model="topicScore"></input>
        </label>
      </div>
    </div>
    <div class="buts">
      <button @click="addPro">增加题干</button>
      <button @click="addText">增加填空</button>
      <button @click="goBack">回退删除</button>
    </div>

    <span class="title">生成区域:</span>
    <div class="generate">
      <div class="anBox">
        <label class="changeLabel"></label>
        <input type="text" class="changeInput" placeholder="输入题目"/>
      </div>
      <div class="input-box">
        <label class="input-box__label"></label>
        <input type="text" class="input-box__input" placeholder="输入填空"/>
      </div>
    </div>
    <div class="submit">
      <button class="btn btn-1 hover-filled-opacity" @click="submitQuestion">
        <span>submit</span>
      </button>
      <button class="btn btn-1 hover-filled-opacity" @click="deleteQuestion">
        <span>delete</span>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherGapQuestion",
  mounted() {
    document.querySelector('.input-box__input').addEventListener('input', (e) => {
      document.querySelector('.input-box__label').innerHTML = e.target.value;
    })
  },
  data() {
    return {
      topicScore: '',
      topicContext: '',
    }
  },
  methods: {
    /*填空题要把题目看做选项，填空看做答案*/
    deleteQuestion() {
      /*当前选择的题目是1往下面排序所有应该减一对应的才是题目号*/
      this.$store.commit("removerOption", this.$store.state.teacher.topicId)
      this.$store.commit("removeAnswer", this.$store.state.teacher.topicId)
      this.$store.commit("removeTopic", this.$store.state.teacher.topicId)
      this.$store.commit("topicNumDelete");
      this.$myBus.$emit("deleteQuestion");
    },
    submitQuestion() {
      /*先构造和后端结构相同的题目,答案,选项数据结构*/
      let topic = {
        topicScore: this.topicScore,
        topicContext: '',
        /*考试号,当前题目号都是存在store中的*/
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        topicType: 'gap',
      }

      /*自定义提交到数组的方法*/
      this.$store.commit("addTopic", topic);
      /*题目和填空的数量都是不确定的*/
      /*获取题目和填空的dom*/
      let optionList = document.getElementsByClassName("changeInput");
      let answerList = document.getElementsByClassName("input-box__input");
      let option = {
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        optionContext: optionList[0].value,
      }
      this.$store.commit("addOptionList", option);
      let answer = {
        examNum: this.$store.state.teacher.examNum,
        topicNum: this.$store.state.teacher.topicId,
        answerContext: answerList[0].value,
      }
      this.$store.commit("addAnswerList", answer);
      /*for (let i = 0; i < optionList.length; i++) {*/
      /*如果是最后一个题目而且整体还是以题目结尾要另做判断*/
      /*if (i === optionList.length - 1 && optionList.length > answerList.length) {
        let option = {
          examNum: this.$store.state.teacher.examNum,
          topicNum: this.$store.state.teacher.topicId,
          /!*要拼接成特殊的字符串后端才可以进行处理*!/
          optionContext: this.optionList[i].value,
        }
        this.$store.commit("addOptionList", option);
      } else {
        let option = {
          examNum: this.$store.state.teacher.examNum,
          topicNum: this.$store.state.teacher.topicId,
          /!*要拼接成特殊的字符串后端才可以进行处理*!/
          optionContext: this.optionList[i].value + "@",
        }
        this.$store.commit("addOptionList", option);
      }
    }*/
      /*答案循环提交即可*/
      /*for (let i = 0; i < answerList.length; i++) {
        let answer = {
          examNum: this.$store.state.teacher.examNum,
          topicNum: this.$store.state.teacher.topicId,
          answerContext: answerList[i].value,
        }
        this.$store.commit("addAnswerList", answer);
      }*/
      this.$store.commit("setTotalScore");
    },
    addPro() {
      let first = document.getElementsByClassName('generate')[0].firstChild;
      let clone = first.cloneNode(true);
      document.getElementsByClassName('generate')[0].appendChild(clone);
    },
    addText() {
      let first = document.getElementsByClassName('generate')[0].childNodes[2];
      let clone = first.cloneNode(true);
      document.getElementsByClassName('generate')[0].appendChild(clone);
    },
    goBack() {
      let num = document.getElementsByClassName('generate')[0].childNodes.length;
      if (num === 3) {
        this.$message.error("只剩一道题目了哦!")
      } else {
        document.getElementsByClassName('generate')[0].removeChild(document.getElementsByClassName('generate')[0].lastChild);
      }
    },
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

/*为方便观察，将按钮元素放于屏幕中间
1.top: 50%;left: 50%;代表可以让div块的左上顶点位于页面正中间
2.transform: translate(-50%, -50%);代表让div块向上向左分别偏移50%，这样刚好使得div块位于正中央。
*/
.btn-holder {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}


.btn {
  /*每个按钮应在页面中占有自己的位置，因此选择相对定位*/
  position: relative;
  /*使元素填满父级元素*/
  display: inline-block;
  /*div的width/height设置为auto,那么表示的这个div的所有部分(内容、边框、内边距等的距离加起来)为父元素宽度*/
  width: auto;
  height: auto;
  /*方便观察设置背景色为绿色，演示效果中设置为透明transparent*/
  background-color: transparent;
  border: none;
  /*鼠标悬浮于其上时显示为手型光标*/
  cursor: pointer;
  margin-left: 40px;
}

.btn span {
  /*同样文字也应每个按钮中占有固定的位置，选择相对定位*/
  position: relative;
  /*使元素填满父级元素*/
  display: inline-block;
  /*设置字体大小*/
  font-size: 14px;
  /*设置文字为粗体*/
  font-weight: bold;
  /*设置文字间的间距*/
  letter-spacing: 2px;
  /*定义为大写字母*/
  text-transform: uppercase;
  /*调整子元素在父元素中的位置*/
  padding: 15px 20px;
  /*设置从一个属性过渡到另一个属性的时间*/
  transition: 0.3s;
}

/*--- btn-1 ---*/
/*
使用::before选择器，在button元素之前插入内容背景设置为黑色
position: absolute;绝对定位，相对最近的父元素（btn-holder）来定位，不保留原有空间，并且不会影响其他元素位置
ease-out : 运动形式为减速*/
.btn-1::before {
  position: absolute;
  content: "";
  background-color: rgb(28, 31, 30);
  transition: 0.3s ease-out;

}

/*，设置按钮的边框为黑色
按钮内部及span中文本鼠标hover前字的颜色为白色*/
.btn-1 span {
  color: rgb(255, 255, 255);
  border: 1px solid rgb(28, 31, 30);
  transition: 0.2s 0.1s;
}

/*鼠标hover后，字体变色*/
.btn-1 span:hover {
  color: rgb(28, 31, 30);
  transition: 0.2s 0.1s;
}

/* 5. hover-filled-opacity */
.btn.hover-filled-opacity::before {
  height: 100%;
  width: 100%;
  opacity: 1;
}

.btn.hover-filled-opacity:hover::before {
  opacity: 0;
}

.submit {
  position: relative;
  align-items: center;
  padding-top: 50px;
  padding-left: 25%;
}


.anBox {
  display: inline-flex;
  align-items: center;
  box-sizing: border-box;
  position: relative;
  border: 1.5px solid #cbc8c8;
  border-radius: 5px;
  height: 40px;
  min-width: 50px;
  /* font family is very important */
  font-family: Arial, 'microsoft yahei', serif;
  font-size: 24px;
  margin-top: 20px;
  margin-right: 20px;
}

.input-box {
  display: inline-flex;
  align-items: center;
  margin-right: 20px;
  box-sizing: border-box;
  position: relative;
  border-bottom: 1.5px solid #cbc8c8;
  border-radius: 5px;
  height: 40px;
  min-width: 50px;
  /* font family is very important */
  font-family: Arial, 'microsoft yahei', serif;
  font-size: 24px;
  margin-top: 20px;
}

.changeInput::placeholder {
  color: #8eb7b9;
}

.changeLabel {
  display: inline-block;
  color: #8eb7b9;
  font-size: inherit;
  line-height: normal;
  visibility: hidden;
  font-family: inherit;
  padding: 0 10px;
  min-width: 120px;
}

.changeInput {
  box-sizing: border-box;
  color: #8eb7b9;
  position: absolute;
  display: inline;
  font-size: inherit;
  font-family: inherit;
  line-height: normal;
  border-radius: inherit;
  height: 100%;
  width: 100%;
  outline: 0;
  border: 0;
  margin: 0;
  padding: 0 10px;
  min-width: 120px;
}

.input-box__label {
  display: inline-block;
  color: #4c4b4b;
  font-size: inherit;
  line-height: normal;
  visibility: hidden;
  font-family: inherit;
  padding: 0 10px;
  min-width: 120px;
}

.input-box__input {
  box-sizing: border-box;
  color: #4c4b4b;
  position: absolute;
  display: inline;
  font-size: inherit;
  font-family: inherit;
  line-height: normal;
  border-radius: inherit;
  height: 100%;
  width: 100%;
  outline: 0;
  border: 0;
  margin: 0;
  padding: 0 10px;
  min-width: 120px;
}

.title {
  display: block;
  font-weight: 450;
  font-size: 26px;
  color: #333333;
  margin-top: 25px;
  margin-bottom: 10px;
}

.submit {
  width: 100%;
  margin-right: auto;
  margin-left: auto;
  margin-top: 230px;
  border-top: 2px #cad6e3 solid;
}

.generate {
  width: 100%;
  margin-right: auto;
  margin-left: auto;
  margin-top: 30px;
  border-top: 2px slategray solid;
}

.buts button:hover {
  box-shadow: 2px 4px 2px 2px #dbf0f4;
  color: #4c4b4b;
}

.buts button {
  cursor: pointer;
  color: #d4e9ea;
  background-color: #57c4d6;
  width: 150px;
  font-size: 24px;
  font-weight: 550;
  border-radius: 8px;
  border: none;
  margin-right: 20px;
  padding: 8px;
}

.buts {
  width: 90%;
  position: relative;
  padding-top: 25px;
}

.problem {

}

.con {
  height: 100%;
  width: 100%;
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
