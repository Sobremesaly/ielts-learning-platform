import Vue from 'vue'
import Router from 'vue-router'
import JoinClass from '../components/pc/JoinClass'
import PcLogin from '../components/pc/Login'
import PcMainInterface from '../components/pc/MainInterface'
import ClassStudy from '../components/pc/class/ClassStudy';
import ClassDiscuss from "../components/pc/class/ClassDiscuss";
import ChoiceQuestion from "../components/pc/question/ChoiceQuestion";
import JudgeQuestion from "../components/pc/question/JudgeQuestion";
import EssayQuestion from "../components/pc/question/EssayQuestion";
import GapQuestion from "../components/pc/question/GapQuestion";
import StatisticalLearning from "../components/pc/StatisticalLearning";
import PersonalInformation from "../components/pc/PersonalInformation";
import Inboxes from "../components/pc/Inboxes";
import MyClass from "../components/pc/MyClass";
import TeacherPersonInformation from "../components/tpc/TeacherPersonInformation";
import AllClass from "../components/pc/AllClass";
import TeacherMyClass from "../components/tpc/TeacherMyClass";
import PublishHomework from "../components/tpc/PublishHomework";
import CheckQuestion from "../components/pc/question/CheckQuestion";
import LearningSituation from "../components/tpc/LearningSituation";
import TeacherMyRelease from "../components/tpc/TeacherMyRelease";
import publishJob from "../components/tpc/PublishJob"
import MyHomework from "../components/pc/MyHomework"
import DoHomework from "../components/pc/DoHomework"
import MyExamination from "../components/pc/MyExamination"
import DoExamination from "../components/pc/DoExamination"
import MultipleQuestion from "../components/pc/question/MultipleQuestion";
import PublishExperiment from "../components/tpc/PublishExperiment";
import MyTest from "../components/pc/MyTest"
import DoTest from "../components/pc/DoTest"
import Question from "../components/pc/programmeQuestion/Question"
import QuestionInformation from '../components/pc/programmeQuestion/QuestionImformation'
import TeacherDiscuss from "../components/tpc/TeacherDiscuss"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'PcLogin',
      component: PcLogin,
    },
    {
      path: '/pcMainInterface',
      name: 'PcMainInterface',
      component: PcMainInterface,
    },
    {
      path: '/joinClass',
      name: 'JoinClass',
      component: JoinClass,
    },

    {
      path: '/classStudy',
      name: 'ClassStudy',
      component: ClassStudy,
    },
    {
      path: '/classDiscuss',
      name: 'ClassDiscuss',
      component: ClassDiscuss,
    },
    {
      path: '/choiceQuestion',
      name: 'ChoiceQuestion',
      component: ChoiceQuestion,
    },
    {
      path: '/judgeQuestion',
      name: 'JudgeQuestion',
      component: JudgeQuestion,
    },
    {
      path: '/essayQuestion',
      name: 'EssayQuestion',
      component: EssayQuestion,
    },
    {
      path: '/gapQuestion',
      name: 'GapQuestion',
      component: GapQuestion,
    },
    {
      path: '/StatisticalLearning',
      name: 'StatisticalLearning',
      component: StatisticalLearning,
    },
    {
      path: '/PersonalInformation',
      name: 'PersonalInformation',
      component: PersonalInformation,
    },
    {
      path: '/Inboxes',
      name: 'Inboxes',
      component: Inboxes,
    },
    {
      path: '/MyClass',
      name: 'MyClass',
      component: MyClass,
    },
    {
      path: '/TeacherPersonInformation',
      name: 'TeacherPersonInformation',
      component: TeacherPersonInformation,
    },
    {
      path: '/AllClass',
      name: 'AllClass',
      component: AllClass,
    },
    {
      path: '/TeacherMyClass',
      name: 'TeacherMyClass',
      component: TeacherMyClass,
    },
    {
      path: '/PublishHomework',
      name: 'PublishHomework',
      component: PublishHomework,
    },
    {
      path: '/CheckQuestion',
      name: 'CheckQuestion',
      component: CheckQuestion,
    },
    {
      path: '/LearningSituation',
      name: 'LearningSituation',
      component: LearningSituation,
    },
    {
      path: '/TeacherMyRelease',
      name: 'TeacherMyRelease',
      component: TeacherMyRelease,
    },
    {
      path: '/publishJob',
      name: 'publishJob',
      component: publishJob,
    },
    {
      path: '/MyHomework',
      name: 'MyHomework',
      component: MyHomework
    },
    {
      path: '/DoHomework',
      name: 'DoHomework',
      component: DoHomework
    },
    {
      path: '/MyExamination',
      name: 'MyExamination',
      component: MyExamination
    },
    {
      path: '/DoExamination',
      name: 'DoExamination',
      component: DoExamination
    },
    {
      path: '/MultipleQuestion',
      name: 'MultipleQuestion',
      component: MultipleQuestion
    },
    {
      path: '/PublishExperiment',
      name: 'PublishExperiment',
      component: PublishExperiment,
    },
    {
      path: '/MyTest',
      name: 'MyTest',
      component: MyTest,
    },
    {
      path: '/DoTest',
      name: 'DoTest',
      component: DoTest,
    },
    {
      path: '/Question',
      name: 'Question',
      component: Question,
    },
    {
      path: '/QuestionInformation',
      name: 'QuestionInformation',
      component: QuestionInformation,
    },
    {
      path: '/TeacherDiscuss',
      name: 'TeacherDiscuss',
      component: TeacherDiscuss,
    }
  ]
})


