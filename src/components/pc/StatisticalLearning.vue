<template>
  <el-container>
    <el-header>
      <class-header>

      </class-header>
    </el-header>
    <el-main>
      <div class="leftInfo">
        <p class="leftInfo_title">
          总成绩
        </p>
        <div class="shareImg">
          <div class="first_model" id="first_model">
          </div>
          <div class="process_line">
            <span class="line">
              <i style="width: 50%"></i>
            </span>

            <div class="depart">
              <div class="D">
                <span>D</span>
                <i></i>
              </div>
              <div class="C">
                <span>C</span>
                <i></i>
              </div>
              <div class="B">
                <span>B</span>
                <i></i>
              </div>

              <div class="A">
                <span>A</span>
                <i></i>
              </div>
              <div class="S">
                <span>S</span>
                <i></i>
              </div>
            </div>
          </div>
        </div>
        <div class="tip">
          距离下个等级还需要10分，继续加油～
        </div>
      </div>
      <div class="rightInfo">
        <p class="t1">成绩明细
          <span>(满分100分)</span>
        </p>
        <div class="d_con">
          <div class="list">
            <div class="title">
              <span class="t3">
                作业
                <span class="t4">
                  20
                  <span class="t5">
                    分(占比20%)
                  </span>
                </span>
              </span>
              <div class="fr">
                <div class="process">
                  完成度
                  <span>
                    10
                  </span>
                  %
                </div>
                <div class="score">
                  得分:
                  <span>
                    1.26
                  </span>
                </div>
                <i class="el-icon-arrow-down"></i>
              </div>
            </div>
            <div class="detail">
              <div class="homeWork">
                <i class="el-icon-tickets"></i>
                <span class="workName">
                  第一次作业
                </span>
                <div class="p_process">
                  <span>
                    进度
                  </span>
                  <p>
                    25%
                  </p>
                </div>
                <div class="p_score">
                  <span>
                    0.267
                  </span>
                  /
                  <p>
                    0.704分
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="list">
            <div class="title">
              <span class="t3">
                平时分
                <span class="t4">
                  20
                  <span class="t5">
                    分(占比20%)
                  </span>
                </span>
              </span>
              <div class="fr">
                <div class="process">
                  完成度
                  <span>
                    0
                  </span>
                  %
                </div>
                <div class="score">
                  得分:
                  <span>
                    0
                  </span>
                </div>
                <i class="el-icon-arrow-down"></i>
              </div>
            </div>
          </div>
          <div class="list">
            <div class="title">
              <span class="t3">
                实验
                <span class="t4">
                  30
                  <span class="t5">
                    分(占比30%)
                  </span>
                </span>
              </span>
              <div class="fr">
                <div class="process">
                  完成度
                  <span>
                    20
                  </span>
                  %
                </div>
                <div class="score">
                  得分:
                  <span>
                    6
                  </span>
                </div>
                <i class="el-icon-arrow-down"></i>
              </div>
            </div>
          </div>
          <div class="list">
            <div class="title">
              <span class="t3">
                考试
                <span class="t4">
                  30
                  <span class="t5">
                    分(占比30%)
                  </span>
                </span>
              </span>
              <div class="fr">
                <div class="process">
                  完成度
                  <span>
                    10
                  </span>
                  %
                </div>
                <div class="score">
                  得分:
                  <span>
                    3
                  </span>
                </div>
                <i class="el-icon-arrow-down"></i>
              </div>
            </div>
          </div>
        </div>
      </div>

    </el-main>
  </el-container>
</template>

<script>
import HoverAvatar from "./HoverAvatar";
import ClassHeader from "./ClassHeader";
import * as echarts from "echarts";

export default {
  name: "StatisticalLearning",
  components: {ClassHeader, HoverAvatar},
  mounted() {
    this.drawFirst();
  },
  methods: {
    drawFirst() {
      let myChart = echarts.init(document.getElementById("first_model"));
      let option = {
        tooltip: {
          formatter: '{a} <br/>{b} : {c}%'
        },
        series: [
          {
            name: 'Pressure',
            type: 'gauge',
            progress: {
              show: true
            },
            detail: {
              valueAnimation: true,
              formatter: '{value}'
            },
            data: [
              {
                value: 50,
                name: 'SCORE'
              }
            ]
          }
        ]
      };
      myChart.setOption(option);
    }
  }
}


</script>

<style scoped>
.p_score p {
  display: inline-block;
  font-weight: 300;
  font-size: 24px;
  color: #666;
  margin: 0;
}

.p_score span {

  font-weight: 400;
  font-size: 16px;
  color: #666;
  margin: 0;
}

.p_score {
  width: 220px;
}

.p_process span {
  display: inline-block;
  font-weight: 350;
  font-size: 20px;
  color: #3d3c3c;
}

.p_process p {
  display: inline-block;
  font-weight: 350;
  font-size: 28px;
  color: #666;
  margin: 0;
}

.homeWork:hover .p_process p {
  color: #4e8ee2;
}

.homeWork:hover .p_process span {
  color: #4e8ee2;
}

.homeWork:hover {
  color: #4e8ee2;
}

.homeWork:hover .workName {
  color: #4e8ee2;
}

