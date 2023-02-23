package com.example.preSpring.controller;

import com.example.preSpring.pojo.Test;
import com.example.preSpring.service.TestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class TestController {
    @Resource
    TestService testService;

    @RequestMapping(value = "/teacherCreateTest")

    @CrossOrigin
    public String teacherCreateTest(String courseNum, String testPurpose, String testTask, String testStep, String testEndTime, String testName) {
        return testService.teacherCreateTest(courseNum, testPurpose, testTask, testStep, testEndTime, testName);
    }

    @RequestMapping(value = "/getTestByCourseNum")

    @CrossOrigin
    public String getTestByCourseNum(String courseNum) {
        return testService.getTestByCourseNum(courseNum);
    }

    @RequestMapping(value = "/setTestTime")

    @CrossOrigin
    public String setTestTime(String testNum, String endTime) {
        return testService.setTestTime(testNum, endTime);
    }

    @RequestMapping(value = "getTestByNum")

    @CrossOrigin
    public String getTestByNum(String testNum) {
        return testService.getTestByNum(testNum);
    }
}
