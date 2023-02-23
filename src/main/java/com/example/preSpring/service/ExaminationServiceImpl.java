package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.preSpring.dao.AnswerMapper;
import com.example.preSpring.dao.ExaminationMapper;
import com.example.preSpring.dao.OptionMapper;
import com.example.preSpring.dao.TopicMapper;
import com.example.preSpring.pojo.Answer;
import com.example.preSpring.pojo.Examination;
import com.example.preSpring.pojo.Option;
import com.example.preSpring.pojo.Topic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Resource
    ExaminationMapper examinationMapper;

    @Override
    public String teacherCreateExamination(String teacherNum, String examName, String courseNum) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            QueryWrapper<Examination> query = new QueryWrapper<Examination>();
            int maxId = Math.toIntExact(examinationMapper.selectOne(query.orderByDesc("id").last("limit 1")).getId()) + 1;
            String examNum = "000" + maxId;
            Examination examination = new Examination();
            examination.setTeacherNum(teacherNum);
            examination.setExamName(examName);
            examination.setCourseNum(courseNum);
            examination.setExamNum(examNum);
            long now = System.currentTimeMillis();
            long after = now + 720000 * 5;
            Timestamp timestamp = new Timestamp(now);
            Timestamp nextTime = new Timestamp(after);
            examination.setExamStartTime(timestamp);
            examination.setExamEndTime(nextTime);
            examinationMapper.insert(examination);
            map.put("flag", "right");
            map.put("examNum", examNum);
        } catch (Exception e) {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);
    }

    @Resource
    TopicMapper topicMapper;
    @Resource
    OptionMapper optionMapper;
    @Resource
    AnswerMapper answerMapper;

    @Override
    public String teacherSupplyExamination(List<Topic> topics, List<Option> options, List<Answer> answers) {
        HashMap<String, Object> map = new HashMap<>(1);
        /*录题也就是对表的循环插入*/
        for (Topic topic : topics) {
            topicMapper.insert(topic);
        }
        for (Option option : options) {
            optionMapper.insert(option);
        }
        for (Answer answer : answers) {
            answerMapper.insert(answer);
        }
        map.put("flag", "right");
        return JSON.toJSONString(map);
    }

    @Override
    public String getExaminationByCourseNum(String courseNum) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper<Examination> wrapper = new QueryWrapper<>();
        wrapper.eq("course_num", courseNum);
        List<Examination> list = examinationMapper.selectList(wrapper);
        map.put("list", list);
        return JSON.toJSONString(map);
    }

    @Override
    public String setExamTime(String examNum, String endTime) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            UpdateWrapper<Examination> updateWrapper = new UpdateWrapper<Examination>();
            updateWrapper.eq("exam_num", examNum);
            Timestamp time = Timestamp.valueOf(endTime);
            updateWrapper.setSql("exam_end_time = '" + time + "'");
            examinationMapper.update(null, updateWrapper);
            QueryWrapper<Examination> query = new QueryWrapper<>();
            query.eq("exam_num", examNum);
            List<Examination> list = examinationMapper.selectList(query);
            map.put("list", list);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