.p_process {
  width: 380px;
  margin-left: auto;
  text-align: center;
}

.workName {
  font-weight: 400;
  font-size: 20px;
  color: #333;
  padding-bottom: 2px;
  line-height: 36px;
}

.homeWork {
  display: flex;
  cursor: pointer;
  line-height: 20px;
  font-size: 30px;
  padding-bottom: 20px;
}

.detail {
  padding-top: 25px;
  padding-bottom: 25px;
  border-bottom: 1px solid #ebebeb;
}

.fr i {
  font-size: 28px;
}

.score span {
  font-weight: 300;
  font-size: 28px;
}

.score {
  display: inline-block;
  margin-right: 30px;
}

.process span {
  letter-spacing: 0.5px;
  display: inline-block;
  font-weight: 600;
  font-size: 20px;
}

.process {
  display: inline-block;
  margin-left: 5px;
  width: 160px;
}

.fr {
  float: right;
}

.t5 {
  font-weight: 400;
  font-size: 16px;
  color: #333;
}

.t3 {
  font-weight: 500;
  font-size: 24px;
  color: #333;
}

.title {
  cursor: pointer;
  border-bottom: 1px solid #ebebeb;
}

.list {
  line-height: 56px;
  padding-top: 25px;
  padding-bottom: 25px;
}

.d_con {
  box-shadow: 0 2px 10px 0 rgb(0 0 0 / 10%);
  padding: 0 16px;
  border-radius: 4px;
}

.t1 span {
  font-weight: 400;
  font-size: 18px;
  color: #4c4b4b;
}

.t1 {
  font-size: 30px;
  color: #333;
  line-height: 68px;
  height: 68px;
  font-weight: 400;
}

.rightInfo {
  width: 960px;
  margin-right: 62px;
}

.tip {
  display: block;
  background: #edf5ff;
  width: 100%;
  height: 50px;
  line-height: 50px;
  margin: 100px auto 0;
  text-align: center;
  font-size: 24px;
  color: #1769fe;
  border-radius: 16px;
}

.S span {
  padding-left: 20%;
  padding-top: 5px;
}

.S i {
  width: 8px;
  height: 8px;
  display: block;
  float: right;
  margin-bottom: 1px;
  border-radius: 50%;
  background: #114bfb;
}

.S {
  width: 10%;
}

.A span {
  padding-left: 20%;
  padding-top: 5px;
}

.A i {
  width: 8px;
  height: 8px;
  display: block;
  float: right;
  margin-bottom: 1px;
  border-radius: 50%;
  background: #119dfb;
}

.A {
  width: 10%;
}

.B span {
  padding-left: 20%;
  padding-top: 5px;
}

.B i {
  width: 8px;
  height: 8px;
  display: block;
  float: right;
  margin-bottom: 1px;
  border-radius: 50%;
  background: #119dfb;
}

.B {
  width: 10%;
}

.C span {
  padding-left: 20%;
  padding-top: 5px;
}

.C i {
  width: 8px;
  height: 8px;
  display: block;
  float: right;
  margin-bottom: 1px;
  border-radius: 50%;
  background: #63fb11;
}

.C {
  width: 10%;
}

.D span {
  padding-left: 50%;
  padding-top: 5px;
}

.D i {
  width: 8px;
  height: 8px;
  display: block;
  float: right;
  margin-bottom: 1px;
  border-radius: 50%;
  background: #fb1111;
}

.D {
  width: 60%;
}

.depart {
  display: flex;
  position: absolute;
  z-index: 2;
  width: 480px;
  top: 80px;
  left: 28px;
}

.first i {
  width: 8px;
  height: 8px;
  display: block;
  border-radius: 50%;
  margin: 10px auto 14px;
  background: #999;
}

.line i {
  display: block;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background: -webkit-gradient(linear, left top, right top, from(#fa4949), to(#ff9243));
  background: -o-linear-gradient(left, #fa4949, #ff9243);
  background: linear-gradient(90deg, #fa4949, #ff9243);
}

.line {
  background: #ebebeb;
  height: 6px;
  display: block;
  position: absolute;
  z-index: 2;
  width: 480px;
  top: 80px;
  left: 28px;
}

.process_line {
  position: relative;
  background-color: white;
  padding: 34px 13px 10px;
}

.first_model {
  width: 600px;
  height: 500px;
  background-color: rgb(246, 252, 254);
}

.shareImg {
  position: relative;
  -webkit-box-shadow: 0 2px 10px 0 rgb(0 0 0 / 10%);
  box-shadow: 3 3px 10px 0 rgb(0 0 0 / 10%);
  border-radius: 4px;
}

.leftInfo_title {
  font-size: 30px;
  color: #333;
  line-height: 92px;
  height: 92px;
  font-weight: 400;
  margin-bottom: 0 !important;
}

.leftInfo {
  width: 585px;
  margin-right: 62px;
}

.el-main {
  display: flex;
  padding: 0;
  margin-left: auto;
  margin-right: auto;
  margin-top: 2%;
}

@media screen and (min-width: 1600px) {
  .el-main {
    width: 1600px !important;
    margin-left: auto;
    margin-right: auto !important;
  }
}
</style>
