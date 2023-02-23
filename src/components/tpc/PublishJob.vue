<template>
  <el-container>
    <el-header>
      <teacher-class-header>
      </teacher-class-header>
    </el-header>
    <el-main>
      <div class="con">
        <div class="uploadDiv">
          <p class="uploadTitle">
            可以选择在下面上传word
          </p>

          <el-upload class="upload-demo" drag action="http://localhost:8090/uploadHomeworkWord" multiple show-file-list
                     accept=".doc,.docx,.xls,.xlsx"
                     :headers="headerObj"
                     :before-upload="beforeUpload" ref="upload" :before-remove="beforeRemove"
                     :file-list="fileList" :auto-upload="false"
                     :limit="5"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或点击上传</div>
            <div class="el-upload__tip uploadeMax">支持上传后缀为：.doc、.docx、.xls、.xlsx文件,且不超过200M</div>
            <div class="el-upload__tip uploadeError">Excel文件单元格内容避免使用"=" "+" "-" 或@开头，以免下载查看时，造成命令注入风险
            </div>

          </el-upload>
        </div>
        <div class="inputList">
          <div class="inputCon">
            <span>
              作业标题:
            </span>
            <input v-model="homeworkTopic">

            </input>
          </div>
          <div class="inputCon">
            <span>
              作业内容:
            </span>
            <input v-model="homeworkContent">

            </input>
          </div>
        </div>
        <div class="butCon">
          <button @click="teacherCreateHomework">完成创建</button>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import TeacherClassHeader from "./share/TeacherClassHeader";
import qs from "qs";

export default {
  name: "publishJob",
  components: {
    TeacherClassHeader,
  },
  data() {
    return {
      homeworkTopic: '',
      homeworkContent: '',
      fileList: [],
      headerObj: {
        token: localStorage.getItem("token"),
      }
    }
  },
  methods: {

    beforeUpload(file) {

      console.log(file)

      // 文件类型校验

      var testmsg = file.name.substring(file.name.lastIndexOf(".") + 1);

      const extension = testmsg === "doc" || testmsg === "docx" || testmsg === "xls" || testmsg === "xlsx";

      if (!extension) {

        this.$message({

          message: "上传文件只能是.doc、.docx、.xls、.xlsx格式!",

          type: "warning",

        });

        // 文件大小校验

        let size200M = file.size / 1024 / 1024 < 200

        if (!size200M) {

          this.$message.warning('上传大小不能超过200M!')

          return false

        }


      }

      return extension;

    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    async teacherCreateHomework() {
      let homework = {
        homeworkTopic: this.homeworkTopic,
        homeworkContent: this.homeworkContent,
        courseNum: this.$store.state.teacher.courseNum,
        teacherNum: localStorage.getItem("userId"),
      }
      let {data: res} = await this.$http.post("teacherCreateHomework", qs.stringify(homework), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      if (res.flag === 'right') {
        this.$message.success("创建成功");
        this.uploadWord();
      } else {
        this.$message.error("创建失败");
      }
    },
    uploadWord() {
      this.$refs.upload.submit();
    }
  }
}
</script>

<style scoped>
.butCon button {
  cursor: pointer;
  border: 2px #afaca9 solid;
  font-weight: 400;
  font-size: 24px;
}

.butCon {
  margin-top: 75px;
  padding-left: 40%;
  padding-bottom: 75px;
}

.inputCon span {
  font-size: 20px;
  font-weight: 500;
}

.inputCon input {
  outline: none;
  border: 2px solid #4e4b4b;
  font-size: 20px;
}

.inputCon {
  margin-top: 25px;
  padding-left: 25%;
}

.uploadTitle {
  display: inline-block;
  margin-right: auto;
  margin-left: auto;
}

.uploadDiv {
  margin-left: auto;
  margin-right: auto;
  padding-left: 25%;
}

.con {
  width: 800px;
  margin-top: 50px;
  margin-left: auto;
  margin-right: auto;
  background: #f5f3f3;
}

.uploadTitle {
  font-size: 24px;
  color: #2c2c2c;
  font-weight: 500;
}
</style>
