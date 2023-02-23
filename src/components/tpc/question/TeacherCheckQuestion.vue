<template>
  <div class="con">
    <div class="content">
      <span>答题卡</span>
    </div>
    <div class="scoreList">
      <div class="item">
        <span>
          总分
        </span>
        <span>
          {{ this.$store.state.teacher.totalScore }}
        </span>
        <span>
          分
        </span>
      </div>
      <div class="item">
        <span>
          共
        </span>
        <span>
          {{ this.$store.state.teacher.topicNum + 1 }}
        </span>
        <span>
          题
        </span>
      </div>
    </div>
    <div class="answerList">
      <div class="result" v-for="(item,index) in problemList">
        <span :id="index" class="singleNoResult" tabindex="1" @click="listenClick($event)">
          {{ index + 1 }}
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherCheckQuestion",
  data() {
    return {
      totalScore: 0,
      problemNum: 0,
      problemList: [1],
      targetId: '',
    }
  },
  mounted() {
    document.getElementById("0").className = 'singleNoResult singleNoResultFocus';
  },
  created() {
    this.$myBus.$on('addQuestion', () => {
      // 函数体内即为代码逻辑
      this.problemList.push(this.problemList.length + 1)
    })
    this.$myBus.$on('deleteQuestion', () => {
      this.problemList.pop();
    })
  },
  methods: {
    /*点击侧边栏开始改变题目*/
    changeQuestion(index) {
      let data = this.$store.state.teacher.topicList[index].topicType;
      this.$myBus.$emit("changeQuestion", data);
    },
    /*让老师当前选择的题目对应的题目号亮起*/
    listenClick(event) {
      this.targetId = event.currentTarget.id;
      /*把当前选择的题目号保存在store*/
      this.$store.commit("setTopicId", event.currentTarget.id);
      let targets = document.getElementsByClassName("singleNoResult");
      /*遍历把不是当前被选择的按钮样式给还原回去*/
      for (let i = 0; i < targets.length; i++) {
        if (i === Number(this.targetId)) {
          targets[i].className = 'singleNoResult singleNoResultFocus';
        } else {
          targets[i].className = 'singleNoResult';
        }
      }
      this.changeQuestion(this.targetId);
    },
  },
  beforeDestroy() {
    this.$myBus.$off('addQuestion')
  }

}
</script>

<style scoped>


.singleNoResult {
  display: block;
  width: 60px;
  height: 60px;
  background: rgba(167, 173, 186, 0.1);
  line-height: 60px;
  text-align: center;
  font-size: 28px;
  color: #1b4179;
  margin-right: auto;
  margin-left: auto;
  border: 1px solid #a7dddf;
}

.singleNoResult:hover {
  color: #e29a9a !important;
  background: rgba(96, 104, 118, 0.1) !important;
}

.singleNoResultFocus {
  color: #31e691 !important;
  background: rgba(98, 106, 121, 0.1) !important;
  border: 1px solid #51222e;
}

.singleResultRight {
  display: block;
  width: 25px;
  height: 25px;
  background: url(../../../assets/right.png) no-repeat;
  background-size: contain;
  margin-right: auto;
  margin-left: auto;
  margin-top: -15px !important;
}

.result {
  cursor: pointer;
  display: inline-block;
  width: 100px;
  height: 100px;
}


.answerList {
  padding-top: 20px;
  width: 90%;
  margin-left: auto;
  margin-right: auto;
  height: auto;
}

.item span:nth-child(2) {
  font-size: 30px;
  color: #333;
  font-weight: 450;
  letter-spacing: 0;
}

.item span:nth-child(1) {
  font-size: 18px;
  color: #333;
  letter-spacing: 0;
}

.item {
  display: inline-block;
  vertical-align: middle;
  margin-right: 30px;
}

.scoreList {
  padding-top: 20px;
  width: 90%;
  margin-left: auto;
  margin-right: auto;
}

.content span {
  font-size: 24px;
  line-height: 50px;
  color: #333;
  letter-spacing: 0;
  font-weight: 500;
  display: inline-block;
  vertical-align: middle;
}

.content {
  float: top;
  line-height: 30px;
  width: 90%;
  font-size: 0;
  padding-bottom: 4px;
  border-bottom: 2px solid #d4d4d4;
  margin-left: auto;
  margin-right: auto;
}

.con {
  display: block;
  position: relative;
  width: 100%;
  height: 100%;
  box-shadow: 5px 4px 5px 5px #959292;
}
</style>


