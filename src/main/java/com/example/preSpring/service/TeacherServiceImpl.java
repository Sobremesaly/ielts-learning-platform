package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.CourseMapper;
import com.example.preSpring.dao.TeacherMapper;
import com.example.preSpring.pojo.Courses;
import com.example.preSpring.pojo.Students;
import com.example.preSpring.pojo.Teachers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    TeacherMapper teacherMapper;

    @Override
    public String getTeacherInformation(String teacherId) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper query = new QueryWrapper();
        query.eq("teacher_num", teacherId);
        Teachers teacher = teacherMapper.selectOne(query);
        map.put("user", teacher);
        return JSON.toJSONString(map);
    }

    @Resource
    CourseMapper courseMapper;

    @Override
    public String getTeacherAllCourse(String teacherId) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper<Courses> query = new QueryWrapper<Courses>();
        query.eq("teacher_num", teacherId);
        List<Courses> list = courseMapper.selectList(query);
        map.put("list", list);
        return JSON.toJSONString(map);
    }
}
