<template>
  <el-container>
    <el-header>
      <teacher-class-header>

      </teacher-class-header>
    </el-header>
    <el-main>
      <div>
        <div class="myModel">
          <div id="first_model" class="first_model">
          </div>
        </div>
        <div class="anModel">
          <div id="second_model" class="second_model">
          </div>
        </div>
      </div>
    </el-main>
  </el-container>

</template>

<script>
import * as echarts from "echarts";
import TeacherClassHeader from "./share/TeacherClassHeader";
export default {
  name: "LearningSituation",
  components: {TeacherClassHeader},
  mounted() {
    this.drawModel();
    this.drawDetails();
  },
  methods:{
    drawModel(){
      let myChart = echarts.init(document.getElementById("first_model"));
      let option = {
        title: {
          text: '学生成绩',
          subtext: '统计饼图',
          left: 'left'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'left',
          top: 'bottom',
          data: [
            'rose1',
            'rose2',
            'rose3',
            'rose4',
            'rose5',
            'rose6',
            'rose7',
            'rose8'
          ]
        },
        toolbox: {
          show: true,

          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true }
          }
        },
        series: [

          {
            name: 'Area Mode',
            type: 'pie',
            radius: [20, 140],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 5
            },
            data: [
              { value: 30, name: '60以下' },
              { value: 28, name: '60-65' },
              { value: 26, name: '65-70' },
              { value: 24, name: '70-75' },
              { value: 22, name: '75-80' },
              { value: 20, name: '80-85' },
              { value: 18, name: '85-90' },
              { value: 16, name: '90以上' }
            ]
          }
        ]
      };
      myChart.setOption(option);
    },
    drawDetails(){
      let myChart = echarts.init(document.getElementById("second_model"));
      let anOption = {
        title: {
          text: '成绩明细图'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {},
        toolbox: {
          show: true,
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            dataView: { readOnly: false },
            magicType: { type: ['line', 'bar'] },
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34']
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            formatter: '{value} 分'
          },

        },
        series: [
          {
            name: 'Highest',
            type: 'line',
            data: [50, 55, 98, 71, 12, 12, 9],
            markPoint: {
              data: [
                { type: 'max', name: 'Max' },
                { type: 'min', name: 'Min' }
              ]
            },
            markLine: {
              data: [
                { type: 'average', name: 'Avg' ,lineStyle:{
                    color:  '#999'
                  },
                },
                [
                  {
                    symbol: 'none',
                    x: '90%',
                    yAxis: 'max'
                  },
                  {
                    symbol: 'circle',
                    label: {
                      position: 'start',
                      formatter: 'Max'
                    },
                    type: 'max',
                    name: '最高点'
                  },
                ],
                [
                  {
                    symbol: 'none',
                    x: '90%',
                    yAxis: 'min'
                  },
                  {
                    lineStyle:{
                      color: '#8acdcf'
                    },
                    symbol: 'circle',
                    label: {
                      position: 'start',
                      formatter: 'min'
                    },
                    type: 'min',
                    name: '最高点'
                  },
                ],
                {
                  yAxis: 60,
                  lineStyle:{
                    color: 'red'
                  },
                },
              ]

            }
          },

        ]
      };
      myChart.setOption(anOption);
    }
  }
}
</script>

<style scoped>
.anModel {
  float: right;
  display: block;
  width: 1400px;
  height: 1000px;

}
.el-main {
  margin-top: 40px;
  min-height: 1400px;
  background-color: #c5f7f9;
}
.myModel {
  float: left;
  display: block;
  width: 600px;
  height: 1000px;

}
.first_model {
  height: 100%;
  width: 100%;
}

.second_model{
  height: 100%;
  width: 100%;
}
</style>
