package com.example.preSpring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.preSpring.pojo.Answer;
import com.example.preSpring.pojo.Option;
import com.example.preSpring.pojo.Topic;
import com.example.preSpring.service.ExaminationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小叶子
 */
@RestController
public class ExaminationController {
    @Resource
    ExaminationService examinationService;

    @CrossOrigin
    @RequestMapping(value = "/teacherCreateExamination")
    public String teacherCreateExamination(String teacherNum, String examName, String courseNum) {
        return examinationService.teacherCreateExamination(teacherNum, examName, courseNum);
    }

    @CrossOrigin
    @RequestMapping(value = "/teacherSuppleExamination")
    public String teacherSupplyExamination(String topicList, String optionList, String answerList) {
        List<Topic> topics = JSONObject.parseArray(topicList, Topic.class);
        List<Option> options = JSONObject.parseArray(optionList, Option.class);
        List<Answer> answers = JSONObject.parseArray(answerList, Answer.class);
        return examinationService.teacherSupplyExamination(topics, options, answers);
    }

    @CrossOrigin
    @RequestMapping(value = "/getExaminationByCourseNum")
    public String getExaminationByCourseNum(String courseNum) {
        return examinationService.getExaminationByCourseNum(courseNum);
    }

    @CrossOrigin
    @RequestMapping(value = "/setExamTime")
    public String setExamTime(String examNum, String endTime) {
        return examinationService.setExamTime(examNum, endTime);
    }
}
