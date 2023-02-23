const state = {
  CreateClass: false,//控制弹出创建课程
  courseNum: '',//记录老师打开的课程号
  topicList: [],//老师创建的题目
  answerList: [],//题目的答案
  optionList: [],//题目的选项
  topicNum: 0,//创建考试题目的数量
  totalScore: 0,//创建题目的总分
  examNum: '',//记录老师当前创建的考试号
  topicId: 0,//记录老师当前所选择的题目号
  courseName: '',//记录老师打开的课程名
};

// getters计算属性
const getters = {
  CreateClass: state => state.CreateClass
};

// actions异步请求处理
const actions = {};

// mutations主要用来操作state
const mutations = {

  setCourseName(state, courseName) {
    state.courseName = courseName;
  },
  setTeacherState(state, Storage) {
    state = Storage;
  },
  setTotalScore(state) {
    state.totalScore = 0;
    for (let i = 0; i < state.topicList.length; i++) {
      state.totalScore = state.totalScore + Number(state.topicList[i].topicScore);
    }
  },
  setTopicId(state, topicId) {
    state.topicId = topicId;
  },
  topicNumAdd(state) {
    state.topicNum++;
  },
  topicNumDelete(state) {
    state.topicNum--;
  },
  setCreateClass(state, CreateClass) {
    state.CreateClass = CreateClass;
  },
  setCourseNum(state, courseNum) {
    state.courseNum = courseNum;
  },
  /*根据下标移除数组某一项*/
  removeTopic(state, id) {
    state.topicList.splice(id, 1);
  },
  addTopic(state, topic) {
    state.topicList.splice(topic.topicNum, 1, topic);
  },


  removerOption(state, id) {
    let index = 0;
    let length = 0;
    for (let i = 0; i < state.optionList.length; i++) {
      if (state.optionList[i].topicNum == state.topicList[id].topicNum) {
        index = i;
        length++;
      }
    }
    state.optionList.splice(length - index - 1, length);
  },
  addOptionList(state, option) {
    state.optionList.push(option);
  },

  removeAnswer(state, id) {
    let index = 0;
    let length = 0;
    for (let i = 0; i < state.answerList.length; i++) {
      if (state.answerList[i].topicNum == state.topicList[id].topicNum) {
        index = i;
        length++;
      }
    }
    state.answerList.splice(length - index - 1, 1);
  },
  addAnswerList(state, answer) {
    state.answerList.push(answer);
  },

  setExamNum(state, examNum) {
    console.log(examNum)
    state.examNum = examNum;
  },

  resetExam(state) {
    state.topicList = []
    state.answerList = []
    state.optionList = []
    state.topicNum = 0
    state.totalScore = 0
    state.examNum = ''
    state.topicId = 0
  }
};

export default {
  state,
  getters,
  actions,
  mutations
}
