package com.example.preSpring.controller;

import com.example.preSpring.service.HomeworkService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class HomeworkController {
    @Resource
    HomeworkService homeworkService;

    @RequestMapping(value = "/teacherCreateHomework")
    @CrossOrigin
    public String teacherCreateHomework(String homeworkTopic, String homeworkContent, String courseNum, String teacherNum) {
        return homeworkService.teacherCreateHomework(homeworkTopic, homeworkContent, courseNum, teacherNum);
    }

    @RequestMapping(value = "/uploadHomeworkWord")
    @CrossOrigin
    public String uploadHomeworkWord(MultipartFile file) {
        return homeworkService.uploadHomeworkWord(file);
    }

    @RequestMapping(value = "getHomeworkByCourseNum")
    @CrossOrigin
    public String getHomeworkByCourseNum(String courseNum) {
        return homeworkService.getHomeworkByCourseNum(courseNum);
    }

    @RequestMapping(value = "setWorkTime")
    @CrossOrigin
    public String setWorkTime(String homeworkNum, String endTime) {
        return homeworkService.setWorkTime(homeworkNum, endTime);
    }

    @RequestMapping(value = "getHomeworkByNum")
    @CrossOrigin
    public String getHomeworkByNum(String homeworkNum) {
        return homeworkService.getHomeworkByNum(homeworkNum);
    }
}
