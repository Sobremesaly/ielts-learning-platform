<template>
  <el-container>
    <el-header>
      <teacher-class-header>
      </teacher-class-header>
    </el-header>
    <el-main>
      <div class="classDiscussWrap">
        <div class="discussBox">
          <div class="discussBoxMain">
            <div class="postTopic">
              <div class="areaText">
                <el-input
                  type="text"
                  placeholder="请输入标题内容"
                  v-model="titleText"
                  maxlength="30"
                  show-word-limit>
                </el-input>
                <div class="context">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}"
                    placeholder="请输入内容"
                    v-model="textarea">
                  </el-input>
                </div>
                <div class="postBtn">
                  <el-upload
                    ref="discuss"
                    class="uploadPic"
                    action="http://localhost:8090/postDiscuss"
                    :auto-upload="false"
                    list-type="picture-card"
                    :file-list="picList"
                    :on-preview="handlePictureCardPreview"
                    :data="discuss"
                    :headers="headerObj"
                    :on-success="afterSuccess"
                    :on-remove="handleRemove">
                    <i class="el-icon-picture-outline" slot="trigger">图片</i>
                    <button class="c_post" type="button" @click="postDiscuss">发表评论</button>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                  </el-dialog>
                </div>
              </div>
            </div>
            <div class="discussText">
              <div class="single" v-for="item in discussList">
                <div class="singleBox">
                  <div class="topic">
                    <div class="c_topic">
                      {{ item.discussTitle }}
                    </div>
                  </div>
                  <div class="writtenBox">
                    <div class="written">
                      <p class="writtenBody">
                        {{ item.discussContent }}
                      </p>
                    </div>
                  </div>
                  <div class="viewBox">
                    <div class="singleImg">
                      <div class="singleImgOne">
                        <div class="demo-image__preview" style="height: 100%">
                          <el-image
                            style="width: 100%; height: 100%"
                            :src="item.discussPhoto"
                            :preview-src-list="item.discussPhoto">
                          </el-image>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="judgeNumBox">
                    <div class="names">
                      <el-avatar class="u_avatar"> user</el-avatar>
                      <span class="name">{{ item.userName }}</span>
                      <span class="time">{{ item.discussDate | formatDateMore }}</span>
                    </div>
                    <div :id="item.discussNum" class="reply" @click="showOrNot($event)">
                      <i class="el-icon-chat-round"></i>
                      <span>回复</span>
                    </div>
                  </div>
                  <div :id="item.discussNum + 're'" class="replyShow"
                       style="display: none; border-color: rgb(235, 235, 235);">
                    <div class="textInp">
                      <div class="el-textarea el-input--medium"><textarea autocomplete="off" placeholder="请输入内容"
                                                                          class="el-textarea__inner"
                                                                          :id="item.discussNum + 'ret'"
                                                                          style="resize: none; min-height: 73px; height: 73px;"></textarea>
                      </div>
                    </div>
                    <div class="pushJudgeTopic">
                    </div>
                    <div class="judgeCliP">
                      <button type="button" class="publish" @click="postReply">
                        <span>发布</span>
                      </button>
                    </div>
                  </div>
                </div>
                <div v-for="items in item.reply">
                  <div class="singleJudge">
                    <div class="singleJudgeInfo">
                      <el-avatar class="c_avatar">
                        user
                      </el-avatar>
                      <p class="reviewers">
                        {{ items.userName }}
                      </p>
                    </div>
                  </div>
                  <div class="singleWen">
                    {{ items.replyContent }}
                  </div>
                  <div class="singleT">
                    <div class="c_time">
                      {{ items.replyDate | formatDateMore }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="block">
              <el-pagination
                layout="prev, pager, next"
                :total="50">
              </el-pagination>
            </div>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import TeacherClassHeader from "./share/TeacherClassHeader";
import qs from "qs";

export default {
  name: "TeacherDiscuss",
  components: {TeacherClassHeader},
  data() {
    return {
      titleText: '',
      textarea: '',
      dialogImageUrl: '',
      dialogVisible: false,
      picList: [],
      discuss: {},
      discussList: [],
      currentNum: '',
      headerObj: {
        token: localStorage.getItem("token"),
      },
      srcList: [
        'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
        'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
      ]
    }
  },
  methods: {
    async postReply() {
      let reply = {
        replyContent: document.getElementById(this.currentNum + 'ret').value,
        userId: localStorage.getItem("userId"),
        discussNum: this.currentNum,
        userType: this.$store.state.identifier,
      }
      let {data: res} = await this.$http.post("postReply", qs.stringify(reply), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
      this.discussList = res.discussList;
      this.$message.success("回复成功!");
    },
    afterSuccess(response, file, fileList) {
      file = '';
      fileList = '';
      this.$message.success("发布讨论成功");
      this.titleText = '';
      this.textarea = '';
      this.discussList = response.discussList;
    },
    async getDiscussByCourseNum() {
      let course = {
        courseNum: this.$store.state.teacher.courseNum,
      }
      let {data: res} = await this.$http.post("getDiscussByCourseNum", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
      this.discussList = res.discussList;
    },
    postDiscuss() {
      if (this.$store.state.identifier === 'student') {
        this.discuss = {
          courseNum: this.$store.state.student.courseNum,
          discussTitle: this.titleText,
          discussContent: this.textarea,
          userId: localStorage.getItem('userId'),
          userType: 'student',
        }
      } else {
        this.discuss = {
          courseNum: this.$store.state.teacher.courseNum,
          discussTitle: this.titleText,
          discussContent: this.textarea,
          userId: localStorage.getItem('userId'),
          userType: 'teacher',
        }
      }
      setTimeout(this.$refs.discuss.submit, 1)
    },
    showOrNot(event) {
      this.currentNum = event.currentTarget.id;
      if (document.getElementById(event.currentTarget.id + 're').style.display === 'none') {
        document.getElementById(event.currentTarget.id + 're').style.display = 'block';
      } else {
        document.getElementById(event.currentTarget.id + 're').style.display = 'none';
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  },
  mounted() {
    this.getDiscussByCourseNum();
  }
}
</script>

<style scoped>
.uploadPic >>> .el-upload--picture-card {
  width: 20% !important;
}

.reply {
  cursor: pointer;
  display: inline-block;
  float: right;
  font-size: 20px;
  color: #333;
  margin-right: 15px;
}

.judgeCliP {
  height: 40px;
  margin-top: 5px;
  margin-bottom: 5px;
  padding-top: 3px;
  padding-bottom: 3px;
  border: none;
  border-bottom: 2px solid seashell;
}

.publish {
  cursor: pointer;
  float: right;
  width: 100px;
  height: 40px;
  border-radius: 5px;
  background-color: #1769fe;
  border-color: #1769fe;
  font-size: 14px;
  margin-right: 20px;
  margin-bottom: 16px;
  padding: 0;
  color: #fff;
}

. {
  height: auto;
  margin-top: 5px;
  background: #fff;
  border-radius: 4px;
  border: 1px solid #ebebeb;
  margin-left: 5px;
}

.el-pagination >>> .el-pager li {
  width: 30px;
  height: 30px;
}

.el-pagination >>> .active {
  border-radius: 50%;
  background-color: #409eff;
  color: white !important;
  cursor: default;
}

.el-pagination >>> .number {
  color: #999;
  font-size: 18px;
  margin: 0 8px;
}

.el-pagination {
  white-space: nowrap;
  padding: 2px 5px;
  color: #303133;
  font-weight: 700;
}

.block {
  padding-top: 20px;
  padding-bottom: 20px;
  text-align: center;
  width: 1100px;
}

.c_time {
  padding-left: 55px;
  color: #666;
  font-size: 12px;
}

.singleT {
  height: 50px;
  padding-top: 12px;
}

.singleWen {
  padding-top: 14px;
  padding-bottom: 14px;
  padding-left: 55px;
  font-size: 20px;
  font-weight: 400;
  color: #333;
  line-height: 28px;
  text-align: justify;
}

.c_avatar {
  margin: 0 !important;
}

.reviewers {
  display: inline-block;
  margin-left: 15px;
  margin-top: 9px;
}

.singleJudgeInfo {
  display: flex;
  height: 35px;
}

.singleJudge {
  width: 960px;
  height: auto;
  padding-top: 10px;
  margin-left: 32px;
  padding-bottom: 20px;
  border-top: 1px solid #f3f3f3;
}

.time {
  margin-right: 5px;
  color: #666;
  font-size: 12px;
}

.name {
  margin-right: 10px;
}

.u_avatar {
  margin-right: 10px;
  margin-top: 18px !important;
}

.names {
  float: left;
  display: flex;
  height: 100%;
  width: 50%;
}

.judgeNumBox {
  height: 75px;
  line-height: 75px;
  margin-bottom: 8px;
  box-sizing: border-box;
  outline: none;
}

.replyShow {
  animation: replyShow 1s 1;
}

@keyframes replyShow {
  0% {
    opacity: 0
  }
  100% {
    opacity: 1
  }
}

.singleImgOne {
  width: 210px;
  height: 210px;
  border-radius: 5px;
  overflow: hidden;
  cursor: -webkit-zoom-in;
  cursor: zoom-in;
  border: 1px solid #edf5ff;
}

.singleImg {
  border-radius: 2px;
  overflow: hidden;
  margin-bottom: 10px;
}

.viewBox {
  padding-top: 15px;
}

.writtenBody {
  font-family: sans-serif;
  font-size: 22px;
  line-height: normal;
  margin-top: 0;
  color: #838080;
}

.written {
  font-size: 20px;
  font-weight: 400;
  color: #333;
  line-height: 32px;
}

.writtenBox {
  height: 50px;
  overflow: hidden;
  position: relative;
  -webkit-transition: all 1s;
  -o-transition: all 1s;
  transition: all 1s;
}

.c_topic {
  float: left;
  max-width: 785px;
  height: 40px;
  line-height: 42px;
  font-size: 26px;
  font-weight: 400;
  color: #333;
  overflow: hidden;
}

.topic {
  height: 60px;
  overflow: hidden;
}

.singleBox {
  box-sizing: border-box;
  outline: none;
}

.single {
  padding-top: 30px;
  border-bottom: 1.5px solid #ebebeb;
}

.discussText {
  height: auto;
  width: 1129px;
  position: relative;
  box-sizing: border-box;
  padding: 20px 10px 0 20px;
  overflow: hidden;
}

.uploadPic {
  height: 100px;
}

.c_post {
  display: inline-block;
  width: 120px;
  text-align: center;
  float: right;
  height: 50px;
  padding-top: 0;
  background-color: #1769fe;
  border-color: #1769fe;
  font-size: 20px;
  color: #fff;
  margin-top: 8px;
  border-radius: 6px;
  margin-right: 120px;
  z-index: 10;
  cursor: pointer;
}

.uploadPic >>> .el-upload-list__item {
  width: 100px;
  height: 100px;
}

.uploadPic >>> .el-upload-list--picture-card {
  display: block !important;
}

.uploadPic i {
  display: inline-block;
  width: 20%;
  height: 80%;
  vertical-align: middle;
  font-size: 22px;
  padding-top: 18px;
  border-radius: 30px;
  border: 1px solid #ccc;
  background-color: #c9daf4;
  color: #4182fa;
}

.uploadPic >>> .el-upload-list--picture-card {
  margin: 0;
  display: inline;
  vertical-align: top;
}

.uploadPic >>> .el-upload--picture-card {
  display: flex;
  width: 1000px;
  justify-content: space-between;
  line-height: 0 !important;
  border: none;
  height: 50px;
  position: absolute;
  border-radius: 30px;
}

.postBtn {
  overflow: hidden;
  width: 1100px;
  height: auto;
  min-height: 100px;
}

.el-textarea >>> .el-textarea__inner:focus {
  border: none !important;
}

.el-textarea >>> .el-textarea__inner {
  border: none !important;
  font-size: 22px !important;
  color: #333333;
  max-height: 100px !important;
  min-height: 100px !important;
  background-color: #f6fafc;
}

.el-textarea {
  font-size: 20px;

}

.context {
  width: 1000px;
  height: 120px;
  margin-top: 5px;
  border-top: 1px solid rgba(196, 189, 189, 0.5);
  z-index: 10;
  overflow: hidden;
}

.el-input >>> .el-input__inner::placeholder {
  color: #333;
}

.el-input >>> .el-input__inner {
  background-color: #f6fafc;
  border: none;
  height: 50px;
  color: #333;
}

.el-input {
  font-size: 24px;
  font-weight: 400;
  color: #333;

}

.areaText {
  width: 1000px;
  min-height: 200px;
  height: auto;
  background: #fafafa;
  border-radius: 6px;
  margin-left: auto;
  margin-right: auto;
}

.postTopic {
  width: 1100px;
  height: 360px;
  background: #fff;
  -webkit-box-shadow: 0 2px 10px 0 rgb(0 0 0 / 10%);
  box-shadow: 0 2px 10px 0 rgb(0 0 0 / 10%);
  border-radius: 4px;
  padding: 20px 10px 0 20px;
  overflow: hidden;
}

.discussBoxMain {
  min-height: calc(100vh - 1.2rem);
  width: 100%;
  margin: 0 auto;
  overflow: hidden;
  position: relative;
  padding: 0 0 0 calc(-600px + 50%);
}

.discussBox {
  min-height: calc(100vh - 90px);
  width: 100%;
  margin: 0 auto;
  overflow: hidden;
  position: relative;
  padding: 0 0 0 calc(0);
}

.classDiscussWrap {
  width: 100%;
  height: auto;
}

.el-header {
  height: auto !important;
}

.el-main {
  position: relative;
  height: auto;
  overflow: hidden;
}

@media screen and (max-width: 1256px) {
  .el-main {
    width: 1800px;
  }
}
</style>
