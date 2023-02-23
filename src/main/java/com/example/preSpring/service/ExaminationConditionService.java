package com.example.preSpring.service;

/**
 * @author 小叶子
 */
public interface ExaminationConditionService {
    /**
     * 学生完成考试后需要提交
     *
     * @param examNum    考试号
     * @param studentNum 学号
     * @param examScore  考试分数
     * @param courseNum  课程号
     * @return 返回提交结果
     */
    String finishExam(String examNum, String studentNum, String examScore, String courseNum);

    /**
     * 根据学号和课程号获取考试情况，主要是分数
     *
     * @param courseNum  课程号
     * @param studentNum 学生学号
     * @return 返回考试情况list
     */
    String getExaminationConditionByCourseNum(String courseNum, String studentNum);
}
