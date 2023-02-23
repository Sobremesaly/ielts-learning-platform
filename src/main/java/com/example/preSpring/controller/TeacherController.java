package com.example.preSpring.controller;

import com.example.preSpring.service.TeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class TeacherController {
    @Resource
    TeacherService teacherService;

    @CrossOrigin
    @RequestMapping("/getTeacherInformation")
    public String getTeacherInformation(String userId) {
        return teacherService.getTeacherInformation(userId);
    }

    @CrossOrigin
    @RequestMapping("/getTeacherAllCourseCourse")
    public String getTeacherAllCourse(String userId) {
        return teacherService.getTeacherAllCourse(userId);
    }


}
