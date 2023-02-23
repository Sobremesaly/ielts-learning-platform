import option from "element-ui/packages/option";

const state = {
  courseNum: '',//记录学生打开的课程号
  joinCourse: '',//在学生进入课程前，要记录这个学生是否已经加入该课程
  homeworkNum: '',//记录学生打开的作业号
  examNum: '',//记录学生打开的考试号
  testNum: '',//记录学生打开的实验号
  topicList: [],//记录题目列表
  currentIndex: 0,//记录显示题目的下标
  submitNum: 0,//记录提交的数目
  topicSituation: [],//记录做题情况
  currentOption: [],//记录当前的题目选项
  studentOptionList: [],//记录学生的选项
  studentTotalScore: 0,//记录题目的总分
  currentScore: 0,//记录学生当前得分情况
  finishNum: 0,//记录学生完成的题目数量
  specialScore: 0,//编程题和问答题的分数要特殊处理
};

// actions异步请求处理
const actions = {};

const getters = {
  courseNum: state => state.courseNum,
};

const mutations = {
  resetTopicSituation(state) {
    state.finishNum = 0;
    state.topicSituation = [];
    state.specialScore = 0;
  },
  resetCurrentScore(state) {
    state.currentScore = 0;
  },
  setCurrentScore(state) {
    state.currentScore = 0;
    for (let i = 0; i < state.topicList.length; i++) {
      if (state.topicSituation[i] === 'right') {
        state.currentScore = state.currentScore + state.topicList[i].topicScore;
      }
    }
    state.currentScore = state.currentScore + state.specialScore;
  },

  addCurrentScore(state, score) {
    state.specialScore = state.specialScore + parseInt(score);
  },

  setStudentTotalScore(state) {
    state.studentTotalScore = 0;
    for (let i = 0; i < state.topicList.length; i++) {
      state.studentTotalScore = state.topicList[i].topicScore + state.studentTotalScore;
    }
  },
  setTopicSituation(state, situation) {
    state.finishNum++;
    state.topicSituation[state.currentIndex] = situation
  },
  setStudentOptionList(state, optionList) {
    state.studentOptionList = optionList;
  },
  setCurrentOption(state) {
    state.currentOption = []
    for (let i = 0; i < state.studentOptionList.length; i++) {
      if (state.studentOptionList[i].topicNum === state.topicList[state.currentIndex].topicNum) {
        state.currentOption.push(state.studentOptionList[i])
      }
    }
  },
  setSubmitNum(state, submitNum) {
    state.submitNum = submitNum;
  },

  preQuestion(state) {
    state.currentIndex = state.currentIndex - 1;
  },
  nextQuestion(state) {
    state.currentIndex = state.currentIndex + 1;
  },
  setCurrentIndex(state, currentIndex) {
    state.currentIndex = currentIndex
  },
  setStudentTopicList(state, topicList) {
    state.topicList = topicList;
  },
  setTestNum(state, testNum) {
    state.testNum = testNum;
  },
  setExamNum(state, examNum) {
    state.examNum = examNum;
  },
  setHomeworkNum(state, homeworkNum) {
    state.homeworkNum = homeworkNum;
  },
  setStudentCourseNum(state, courseNum) {
    state.courseNum = courseNum;
  },
  setJoinCourse(state, joinCourse) {
    state.joinCourse = joinCourse;
  }
};

export default {
  state,
  getters,
  actions,
  mutations
}
