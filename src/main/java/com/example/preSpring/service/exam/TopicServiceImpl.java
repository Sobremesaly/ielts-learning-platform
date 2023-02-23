package com.example.preSpring.service.exam;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.TopicMapper;
import com.example.preSpring.pojo.Topic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    TopicMapper topicMapper;

    @Override
    public String getTopicByExamNum(String examNum) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper<Topic> query = new QueryWrapper<>();
        query.eq("exam_num", examNum);
        List<Topic> topicList = topicMapper.selectList(query);
        map.put("topicList", topicList);
        return JSON.toJSONString(map);
    }
}
