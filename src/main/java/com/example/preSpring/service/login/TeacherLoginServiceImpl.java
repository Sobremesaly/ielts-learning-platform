package com.example.preSpring.service.login;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.TeacherMapper;
import com.example.preSpring.pojo.Teachers;
import com.example.preSpring.tools.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 小叶子
 */
@Service
public class TeacherLoginServiceImpl implements TeacherLoginService{
    @Resource
    private TeacherMapper teacherMapper;

    @Autowired
    private TokenUtil tokenUtil;
    @Override
    public String getTeacherLogin(String teacherId,String teacherPassword) {
        QueryWrapper wrapper = new QueryWrapper();
        HashMap<String,Object> map = new HashMap<>(2);
        wrapper.eq("teacher_num",teacherId);
        wrapper.eq("teacher_password",teacherPassword);
        String token = null;
        Teachers teacher = teacherMapper.selectOne(wrapper);
        /*查询判断是否存在这个用户,存在则给予token令牌*/
        if(teacher != null){
            token = tokenUtil.sign(teacherId,teacherPassword);
        }
        /*返回token令牌给前端*/
        if(token != null){
            map.put("token", token);
            map.put("flag","right");
            return JSON.toJSONString(map);
        }
        map.put("flag","error");
        return JSON.toJSONString(map);
    }
}
