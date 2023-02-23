package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.ExaminationConditionMapper;
import com.example.preSpring.pojo.ExaminationCondition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class ExaminationConditionServiceImpl implements ExaminationConditionService {

    @Resource
    private ExaminationConditionMapper examinationConditionMapper;

    @Override
    public String finishExam(String examNum, String studentNum, String examScore, String courseNum) {
        HashMap<String, Object> map = new HashMap<>(1);
        ExaminationCondition examinationCondition = new ExaminationCondition();
        examinationCondition.setExamNum(examNum);
        examinationCondition.setStudentNum(studentNum);
        examinationCondition.setExamScore(Long.parseLong(examScore));
        examinationCondition.setCourseNum(courseNum);
        try {
            examinationConditionMapper.insert(examinationCondition);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String getExaminationConditionByCourseNum(String courseNum, String studentNum) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper<ExaminationCondition> query = new QueryWrapper<ExaminationCondition>();
        query.eq("course_num", courseNum);
        query.eq("student_num", studentNum);
        try {
            List<ExaminationCondition> list = examinationConditionMapper.selectList(query);
            map.put("list", list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
