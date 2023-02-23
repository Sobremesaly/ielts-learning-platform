package com.example.preSpring.controller;

import com.example.preSpring.service.login.StudentLoginService;
import com.example.preSpring.service.login.TeacherLoginService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class LoginController {
    /**
     * 完成学生的登录功能
     * 通过调用接口查找数据库中是否存在学号和密码符合的学生用户
     *
     * @ studentId 学生用户学号
     * @ password 学生用户密码
     * @ return 返回查询结果
     */
    @Resource
    StudentLoginService studentLoginService;

    @RequestMapping(value = "/studentLogin")
    @CrossOrigin
    public String studentLogin(@RequestParam("Id") String studentId, @RequestParam("password") String studentPassword) {
        return studentLoginService.getStudentLogin(studentId, studentPassword);
    }

    @Resource
    TeacherLoginService teacherLoginService;

    @RequestMapping(value = "/teacherLogin")
    @CrossOrigin
    /**
     完成老师的登录功能
     通过调用接口查找数据库中是否存在工号和密码符合的学生用户
     @ teacherId 老师工号
     @ teacherPassword 老师用户密码
     @ return 返回查询结果
     */
    public String teacherLogin(@RequestParam("Id") String teacherId, @RequestParam("password") String teacherPassword) {
        return teacherLoginService.getTeacherLogin(teacherId, teacherPassword);
    }
}
