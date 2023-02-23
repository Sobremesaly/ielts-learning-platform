package com.example.preSpring.controller;

import com.example.preSpring.service.SubmitHomeworkService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class SubmitHomeworkController {
    @Resource
    SubmitHomeworkService submitHomeworkService;

    @RequestMapping(value = "/sendHomework")
    @CrossOrigin
    public String sendHomework(String studentNum, String homeworkNum, MultipartFile file, String submitContext) {
        return submitHomeworkService.sendHomework(studentNum, homeworkNum, file, submitContext);
    }
}
