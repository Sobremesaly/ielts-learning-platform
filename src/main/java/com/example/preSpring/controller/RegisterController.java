package com.example.preSpring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.preSpring.service.login.TeacherLoginService;
import com.example.preSpring.service.register.StudentRegisterService;
import com.example.preSpring.service.register.TeacherRegisterService;
import com.example.preSpring.util.SendMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@RestController
public class RegisterController {
    @Resource
    SendMessage sendMessage;
    @RequestMapping(value="/sendMessage")
    @CrossOrigin
    /**
     * @phoneNumber 用户收短信的手机号
     * 返回给用户结果，是发送成功还是已经存在短信或者发送失败等等
     */
    public String sendMessage(@RequestParam("phoneNumber") String phoneNumber ){
        List result = sendMessage.getCode(phoneNumber);
        HashMap<String,Object> map = new HashMap<>(2);
        map.put("message",result.get(0));
        return JSON.toJSONString(map);
    }

    @Resource
    StudentRegisterService studentRegisterService;
    @RequestMapping(value="/studentRegister")
    @CrossOrigin
    public String studentRegister(@RequestParam("phoneNumber") String phoneNumber,@RequestParam("registeredId") String studentId, @RequestParam("registeredPassword") String studentPassword,@RequestParam("verification") String code){
        return studentRegisterService.getStudentRegister(studentId,studentPassword,phoneNumber,code);
    }

    @Resource
    TeacherRegisterService teacherRegisterService;
    @RequestMapping(value="/teacherRegister")
    @CrossOrigin
    public String teacherLoginService(@RequestParam("phoneNumber") String phoneNumber,@RequestParam("registeredId") String studentId, @RequestParam("registeredPassword") String studentPassword,@RequestParam("verification") String code){
        return teacherRegisterService.getTeacherRegister(studentId,studentPassword,phoneNumber,code);
    }
}
