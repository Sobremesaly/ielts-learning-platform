<template>
  <el-container>
    <el-header>
      <class-header>
      </class-header>
    </el-header>
    <el-main>
      <div class="directory">
        <div class="show">
          <p>目录</p>
        </div>
        <div class="listShow">
          <el-tree :data="videoList" :props="defaultProps" @node-click="handleNodeClick" icon-class=""></el-tree>
        </div>
      </div>
      <div class="lesson">
        <div class="lessonCon">
          <div class="lesson_watch">
            <p>计算机的入门</p>
            <hr>
            <div class="lesson_c">
              <video ishivideo="true" autoplay="true" isrotate="true" autoHide="true" controls="controls">
                <source src="../../../../static/mp4/ybymc.mp4" type="video/mp4">
              </video>
            </div>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import ClassHeader from '../ClassHeader'

export default {
  name: "classStudy",
  components: {ClassHeader},
  data() {
    return {
      videoList: [{
        label: '一级 1',
        children: [{
          label: '二级 1-1',
          children: [{
            label: '三级 1-1-1'
          }]
        }]
      }, {
        label: '一级 2',
        children: [{
          label: '二级 2-1',
          children: [{
            label: '三级 2-1-1'
          }]
        }, {
          label: '二级 2-2',
          children: [{
            label: '三级 2-2-1'
          }]
        }]
      }, {
        label: '一级 3',
        children: [{
          label: '二级 3-1',
          children: [{
            label: '三级 3-1-1'
          }]
        }, {
          label: '二级 3-2',
          children: [{
            label: '三级 3-2-1'
          }]
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    }
  },
  mounted() {

  }
}
</script>

<style scoped>

::-webkit-scrollbar {
  width: 10px;
  background-color: skyblue;
}

.el-tree >>> .el-tree-node__expand-icon.expanded {
  -webkit-transform: rotate(0deg);
  transform: rotate(0deg);
}

.el-tree >>> .el-icon-caret-right:before {
  content: "\e6df";
  font-size: 24px;
  padding-top: 10px;
}

.el-tree >>> .el-tree-node__expand-icon.expanded.el-icon-caret-right:before {
  content: "\e6e1";
  font-size: 24px;
}

.el-tree >>> .el-tree-node__content {
  width: 100% !important;
  height: 40px !important;
}

.el-tree >>> .el-tree-node__label {
  font-size: 24px !important;
  color: #333;
  font-weight: 500;
  width: 100px !important;
  height: 30px !important;
}


.show p {
  font-size: 28px;
  color: #333;
  font-weight: 500;
  letter-spacing: .5px;
  text-align: left;
}

.show {
  padding: 8px 20px;
  height: 80px;
  position: relative;
  text-align: center;
}

.listShow {
  position: relative;
  width: 100%;
  height: 100%;
  z-index: 100;
  overflow: auto;
}

.lesson_c video {
  position: absolute;
  width: 100%;
  z-index: 1;
  height: 100%;
  left: 0;
  right: 0;
  top: 0;
  min-height: 400px;
  max-height: 600px;
  overflow: hidden;
}

.lesson_c {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
  z-index: 1;
  overflow: hidden;
  padding: 0;
  margin: 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  transition: opacity .15s ease;
  -moz-transition: opacity .15s ease;
  -webkit-transition: opacity .15s ease;
  -o-transition: opacity .15s ease;
}

.lesson_watch hr {
  width: 99% !important;
  margin-top: 10px;
  border: 1px solid #333;
}

.lesson_watch p {
  font-size: 28px;
  font-weight: 400;
  margin-bottom: 0;
}

.lesson_watch {
  width: 1150px;
  height: 100%;
}

.lessonCon {
  position: relative;
  padding: 0;
  min-width: 1200px;
  height: 100%;
  margin: 0;
}

.lesson {
  padding: 12px 0 0 calc(-256px + 50%);
  height: calc(100vh - 180px);
  position: relative;
  min-width: 1200px;
  margin: auto;
  -webkit-transition: all .3s;
  -o-transition: all .3s;
  transition: all .3s;
}

.el-header {
  height: auto !important;
}

.directory {
  position: absolute;
  padding: 12px 0 0;
  top: 30px;
  left: calc(50% - 750px);
  height: calc(100vh - 180px);
  background: #fff;
  width: 450px;
  border-radius: 4px;
  -webkit-transition: all .5s;
  -o-transition: all .5s;
  transition: all .5s;
  box-shadow: 0 2px 10px 0 rgb(0 0 0 / 30%);
}

.el-main {
  position: relative;
  height: calc(100vh - 94px);
  overflow: hidden;
}

@media screen and (max-width: 1256px) {
  .el-main {
    width: 1800px;
  }
}
</style>
