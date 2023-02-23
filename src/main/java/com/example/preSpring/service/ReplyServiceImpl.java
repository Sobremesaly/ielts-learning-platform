package com.example.preSpring.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.DiscussMapper;
import com.example.preSpring.dao.ReplyMapper;
import com.example.preSpring.pojo.Discuss;
import com.example.preSpring.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    @Resource
    private DiscussMapper discussMapper;

    @Override
    public HashMap<String, Object> insertDiscuss(Reply reply) {
        HashMap<String, Object> data = new HashMap<>(1);
        replyMapper.insert(reply);
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
