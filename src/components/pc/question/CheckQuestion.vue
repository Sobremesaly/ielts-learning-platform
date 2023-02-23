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
          {{ totalScore }}
        </span>
        <span>
          分
        </span>
      </div>
      <div class="item">
        <span>
          得分
        </span>
        <span>
          {{ currentScore }}
        </span>
        <span>
          分
        </span>
      </div>
      <div class="item">
        <span>
          未完成
        </span>
        <span>
          {{ $store.state.student.topicList.length - $store.state.student.finishNum }}
        </span>
        <span>
          个
        </span>
      </div>
    </div>
    <div class="answerList">
      <div style="display:inline-block" v-for="(item,index) in topicList">
        <div class="result" :id="index" v-if="topicSituation[index] === 'false'">
          <span class="singleOne">
          {{ index + 1 }}
        </span>
          <i class="singleResultWrong"></i>
        </div>
        <div class="result" :id="index" v-else-if="topicSituation[index] === 'right'">
          <span class="singleOne">
            {{ index + 1 }}
          </span>
          <i class="singleResultRight"></i>
        </div>
        <div class="result" :id="index" v-else-if="topicSituation[index] === 'special'">
          <span class="singleOne">
            {{ index + 1 }}
          </span>
          <i class="singleResultRight"></i>
        </div>
        <div class="result" :id="index" v-else>
        <span class="singleOne">
          {{ index + 1 }}
        </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CheckQuestion",
  methods: {},
  data() {
    return {
      topicList: this.$store.state.student.topicList,
      topicSituation: [],
      totalScore: 0,
      currentScore: 0,
    }
  },
  created() {
    this.$store.commit("setStudentTotalScore")
    this.$store.commit("resetCurrentScore")
    this.$store.commit("resetTopicSituation")
    this.totalScore = this.$store.state.student.studentTotalScore;
  },
  mounted() {
    this.$myBus.$on("scoreChange", () => {
      this.currentScore = this.$store.state.student.currentScore;
      this.topicSituation = this.$store.state.student.topicSituation;
    })
  }
}
</script>

<style scoped>
.singleNoResult {
  display: block;
  width: 60px;
  height: 60px;
  background: rgba(222, 230, 247, 0.1);
  line-height: 60px;
  text-align: center;
  font-size: 28px;
  color: #1b4179;
  margin-right: auto;
  margin-left: auto;
  border: 1px solid #8acdcf;
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

.singleResultWrong {
  display: block;
  width: 25px;
  height: 25px;
  background: url(../../../assets/wrong.png) no-repeat;
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

.singleOne {
  display: block;
  width: 60px;
  height: 60px;
  background: rgba(77, 161, 255, .1);
  line-height: 60px;
  text-align: center;
  font-size: 28px;
  color: #1b4179;
  margin-right: auto;
  margin-left: auto;
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
  margin-right: 70px;
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
  margin-left: 300px;
  position: relative;
  width: 100%;
  height: 100%;
  box-shadow: 5px 4px 5px 5px #959292;
}
</style>
