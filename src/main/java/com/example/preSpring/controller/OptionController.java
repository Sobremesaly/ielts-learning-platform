package com.example.preSpring.controller;

import com.example.preSpring.dao.OptionMapper;
import com.example.preSpring.service.exam.OptionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 小叶子
 */
@RestController
public class OptionController {
    @Resource
    OptionService optionService;

    @RequestMapping(value = "/getOptionByExamNum")
    
    @CrossOrigin
    public String getOptionByExamNum(String examNum) {
        return optionService.getOptionByExamNum(examNum);
    }

}
