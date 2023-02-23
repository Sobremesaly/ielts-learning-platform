package com.example.preSpring.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.preSpring.dao.CourseMapper;
import com.example.preSpring.dao.StudentMapper;
import com.example.preSpring.pojo.Courses;
import com.example.preSpring.service.CoursesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@RestController
public class CourseController {
    @Resource
    CoursesService coursesService;

    @RequestMapping(value = "/getAllCourse")

    @CrossOrigin
    public String getAllCourses() {
        return coursesService.getAllCourses();
    }

    @RequestMapping(value = "/getCourseByNameOrMajorOrCourseNum")
    @CrossOrigin
    public String selectCourseBy(String input) {
        return coursesService.selectCourseBy(input);
    }

    @RequestMapping(value = "/getCourseByMajor")
    @CrossOrigin
    public String selectCourse(@RequestParam("major") String courseMajor) {
        return coursesService.selectCourse(courseMajor);
    }


    @RequestMapping(value = "/teacherCreateCourse")
    @CrossOrigin
    public String teacherCreateCourse(String courseStudentNum, String courseType,
                                      String courseEndTime, String courseStartTime,
                                      String courseName, String courseRemark,
                                      String courseMajor, String teacherNum) {
        Courses course = new Courses();
        /*必须是符合类型的字符串才能直接转成Timestamp的*/
        course.setCourseStudentNum(Long.parseLong(courseStudentNum));
        course.setCourseType(Long.parseLong(courseType));
        course.setCourseEndTime(Timestamp.valueOf(courseEndTime));
        course.setCourseStartTime(Timestamp.valueOf(courseStartTime));
        course.setCourseName(courseName);
        course.setCourseRemark(courseRemark);
        course.setCourseMajor(courseMajor);
        course.setTeacherNum(teacherNum);
        return coursesService.teacherCreateCourse(course);
    }

    @CrossOrigin
    @RequestMapping("/uploadCourseImg")
    public String studentUploadAvatar(@RequestParam("file") MultipartFile file, String teacherNum) {
        return coursesService.uploadCourseImg(file, teacherNum);
    }

    @CrossOrigin
    @RequestMapping("/getStudentAllCourse")
    public String getStudentAllCourse(String studentId) {
        return coursesService.getStudentAllCourse(studentId);
    }

    @CrossOrigin
    @RequestMapping("/checkStudentJoin")
    public String checkStudentJoin(String studentId, String courseNum) {
        return coursesService.checkStudentJoin(studentId, courseNum);
    }

    @CrossOrigin
    @RequestMapping("/studentJoinCourse")
    public String studentJoinCourse(String studentId, String courseNum) {
        return coursesService.studentJoinCourse(studentId, courseNum);
    }

    @CrossOrigin
    @RequestMapping("/getCourseByNum")
    public String getCourseByNum(String courseNum) {
        return coursesService.getCourseByNum(courseNum);
    }
}
