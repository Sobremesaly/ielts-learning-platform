package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.StudentMapper;
import com.example.preSpring.pojo.Students;
import org.springframework.stereotype.Service;
import sun.invoke.util.Wrapper;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 小叶子
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentMapper studentMapper;

    @Override
    public String getStudentInformation(String studentId) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper<Students> query = new QueryWrapper<>();
        query.eq("student_num", studentId);
        Students student = studentMapper.selectOne(query);
        map.put("user", student);
        return JSON.toJSONString(map);
    }
}
