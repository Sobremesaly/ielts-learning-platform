package com.example.preSpring.service.exam;

/**
 * @author 小叶子
 */
public interface TopicService {
    /**
     * 根据考试号获取题目
     *
     * @param examNum 考试号
     * @return 返回所有的题目
     */
    String getTopicByExamNum(String examNum);
}
