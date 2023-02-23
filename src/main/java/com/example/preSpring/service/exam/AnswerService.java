package com.example.preSpring.service.exam;

import java.util.List;

/**
 * @author 小叶子
 */

public interface AnswerService {
    /**
     * 验证学生提交的答案是否正确
     *
     * @param examNum  考试号
     * @param topicNum 题目号
     * @param answer   答案
     * @return 返回正确与否
     */
    String verifyAnswer(String examNum, String topicNum, String answer);

    /**
     * 匹配学生提交问答题的相似度
     *
     * @param examNum  考试号
     * @param topicNum 题目号
     * @param answer   答案
     * @param essay    问答题
     * @return 返回正确与否
     */
    String verifyAnswer(String examNum, String topicNum, String answer, String essay);

    /**
     * 验证多选题是否正确
     *
     * @param examNum  考试号
     * @param topicNum 题目号
     * @param answer   答案
     * @return 返回验证结果
     */
    String verifyMultiply(String examNum, String topicNum, String answer);

    /**
     * 验证下编程题对了没有
     *
     * @param examNum  考试号
     * @param topicNum 题目号
     * @param answer   答案
     * @param type     需要编译的语言
     * @return 返回结果正确与否
     */
    String verifyProgramme(String examNum, String topicNum, String answer, String type);
}
