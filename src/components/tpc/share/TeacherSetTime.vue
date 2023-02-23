<template>
  <div class="con">
    <div class="timePick">
      <div class="block">
        <el-date-picker
          v-model="value2"
          type="datetime"
          placeholder="选择日期时间"
          align="right"
          @change="timeChange"
          :picker-options="pickerOptions">
        </el-date-picker>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherSetTime",
  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          },
        }],
        disabledDate(date) {
          // 如果没有后面的-8.64e7就是不可以选择今天的
          return date.getTime() < Date.now() - 8.64e7;
        }
      },
      value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      value2: ''
    };
  },
  methods: {
    timeChange() {
      this.$myBus.$emit("timeChange", this.value2)
    }
  }
}
</script>

<style scoped>
.deleteWork {

}

.block >>> .el-range-separator {
  line-height: 38px;
}

.block >>> .el-input__icon {
  font-size: 21px !important;
}

.block >>> .el-range-input { /*Internet Explorer 10+*/
  color: #212121 !important;
  font-size: 21px;
}

.block >>> .el-date-editor {
  border-radius: 0 !important;
  font-size: 24px !important;
  width: 300px;
  height: 50px;
}

.block {
  margin-top: 35px;
  background-color: white;
  padding-left: 25%;
}

.con {
  border: none !important;
  width: 100%;
  height: 100%;
}
</style>
