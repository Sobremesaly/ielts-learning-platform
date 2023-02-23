package com.example.preSpring.controller;


import com.example.preSpring.service.MessageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class MessageController {
    @Resource
    MessageService messageService;
    @RequestMapping(value="/teacherPostMessage")
    @CrossOrigin
    public String teacherPostMessage(String courseNum,String teacherId,String timeNow, String messageTitle,String messageContext){
        return messageService.teacherPostMessage(courseNum, teacherId, timeNow, messageTitle, messageContext);
    }

    @CrossOrigin
    @RequestMapping("/getStudentMessage")
    public String getStudentMessage(String studentId){
        return messageService.getStudentMessage(studentId);
    }
}
