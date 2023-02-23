package com.example.preSpring.service;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.DiscussMapper;
import com.example.preSpring.dao.ReplyMapper;
import com.example.preSpring.pojo.Discuss;
import com.example.preSpring.pojo.Reply;
import com.example.preSpring.pojo.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class DiscussServiceImpl implements DiscussService {

    @Resource
    private DiscussMapper discussMapper;

    @Resource
    private ReplyMapper replyMapper;

    @Override
    public HashMap<String, Object> getDiscuss(String courseNum) {
        HashMap<String, Object> data = new HashMap<>(1);
        QueryWrapper<Discuss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_num", courseNum);
        List<Discuss> discussList = discussMapper.selectList(queryWrapper);
        for (Discuss single : discussList) {
            QueryWrapper<Reply> queryWrap = new QueryWrapper<>();
            queryWrap.eq("discuss_num", single.getDiscussNum());
            List<Reply> list = replyMapper.selectList(queryWrap);
            single.setReply(list);
        }
        data.put("discussList", discussList);
        return data;
    }

    @Override
    public HashMap<String, Object> insertDiscuss(Discuss discuss) {
        HashMap<String, Object> data = new HashMap<>(1);
        discussMapper.insert(discuss);
        List<Discuss> discussList = discussMapper.selectList(null);
        for (Discuss single : discussList) {
            QueryWrapper<Reply> queryWrap = new QueryWrapper<>();
            queryWrap.eq("discuss_num", single.getDiscussNum());
            List<Reply> list = replyMapper.selectList(queryWrap);
            single.setReply(list);
        }
        data.put("discussList", discussList);
        return data;
    }
}
