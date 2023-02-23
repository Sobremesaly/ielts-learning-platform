package com.example.preSpring.service.login;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.StudentMapper;
import com.example.preSpring.pojo.Students;
import com.example.preSpring.tools.RedisUtil;
import com.example.preSpring.tools.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 小叶子
 */
@Service
public class StudentLoginServiceImpl implements StudentLoginService {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TokenUtil tokenUtil;

    @Override
    public String getStudentLogin(String studentId, String studentPassword) {
        QueryWrapper<Students> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>(2);
        wrapper.eq("student_num", studentId);
        wrapper.eq("student_password", studentPassword);
        String token = null;
        Students student = studentMapper.selectOne(wrapper);
        if (student != null) {
            token = tokenUtil.sign(studentId, studentPassword);
        }
        if (token != null) {
            map.put("token", token);
            map.put("flag", "right");
            return JSON.toJSONString(map);
        }
        map.put("flag", "error");
        return JSON.toJSONString(map);
    }
}
