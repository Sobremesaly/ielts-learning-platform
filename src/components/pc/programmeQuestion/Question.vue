<template>
  <div style="overflow: hidden">
    <el-container>
      <el-main style="width: 100%;overflow: hidden;margin-top: 30px">
        <div class="box" ref="box" style="width: 100%">
          <div class="left">
            <question-information>

            </question-information>
            <!--左侧div内容-->

            <!--            <h2>{{topic.questionNumber}}</h2>-->
            <!--            <div style="background-color: #E9EEF3;width: 95%;margin-left: 2%;height: 5%;text-align: center">-->
            <!--              <h4 style="float: left">题目：{{topic.topic}}</h4>-->
            <!--              <h4 style="float: right">分数：{{topic.scores}}分</h4>-->
            <!--            </div>-->
            <router-view :id="id"></router-view>
          </div>
          <div class="resize" title="收缩侧边栏">
            ：
          </div>
          <div class="mid">
            <!--右侧div内容-->

            <el-select v-model="value">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled">
              </el-option>
            </el-select>
            <div style="height: 80%;overflow:hidden">
              <codemirror
                ref="newCm"
                v-model="code"
                :options="cmOptions"
                @ready="onCmReady">
              </codemirror>
            </div>

            <div class="footer">
              <el-button class="fB-left" type="text" icon="el-icon-arrow-left">上一题</el-button>
              <el-button class="fB-center" type="primary" @click="submitAnswer"
                         :disabled="$store.state.student.topicSituation[$store.state.student.currentIndex] != null">
                提交本题作答
              </el-button>
              <el-button class="fB-right" type="text">下一题<i class="el-icon-arrow-right "></i></el-button>
              <el-button class="Test" @click="drawer = true" style="float: right" type="info" plain>测试用例</el-button>

              <el-drawer :visible.sync="drawer" :direction="direction" :with-header="false" size="50%"
                         style="float:bottom;position:absolute">
                <h3>用例输入：</h3>

                <codemirror
                  :options="cmOptions"
                  @ready="onCmReady"
                  v-model="args">
                </codemirror>

                <h3>运行结果：</h3>

                <codemirror
                  :options="cmOptions"
                  @ready="onCmReady"
                  v-model="ans">
                </codemirror>
                <div>
                  <div class="main-submit">
                    <el-button type="text" @click="Testcompilerclear">重置测试用例</el-button>
                    <el-button type="success" @click="Testcompiler">运行测试</el-button>
                  </div>
                </div>
              </el-drawer>
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import 'codemirror/addon/hint/show-hint.css';

//引入插件
import 'codemirror/mode/vue/vue.js'
import 'codemirror/mode/javascript/javascript.js'
import 'codemirror/mode/css/css.js'

//代码补全提示
import 'codemirror/addon/hint/css-hint.js';
import 'codemirror/addon/hint/html-hint.js';
import 'codemirror/addon/hint/javascript-hint.js';
import 'codemirror/addon/hint/show-hint.js';
//主题样式
import 'codemirror/theme/base16-light.css'

import 'codemirror/addon/selection/active-line.js'
import 'codemirror/addon/edit/closebrackets.js'

import 'codemirror/lib/codemirror.css'
//语法高亮
import 'codemirror/mode/clike/clike.js'

import QuestionInformation from './QuestionImformation'
import {codemirror} from "vue-codemirror/src";
import ClassHeader from "../ClassHeader";
import qs from "qs";

