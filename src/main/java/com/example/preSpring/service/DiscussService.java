package com.example.preSpring.service;

import com.example.preSpring.pojo.Discuss;

import java.util.HashMap;

/**
 * @author 小叶子
 */
public interface DiscussService {
    /**
     * 根据课程号获取讨论信息
     *
     * @param courseNum 课程号
     * @return 返回讨论列表
     */
    HashMap<String, Object> getDiscuss(String courseNum);

    /**
     * 完成学生发布一个讨论
     *
     * @param discuss 讨论实体
     * @return 返回发布结果
     */
    HashMap<String, Object> insertDiscuss(Discuss discuss);
}
