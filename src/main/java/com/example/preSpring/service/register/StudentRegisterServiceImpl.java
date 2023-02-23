package com.example.preSpring.service.register;

import com.alibaba.fastjson.JSON;
import com.example.preSpring.dao.StudentMapper;
import com.example.preSpring.pojo.Students;
import com.example.preSpring.util.SendMessage;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class StudentRegisterServiceImpl implements StudentRegisterService{
    @Resource
    private StudentMapper studentMapper;

    @Resource
    RedisTemplate<String,String> redisTemplate;

    @Override
    public String getStudentRegister(String studentId,String studentPassword,String studentPhoneNumber,String code){
        HashMap<String,Object> map = new HashMap<>(2);
        if(code.equals(redisTemplate.opsForValue().get(studentPhoneNumber))){
            Students student = new Students();
            student.setId(0);
            student.setStudentNum(studentId);
            student.setStudentPassword(studentPassword);
            student.setStudentPhoneNum(studentPhoneNumber);
            studentMapper.insert(student);
            map.put("flag","right");
        }
        else {
            map.put("flag","error");
        }
        return JSON.toJSONString(map);
    }
}
