package com.example.preSpring.controller;

import com.example.preSpring.service.SubmitTestService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class SubmitTestController {
    @Resource
    SubmitTestService submitTestService;

    @RequestMapping(value = "/sendTest")
    @CrossOrigin
    public String sendTest(String studentNum, String testNum, MultipartFile file, String submitContext) {
        return submitTestService.sendTest(studentNum, testNum, file, submitContext);
    }
}
