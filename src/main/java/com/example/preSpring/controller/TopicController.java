package com.example.preSpring.controller;

import com.example.preSpring.service.exam.TopicService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class TopicController {
    @Resource
    TopicService topicService;

    @RequestMapping(value = "/getTopicByExamNum")

    @CrossOrigin
    public String getTopicByExamNum(String examNum) {
        return topicService.getTopicByExamNum(examNum);
    }
}
