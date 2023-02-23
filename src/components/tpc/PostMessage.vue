<template>
  <div class="con">
    <div class="postTitle">
      <input placeholder="标题" v-model="messageTitle"></input>
    </div>
    <div class="context">
      <textarea placeholder="内容" v-model="messageContext"></textarea>
    </div>
    <div class="postTime">
      <span>
        <i class="el-icon-timer"></i>
        {{ nowData }}
      </span>
    </div>
  </div>
</template>

<script>
import {dateFormat, getNowFormatDate} from "../../share/timeFormate";
import qs from "qs";

export default {
  name: "PostMessage",
  mounted(){
    this.getTime();
  },

  created() {
    this.$myBus.$on('postMessage', () => {
      // 函数体内即为代码逻辑
      this.teacherPostMessage();
    })
  },
  data(){
    return{
      nowData: '',
      messageTitle: '',
      messageContext: '',
    }
  },
  methods:{
    teacherPostMessage(){
      let message = {
        courseNum : this.$store.state.teacher.courseNum,
        teacherId : localStorage.getItem("userId"),
        timeNow : getNowFormatDate(),
        messageTitle : this.messageTitle,
        messageContext : this.messageContext,
      }
      let {data:res} = this.$http.post("teacherPostMessage", qs.stringify(message), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      if(res.flag === 'right'){
        this.$message.success("发送通知成功!");
        return
      }
      this.$message.error("发送失败!");
    },
    getTime(){
      let now = new Date();
      let year = now.getFullYear(); //得到年份
      let month = now.getMonth(); //得到月份
      let date = now.getDate(); //得到日期
      this.nowData = year + "-" + month + "-" + date;
    }
  },
  beforeDestroy(){
    this.$myBus.off('postMessage')
  },
}
</script>

<style scoped>
.postTime span{
  display: block;
  float: left;
  padding-top: 8px;
}
.context textarea {
  width: 100%;
  height: 150px;
  border-radius: 0;
  outline: none;
  border: 1px slategray solid;
}
.context {
  display: block;
  margin-top: 20px;
  padding-left: 0;
}
.postTitle input {
  float: left;
  border: none;
  position: static;
  border-bottom: 2px solid #707684;
  width: 250px;
  font-size: 20px;
  color: #4c4b4b;
  outline: none;
}
.postTitle {
  padding-left: 20%;
  padding-top: 30px;
  height: 20%;
  margin-bottom: 0;
}
.con {
  width: 100%;
  height: 100%;
}

</style>
