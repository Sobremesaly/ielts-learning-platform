package com.example.preSpring.controller;

import com.example.preSpring.service.exam.AnswerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 小叶子
 * 这个类中方法要实现多态
 */
@RestController
@CrossOrigin
public class AnswerController {

    @Resource
    AnswerService answerService;

    @RequestMapping("verifyAnswer")

    public String verifyAnswer(String examNum, String topicNum, String answer, String essay) {
        if (essay != null) {
            return answerService.verifyAnswer(examNum, topicNum, answer, essay);
        }
        return answerService.verifyAnswer(examNum, topicNum, answer);
    }

    @RequestMapping("verifyMultiply")
    public String verifyMultiply(String examNum, String topicNum, String answer) {
        return answerService.verifyMultiply(examNum, topicNum, answer);
    }

    @RequestMapping("verifyProgramme")
    public String verifyProgramme(String examNum, String topicNum, String answer, String type) {
        return answerService.verifyProgramme(examNum, topicNum, answer, type);
    }
}
