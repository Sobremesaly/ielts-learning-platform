<template>
  <div>
    <div class="C_header">
      <div class="headerCont">
        <img src="../../assets/myLogo.png">
        <p class="className">
          <i></i>
          <span class="C_name">
            计算机网络
          </span>
        </p>
        <div class="tableList">
          <p class="C_active" @click="goClassStudy">
            章节
          </p>
          <p class="C_active" @click="goDiscuss">
            讨论
          </p>
          <p class="C_active" @click="goHomework">
            作业
          </p>
          <p class="C_active" @click="goExam">
            考试
          </p>
          <p class="C_active" @click="goTest">
            实验
          </p>
          <p class="C_pro">
            资料
          </p>
          <p class="C_pro" @click="goStatistic">
            学习记录
          </p>
        </div>
        <div class="C_avatar">
          <el-dropdown split-button type="primary" class="drop">
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item class="dropItem">
                <hover-avatar>
                </hover-avatar>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <img src="../../assets/dear_png.png">
          <span></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HoverAvatar from './HoverAvatar'

export default {
  name: "ClassHeader",
  components: {HoverAvatar},
  methods: {
    goExam() {
      this.$router.push("/myExamination")
    },
    goHomework() {
      this.$router.push("/myHomework")
    },
    goStatistic() {
      this.$router.push("/statisticalLearning")
    },
    goClassStudy() {
      this.$router.push("/classStudy")
    },
    goDiscuss() {
      this.$router.push("/classDiscuss")
    },
    goTest() {
      this.$router.push("/myTest")
    }
  }
}
</script>

<style scoped>
.dropItem:hover {
  background-color: white;
}

.drop >>> .el-dropdown__caret-button {
  margin-right: 10px !important;
  height: 76px;
  width: 76px;
  border-radius: 50%;
}

.drop >>> .el-button-group button:nth-child(1) {
  display: none;
}

.drop {
  opacity: 0;
  position: absolute;
  z-index: 10;
}

.C_avatar span {
  position: absolute;
  background: url("../../assets/head-bg.png") no-repeat;
  background-size: contain;
  top: 3px;
  right: 3px;
  width: 100%;
  height: 100%;
  z-index: 1 !important;
}

.C_avatar img {
  display: block;
  width: 80% !important;
  height: 80% !important;
  border-radius: 50%;
  z-index: 3 !important;
  margin-top: 6.2px;
  position: relative;
  overflow: hidden;
}

.C_avatar {
  vertical-align: middle;
  display: inline-block;
  position: relative;
  height: 76px;
  width: 76px;
  cursor: pointer;
}

.C_pro {
  opacity: .6;
  font-weight: 500;
  font-size: 20px;
  color: hsla(0, 0%, 100%, .89);
  letter-spacing: 0;
  margin: 0 12px;
  display: inline-block;
  vertical-align: middle;
  cursor: pointer;
}

.C_active {
  opacity: 1;
  font-size: 20px;
  color: #fff;
  letter-spacing: .56px;
}

.tableList p {
  font-weight: 450;
  margin: 0 12px;
  display: inline-block;
  vertical-align: middle;
  cursor: pointer;
}

.tableList {
  display: inline-block;
  vertical-align: middle;
  line-height: 88px;
  flex: 1;
}

.className i {
  display: inline-block;
  vertical-align: middle;
  width: 3px;
  height: 66px;
  background: hsla(0, 0%, 100%, .44);
  margin: 0 20px;
}

.C_name {
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
  margin: 0 20px 0 0;
  font-size: 26px;
  color: #fff;
  letter-spacing: .5px;
  font-weight: 500;
  line-height: 88px;
  display: inline-block;
  vertical-align: middle;
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
  text-decoration: none;
}

.className {
  -webkit-box-flex: 1;
  width: 50%;
  -ms-flex: 1;
  flex: 1;
  margin: 0 20px 0 0;
  font-size: 16px;
  color: #fff;
  letter-spacing: 0.5px;
  font-weight: 500;
  line-height: 125px;
  display: inline-block;
  vertical-align: middle;
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
  text-decoration: none;
}

.C_header img {
  height: 100px;
  width: auto;
  align-items: center;
}

.C_header {
  display: block;
  width: 100%;
  border-bottom: 1px solid #eee;
  height: 125px;
  position: relative;
  background-image: linear-gradient(90deg, #4e8ee2, #7476ec);
}

.headerCont {
  display: flex;
  width: 1800px;
  height: 100%;
  margin-left: auto;
  margin-right: auto;
  align-items: center;
  background-image: linear-gradient(90deg, #4e8ee2, #7476ec);
}
</style>
