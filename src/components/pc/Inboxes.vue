<template>
  <el-container>
    <main-header class="b">
    </main-header>
    <el-main>
      <div class="email">
        <div class="clearfix">
          <div class="leftInfo">
            <div class="l_title">
              <p>
                <i></i>
                收信箱
              </p>
            </div>
            <div class="listCon" tabindex="0" @click="clickEvent($event)" v-for="item in messageList">
              <div class="titleCon">
                <p>
                  {{ item.messageTitle }}
                </p>
              </div>
              <div class="con">
                <p>
                 <span>
                   {{ item.messageContext }}
                 </span>
                </p>
              </div>
              <div class="other">
                <i class="el-icon-timer"></i>
                <span class="b_time">{{ item.messageTime | formatDateMore }}</span>
                <i class="el-icon-user"></i>
                <span class="b_user">{{ item.teacherName }}</span>
              </div>
            </div>
          </div>
          <div class="rightInfo">
            <div class="detail">
              <p class="detail_p">
                {{ title }}
              </p>
              <div class="other">
                <i class="el-icon-timer"></i>
                <span class="b_time">{{ time }}</span>
                <i class="el-icon-user"></i>
                <span class="b_user">{{ name }}</span>
              </div>
              <div class="foot">
                <div class="footTop">
                  <p>
                    {{ context }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import MainHeader from './MainHeader'
import qs from "qs";

export default {
  name: "Inboxes",
  components: {MainHeader},
  data() {
    return {
      text: "Inbox",
      title: '',
      time: '',
      name: '',
      context: '',
      messageList: '',
    }
  },
  mounted() {
    this.getStudentMessage();
  },
  methods: {
    clickEvent(e) {
      this.title = e.currentTarget.getElementsByTagName('p')[0].innerText;
      this.time = e.currentTarget.getElementsByClassName('b_time')[0].innerText;
      this.name = e.currentTarget.getElementsByClassName('b_user')[0].innerText;
      this.context = e.currentTarget.getElementsByTagName('p')[1].innerText;
    },
    async getStudentMessage() {
      let user = {
        studentId: localStorage.getItem("userId"),
      }
      let {data: res} = await this.$http.post("getStudentMessage", qs.stringify(user), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      if (res.flag === 'right') {
        this.$message.success("收信箱更新了!");
        this.messageList = res.list;
        return
      }
      this.$message.error("获取信息失败!");
    },
  }
}
</script>
<style scoped>
.footTop p {
  padding-left: 20px;
}

.footTop {
  font-family: sans-serif;
  font-size: 24px;
  line-height: normal;
}

.foot {
  font-size: 20px;
  color: #666;
  letter-spacing: 0;
  line-height: 22px;
  margin: 20px 0;
}

.detail_p {
  font-weight: 500;
  font-size: 30px;
  color: #2c2c2c;
  overflow: hidden;
  white-space: nowrap;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  line-height: 38px;
  margin-bottom: 20px;
  margin-top: 0;
}

.detail {
  width: 890px;
  margin-left: 48px;
}

.rightInfo {
  height: 100%;
  overflow: auto;
}

.listCon:focus p {
  color: #4e8ee2;
}

.listCon:focus {
  background: rgba(65, 130, 250, .07);
}

.listCon:hover {
  background-color: #f9f9f9;
}

div::-webkit-scrollbar { /*滚动条整体样式*/
  width: 5px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 5px;
}

div::-webkit-scrollbar-thumb { /*滚动条里面小方块*/
  border-radius: 5px;
  background: rgba(0, 0, 0, .075);
}

div::-webkit-scrollbar-track { /*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px #fafafa;
  border-radius: 0;
  background: #ececec;
}

.b_user {
  width: 120px;
  overflow: auto;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 20px;
}

.other i {
  font-size: 22px;
  margin-right: 8px;
}

.b_time {
  margin-right: 18px;
  font-size: 20px;
}

.other {
  padding: 0 20px;
  line-height: 28px;
  font-size: 0;
  color: #666;
}

.con span {
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}

.con p {
  display: inline-block;
  vertical-align: top;
  line-height: 28px;
  font-size: 20px;
  color: #666;
  width: 100%;
  min-height: 20px;
  max-height: 80px;
  overflow: hidden;
  position: relative;
  padding: 0 22px;
}

.titleCon p {
  font-weight: 500;
  font-size: 26px;
  color: #333;
  overflow: hidden;
  white-space: nowrap;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  line-height: 32px;
  margin-bottom: 20px;
}

.titleCon {
  position: relative;
  padding: 0 16px;
}

.listCon {
  cursor: pointer;
  margin: 0 auto;
  padding: 20px 0 24px;
  border-bottom: 1px solid #ebebeb;
}

.l_title i {
  display: inline-block;
  vertical-align: top;
  margin-right: 8px;
  margin-top: 2px;
  width: 8px;
  height: 30px;
  border-radius: 3px;
  background-color: #1769fe;
}

.l_title p {
  font-weight: 500;
  font-size: 24px;
  color: #333;
  margin-bottom: 0;
  padding-bottom: 0;
}

.l_title {
  border-bottom: 1px solid #ebebeb;
  padding: 20px 0 20px 16px;
  line-height: 35px;
}

.leftInfo {
  width: 480px;
  margin-right: 70px;
  height: calc(100% - 20px);
  overflow: auto;
  -webkit-box-shadow: 0 2px 10px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 10px 0 rgb(0 0 0 / 10%);
  position: absolute;
  top: 100px;
  left: calc(50% - 800px);
}

.clearfix {
  margin: 0 auto;
  position: relative;
  height: 80%;
  padding: 120px 0 0 calc(-280px + 50%);
  zoom: 1;
}

.email {
  padding: 0;
  font-weight: 400;
  height: calc(90vh - 10px);
  overflow: auto;
  min-width: 1600px;
}
</style>


