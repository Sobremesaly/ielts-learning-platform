package com.example.preSpring.controller;

import com.example.preSpring.service.ExaminationConditionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
@CrossOrigin
public class ExaminationConditionController {

    @Resource
    private ExaminationConditionService examinationConditionService;

    @RequestMapping("finishExam")
    public String finishExam(String examNum, String studentNum, String examScore, String courseNum) {
        return examinationConditionService.finishExam(examNum, studentNum, examScore, courseNum);
    }

    @RequestMapping("getExaminationConditionByCourseNum")
    public String getExaminationConditionByCourseNum(String courseNum, String studentNum) {
        return examinationConditionService.getExaminationConditionByCourseNum(courseNum, studentNum);
    }
}
