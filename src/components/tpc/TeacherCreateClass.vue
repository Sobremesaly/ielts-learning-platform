<template>
  <div class="con">
    <div class="classImg">
      <el-upload
        ref="courseImg"
        class="avatar-uploader"
        :action="actionStr"
        :auto-upload="false"
        :on-change="handleAvatarSuccess"
        :on-success="uploadOver"
        :show-file-list="false"
        :headers="headerObj"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="clickMe">点我上传课程图片</i>
      </el-upload>
    </div>
    <div class="inputList">
      <input placeholder="请输入课程名" v-model="courseName"></input>
      <input placeholder="请输入课程简介" v-model="courseRemark"></input>
      <input placeholder="请输入课程专业" v-model="courseMajor"></input>
      <select v-model="courseType">
        <option value="1">
          选修课
        </option>
        <option value="0">
          必修课
        </option>
        <option value="2">
          专业课
        </option>
      </select>
      <div class="block">
        <el-date-picker
          v-model="endTime"
          type="date"
          placeholder="结课时间">
        </el-date-picker>
      </div>
    </div>
  </div>
</template>

<script>
import qs from "qs";
import {dateFormat, getNowFormatDate} from "../../share/timeFormate";

export default {
  name: "TeacherCreateClass",
  data() {
    return {
      imageUrl: '',
      courseType: 0,
      endTime:'',
      courseName:'',
      courseRemark:'',
      courseMajor:'',
      actionStr: 'http://localhost:8090/uploadCourseImg?teacherNum',
      headerObj:{
        token:localStorage.getItem("token"),
      }
    };
  },

  created() {
    this.actionStr = this.actionStr + "=" + localStorage.getItem("userId");
    this.$myBus.$on('confirmCreate', () => {
      // 函数体内即为代码逻辑
      this.teacherCreateCourse();
    })

  },
  methods: {
    uploadOver(res){
      this.$myBus.$emit('courseListChange',res.list);
    },
    handleAvatarSuccess(file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    async uploadCourseImg(){
      await this.$refs.courseImg.submit();
    },
    async teacherCreateCourse(){
      let course = {
        courseStudentNum:0,
        courseType: this.courseType,
        courseEndTime: dateFormat(this.endTime),
        courseStartTime: getNowFormatDate(),
        courseName: this.courseName,
        courseRemark: this.courseRemark,
        courseMajor: this.courseMajor,
        teacherNum: localStorage.getItem("userId"),
      }
      let {data:res} = await this.$http.post("teacherCreateCourse", qs.stringify(course), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
      if(res.flag === 'right'){
        await this.uploadCourseImg();
        this.$message.success("创建成功");
      }
      else{
        this.$message.error("创建失败");
      }
    },
  },
  beforeDestroy(){
    this.$myBus.off('confirmCreate')
  }

}
</script>

<style scoped>
.block >>> .el-input__inner::placeholder{
  color: #5e5c5c !important;
}
.block >>> .el-input__icon {
  font-size: 24px !important;
  color: #5e5c5c !important;
}
.block >>> .el-input__inner {
  border-radius: 0 !important;
  border: 3px solid #58636d;
  font-size: 24px !important;
  color: #5e5c5c !important;
}
.block {
  margin-top: 25px;
  margin-left: 25%;
}
.inputList select {
  display: block;
  margin-top: 35px;
  border: none;
  border-bottom: 3px solid #58636d;
  font-size: 24px;
  color: #5e5c5c;
  font-weight: 500;
  outline: none;
  margin-left: 25%;
}
.inputList input:focus {
  border-bottom: 3px solid wheat;
}
.inputList input{
  display: block;
  margin-top: 35px;
  border: none;
  border-bottom: 3px solid #58636d;
  font-size: 24px;
  color: #5e5c5c;
  font-weight: 500;
  outline: none;
  margin-left: 25%;
}
.clickMe {
  display: inline-block;
  line-height: 40px;
  font-size: 40px;
  margin-top: 46px;
}
.classImg {
  display: inline-block;
  margin-left: 34%;
}
.con {
  margin-top: 0 !important;
  width: 100%;
  height: 100%;
}

.classImg {
  border: 1px dashed #c61e1e;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  height: 180px;
  width: 180px;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 180px;
  height: 180px;
  display: block;
}
</style>