export default {
  components: {ClassHeader, QuestionInformation, codemirror},
  name: "question",
  data() {
    return {
      id: '',
      topicList: '',
      options: [{
        value: 'C(gcc)',
        label: 'C(gcc)'
      }, {
        value: 'java(javac)',
        label: 'java(javac)',
      }],
      value: 'C(gcc)',
      textarea: '',
      drawer: false,
      direction: 'btt',
      code: "",
      args: "",
      ans: "",
      cmOptions: {
        tabSize: 4, // tabsize默认为4
        styleActiveLine: true,
        lineNumbers: true,
        line: true,
        mode: 'text/x-java', // 选择代码语言，我这里选的sql
        extraKeys: {"Ctrl": "autocomplete"},   //自动提示配置
        lineWrapping: true,    // 自动换行
        theme: 'base16-light' // 主题根据需要自行配置
      }
    }


  },
  mounted() {
    this.Swipe()
  },
  created() {
    this.getParams()
    this.topicList = this.$store.state.student.topicList[this.$store.state.student.currentIndex];
  },
  methods: {
    async submitAnswer() {
      let answer = {
        examNum: this.$store.state.student.examNum,
        topicNum: this.$store.state.student.topicList[this.$store.state.student.currentIndex].topicNum,
        answer: this.code,
        type: this.value,
      }
      let {data: res} = await this.$http.post("verifyProgramme", qs.stringify(answer), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      /*提交一下做题情况*/
      this.$store.commit("setTopicSituation", 'special');
      this.$store.commit("addCurrentScore", Number(this.topicList.topicScore) * Number(res.accuracy))
      this.$store.commit("setCurrentScore");
      this.$myBus.$emit("scoreChange");
    },
    async Testcompiler() {
      await this.$http.post("http://localhost:8090/CompilerRes", {
        "code": this.code,
        "args": this.args,
        "type": this.value
      })
        .then(res => {
          // 结果放入测试结果中
          this.ans = String(res.data)
        })
    },
    Testcompilerclear() {
      this.args = "";
      this.ans = ""
    },

    onCmReady(cm) {
      // 设置代码编辑框宽和高
      this.$refs.newCm.codemirror.setSize("100%", "700px")
    },

    // getParam(){
    //   console.log(this.$router)
    // },

    getParams() {
      // 取到路由带过来的参数
      this.id = this.$route.query.id

    },


    Swipe: function () {
      var resize = document.getElementsByClassName('resize');
      var left = document.getElementsByClassName('left');
      var mid = document.getElementsByClassName('mid');
      var box = document.getElementsByClassName('box');
      for (let i = 0; i < resize.length; i++) {
        // 鼠标按下事件
        resize[i].onmousedown = function (e) {
          //颜色改变提醒
          resize[i].style.background = '#818181';
          var startX = e.clientX;
          resize[i].left = resize[i].offsetLeft;
          // 鼠标拖动事件
          document.onmousemove = function (e) {
            var endX = e.clientX;
            var moveLen = resize[i].left + (endX - startX); // （endx-startx）=移动的距离。resize[i].left+移动的距离=左边区域最后的宽度
            var maxT = box[i].clientWidth - resize[i].offsetWidth; // 容器宽度 - 左边区域的宽度 = 右边区域的宽度

            if (moveLen < 500) moveLen = 500; // 左边区域的最小宽度为500px
            if (moveLen > maxT - 600) moveLen = maxT - 600; //右边区域最小宽度为600px

            resize[i].style.left = moveLen; // 设置左侧区域的宽度

            for (let j = 0; j < left.length; j++) {
              left[j].style.width = moveLen + 'px';
              mid[j].style.width = (box[i].clientWidth - moveLen - 20) + 'px';
            }
          };
          // 鼠标松开事件
          document.onmouseup = function (evt) {
            //颜色恢复
            resize[i].style.background = '#d6d6d6';
            document.onmousemove = null;
            document.onmouseup = null;
            resize[i].releaseCapture && resize[i].releaseCapture(); //当你不在需要继续获得鼠标消息就要应该调用ReleaseCapture()释放掉
          };
          resize[i].setCapture && resize[i].setCapture(); //该函数在属于当前线程的指定窗口里设置鼠标捕获
          return false;
        };
      }
    }
  },
  // watch: {
  //   // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
  //   '$route': 'getParams'
  // }
}


</script>

<style scoped>
.main-submit {
  float: right;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  width: 100%;
  height: 40%;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  width: 130%;
  height: 90vh;
}

.box {
  width: 100%;
  height: 100%;
  margin: 1% 0px;
  overflow: hidden;
  box-shadow: -1px 9px 10px 3px rgba(0, 0, 0, 0.11);
}

/*左侧div样式*/
.left {
  overflow: auto;
  width: calc(32% - 10px); /*左侧初始化宽度*/
  height: 100%;
  background: #FFFFFF;
  float: left;
}

/*拖拽区div样式*/
.resize {
  cursor: col-resize;
  float: left;
  position: relative;
  top: 45%;
  background-color: #d6d6d6;
  border-radius: 5px;
  margin-top: -10px;
  width: 10px;
  height: 50px;
  background-size: cover;
  background-position: center;
  /*z-index: 99999;*/
  font-size: 32px;
  color: white;
}

/*拖拽区鼠标悬停样式*/
.resize:hover {
  color: #444444;
}

/*右侧div'样式*/
.mid {
  float: left;
  width: 68%; /*右侧初始化宽度*/
  height: 100%;
  background: #fff;
  box-shadow: -1px 4px 5px 3px rgba(0, 0, 0, 0.11);
}
</style>
