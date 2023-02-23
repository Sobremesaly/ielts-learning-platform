package com.example.preSpring.service;

import com.example.preSpring.pojo.Reply;

import java.util.HashMap;

/**
 * @author 小叶子
 */
public interface ReplyService {
    /**
     * 完成学生发布一个回复
     *
     * @param reply 回复实体
     * @return 返回发布结果
     */
    HashMap<String, Object> insertDiscuss(Reply reply);
}
