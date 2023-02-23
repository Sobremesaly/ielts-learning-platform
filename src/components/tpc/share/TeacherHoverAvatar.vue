<template>
  <div class="header">
    <div class="h_header">
      <span>
        Hi~.
      </span>
      <div class="btn">

        <button class="proButton" @click="myClass">
          我的课程
        </button>
      </div>
      <div class="btn">
        <button class="proButton" @click="myPerson">
          个人资料
        </button>
      </div>
      <hr>
      <div class="c_btn" @click="exit">
        <span>
          安全退出
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TeacherHoverAvatar",
  methods: {
    exit() {
      localStorage.clear();
      this.$router.push("/");
    },
    myClass() {
      this.$router.push("/TeacherMyClass")
    },
    myPerson() {
      this.$router.push("/TeacherPersonInformation")
    }
  }
}
</script>

<style scoped>
.c_btn {
  margin-top: 20px;
}

.c_btn span {
  display: block;
  margin-top: 0;
  margin-right: 35px;
  float: right;
  font-size: 10px !important;
}

.c_btn span:hover {
  cursor: pointer;
  color: #4285F4;
}

hr {
  width: 90%;
  margin-top: 40px;
}

.h_header span {
  margin-top: 10px;
  padding-left: 30px;
  font-weight: 500;
  font-size: 24px;
  color: #333333;
}

.proButton:hover {
  cursor: pointer;
  color: #4285F4;
}

.proButton {
  margin-left: 10px;
  margin-top: 15px;
  border: none;
  border-radius: 3px;
  width: 165px;
  height: 48px;
  background-color: #f8f8f8;
}

.h_header {
  display: flex;
  flex-direction: column;
  width: 95%;
  height: auto;
  margin-left: auto;
  margin-right: auto;
}

.header {
  width: 400px;
  height: 300px;
}
</style>
