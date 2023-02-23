<template>
  <el-container>
    <!--    <el-header>-->
    <!--      <div class="header">-->
    <!--&lt;!&ndash;        <div>&ndash;&gt;-->
    <!--&lt;!&ndash;          <el-button class="headerButton" icon="el-icon-arrow-left" circle plain></el-button>&ndash;&gt;-->
    <!--&lt;!&ndash;        </div>&ndash;&gt;-->
    <!--        <div>-->
    <!--          <el-divider direction="vertical"></el-divider>-->
    <!--        </div>-->
    <!--        <div class="headerTitle">-->
    <!--          <h1>{{topic.questionNumber}}</h1>-->
    <!--        </div>-->
    <!--        <div class="dropdown">-->
    <!--          <el-dropdown style="margin-top: 16px" trigger="click">-->
    <!--            <span class="el-dropdown-link">-->
    <!--              题目列表<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
    <!--            </span>-->
    <!--            <el-dropdown-menu slot="dropdown">-->
    <!--              <el-dropdown-item>题目列表</el-dropdown-item>-->
    <!--              <el-dropdown-item>提交列表</el-dropdown-item>-->
    <!--              <el-dropdown-item>排名</el-dropdown-item>-->
    <!--            </el-dropdown-menu>-->
    <!--          </el-dropdown>-->
    <!--        </div>-->
    <!--        <div class="message">-->
    <!--          <el-button class="headerButton" icon="el-icon-message" circle plain></el-button>-->
    <!--        </div>-->
    <!--        <div class="user">-->
    <!--          <el-button class="headerButton" icon="el-icon-user" circle plain></el-button>-->
    <!--        </div>-->
    <!--      </div>-->
    <!--    </el-header>-->
    <el-main>
      <div class="main">
        <h1 class="mainPro">{{ programNum }} {{ programName }}</h1>
        <el-tag type="info">{{ topicList.topicScore + "分" }}</el-tag>
      </div>
      <div class="mainDis">
        <p>
          编程题
        </p>
        <p class="mainDis2">
          作者 小叶子
        </p>
        <p class="mainDis3">
          单位 南昌航空大学
        </p>
      </div>
      <el-divider></el-divider>
      <p>
        {{ topicText }}
      </p>
      <h3>输入格式</h3>
      <p></p>

      <p style="white-space:pre-line">{{ inputFormat }}</p>
      <h3>输出格式</h3>

      <p style="white-space:pre-line">{{ outputFormat }}</p>
      <h3>输入样例</h3>

      <pre><code class="preCode">{{ inputSample }}</code></pre>
      <h3>输出样例</h3>
      <pre><code class="preCode">{{ outputSample }}</code></pre>
      <div>
        <p>代码长度限制</p>
        <code> 16KB</code>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  name: "QuestionInformation",
  props: [
    'id'
  ],
  data() {
    return {
      programNum: '',
      programName: '',
      programContext: '',
      programRight: '',
      programInputFormat: '',
      programOutputFormat: '',
      programInputSample: '',
      programOutputSample: '',
      programScore: '',
      topicList: '',
      optionList: '',
      inputFormat: '',
      outputFormat: '',
      inputSample: '',
      outputSample: '',
      topicText: '',
    }
  },
  computed: {},
  created() {
    /*this.getParams()*/
    this.$store.commit("setCurrentOption");
    let index = this.$store.state.student.currentIndex;
    this.topicList = this.$store.state.student.topicList[index];
    let topic = this.topicList.topicContext.split('@');
    this.topicText = topic[0];
    this.inputFormat = topic[1];
    this.outputFormat = topic[2];
    this.inputSample = topic[3];
    this.outputSample = topic[4];
  },
  methods: {
    getParams() {
      // 取到路由带过来的参数
      this.$http.get("http://localhost:8000/retrunProgram/" + this.id).then(res => {
        console.log(res.data)
        this.programNum = res.data.programNum,
          this.programName = res.data.programName,
          this.programContext = res.data.programContext,
          this.programRight = res.data.programRight,
          this.programInputFormat = res.data.programInputFormat,
          this.programOutputFormat = res.data.programOutputFormat,
          this.programInputSample = res.data.programInputSample,
          this.programOutputSample = res.data.programOutputSample,
          this.programScore = res.data.programScore
      })
    },
  },
}
</script>

<style scoped>
.el-header, .el-footer {
  background-color: #F6F7F9;
  color: #333;
}

.el-header {
  padding: 0;
}

.el-main {
  background-color: #FFFFFF;
  color: #333;
}

/deep/ .el-main {
  overflow: auto;
}

.header {
  position: fixed;
  width: 100%;
  background-color: #F6F7F9;
  color: #333;
  display: flex;
  top: 0;
}

/*.footer{*/
/*  position:absolute;*/
/* width: 100%;*/
/*}*/
.headerTitle {
  width: 150px;
}

h1 {
  white-space: nowrap;
  /* 文字用省略号代替超出的部分 */
  text-overflow: ellipsis;
  overflow: hidden;
}

h3 {
  font-size: 30px;
}

p {
  font-size: 20px;
}

.headerButton {
  font-size: 28px;
  margin-top: 10px;
}

.main {
  display: flex;
}

.pCode {
  color: red;
  background-color: #F6F7F9;
  border: 1px solid rgba(0, 0, 0, 0.06);
  border-radius: 4px;
}

pre {
  padding: 10px;
  background-color: #F6F7F9;
  border: 1px solid rgba(0, 0, 0, 0.06);
  border-radius: 4px;
}

.preCode {

}

.mainDis {
  font-size: 5px;
  color: #999999;
}

.mainDis2 {
}

.mainDis3 {
}

.mainPro {
  width: fit-content;
  margin-top: 0px;
  margin-right: 20px;
}

.fB-left {
  font-size: 20px;
  margin: 20px 0 52px 52px;
}

.fB-right {
  font-size: 20px;
  margin: 20px 0 52px 52px;
}

.fB-center {
  font-size: 20px;
  margin: 20px 0 52px 52px;
}

.el-divider--vertical {
  display: inline-block;
  width: 2px;
  height: 80%;
  margin: 8px 16px;
  vertical-align: middle;
  position: relative;
}

.el-dropdown-link {
  font-size: 22px;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.dropdown {
  margin: 0 20px;
}

.message, .user {
  margin-left: 15px;
}
</style>
