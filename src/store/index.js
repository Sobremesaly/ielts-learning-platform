import Vue from 'vue'
import Vuex from 'vuex'
import teacher from './modules/teacher';
import student from './modules/student';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: '',
    userId: '',
    avatar: '',
    courseList: '',
    identifier: '',
  },
  modules: {
    teacher: teacher,
    student: student,
  },

  mutations: {
    setIdentifier(state, identifier) {
      state.identifier = identifier;
    }
  },

  getters: {
    getIdentifier(state) {
      return state.identifier;
    }
  }
})
