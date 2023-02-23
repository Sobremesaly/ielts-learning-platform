<template>
  <el-container>
    <el-header>
      <teacher-class-header>
      </teacher-class-header>
    </el-header>
    <el-main>
      <div class="title">
        <p>创建实验</p>
      </div>
      <div class="publishBody">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="实验名称" prop="testName">
            <el-input v-model="ruleForm.testName"></el-input>
          </el-form-item>

          <el-form-item label="实验目的" prop="testPurpose">
            <el-input type="textarea" v-model="ruleForm.testPurpose"></el-input>
          </el-form-item>
          <el-form-item label="实验任务" prop="testTask">
            <el-input type="textarea" v-model="ruleForm.testTask"></el-input>
          </el-form-item>
          <el-form-item label="实验步骤" prop="testStep">
            <el-input type="textarea" v-model="ruleForm.testStep"></el-input>
          </el-form-item>
          <el-form-item label="截止时间" required>
            <el-col :span="11">
              <el-form-item prop="date1">
                <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.date1"
                                style="width: 100%;"
                                value-format="yyyy-MM-dd"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item prop="date2">
                <el-time-picker placeholder="选择时间" v-model="ruleForm.date2" style="width: 100%;"
                                value-format="HH:mm:ss"></el-time-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import TeacherClassHeader from "./share/TeacherClassHeader";
import qs from "qs";

export default {
  name: "PublishExperiment",
  components: {TeacherClassHeader},
  data() {
    return {
      ruleForm: {
        testName: '',
        testPurpose: '',
        testTask: '',
        testStep: '',
        date1: '',
        date2: '',
      },
      rules: {
        testName: [
          {required: true, message: '请输入实验名称', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
        ],
        testPurpose: [
          {required: true, message: '请填写实验目的', trigger: 'blur'}
        ],
        testTask: [
          {required: true, message: '请填写实验任务', trigger: 'blur'}
        ],
        testStep: [
          {required: true, message: '请填写实验步骤', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    async teacherCreateTest() {
      console.log(Date.parse(this.ruleForm.date1 + " " + this.ruleForm.date2))
      let test = {
        courseNum: this.$store.state.teacher.courseNum,
        testPurpose: this.ruleForm.testPurpose,
        testTask: this.ruleForm.testTask,
        testStep: this.ruleForm.testStep,
        testEndTime: this.ruleForm.date1 + " " + this.ruleForm.date2,
        testName: this.ruleForm.testName,
      }
      let {data: res} = await this.$http.post("teacherCreateTest", qs.stringify(test), {headers: {'Content-Type': 'application/x-www-form-urlencoded'}});
      if (res.flag === 'right') {
        this.$message.success("创建实验成功");
      } else {
        this.$message.error("创建实验失败");
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.teacherCreateTest();
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.demo-ruleForm >>> .el-form-item {
  margin-bottom: 30px;
}

.title p {
  text-align: center;
  font-size: 32px;
  color: #3b3b3b;
  font-weight: bold;
  letter-spacing: 3.5px;
}

.title {
  margin-top: 50px;
  margin-right: auto;
  margin-left: auto;
}

.publishBody {
  margin-top: 40px;
  margin-right: auto;
  margin-left: auto;
  width: 40%;
}
</style>
