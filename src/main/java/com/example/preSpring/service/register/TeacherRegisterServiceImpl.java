package com.example.preSpring.service.register;

import com.alibaba.fastjson.JSON;
import com.example.preSpring.dao.StudentMapper;
import com.example.preSpring.dao.TeacherMapper;
import com.example.preSpring.pojo.Teachers;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 小叶子
 */
@Service
public class TeacherRegisterServiceImpl implements TeacherRegisterService{
    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    RedisTemplate<String,String> redisTemplate;

    @Override
    public String getTeacherRegister(String teacherId,String teacherPassword,String teacherPhoneNumber,String code) {
        HashMap<String, Object> map = new HashMap<>(2);
        if (code.equals(redisTemplate.opsForValue().get(teacherPhoneNumber))) {
            Teachers teacher = new Teachers();
            teacher.setId(0);
            teacher.setTeacherNum(teacherId);
            teacher.setTeacherPassword(teacherPassword);
            teacher.setTeacherPhone(teacherPhoneNumber);
            teacherMapper.insert(teacher);
            map.put("flag", "right");
        } else {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);
    }
}
