package com.example.preSpring.service;

import com.example.preSpring.pojo.Answer;
import com.example.preSpring.pojo.Option;
import com.example.preSpring.pojo.Topic;

import java.util.List;

/**
 * @author 小叶子
 */
public interface ExaminationService {
    /**
     * 根据下列信息创建信息不全的考试，后面后接着补充
     *
     * @param teacherNum 老师工号
     * @param examName   考试名
     * @param courseNum  课程号
     * @return 返回创建结果
     */
    String teacherCreateExamination(String teacherNum, String examName, String courseNum);

    /**
     * 返回创建考试结果
     *
     * @param topics  题目list
     * @param options 选项list
     * @param answers 答案list
     * @return 返回是否创建成功
     */
    String teacherSupplyExamination(List<Topic> topics, List<Option> options, List<Answer> answers);

    /**
     * 获取该课程下的所有考试
     *
     * @param courseNum 课程号
     * @return 返回一个考试list
     */
    String getExaminationByCourseNum(String courseNum);

    /**
     * 设置考试时间
     *
     * @param examNum 考试号
     * @param endTime 截止时间
     * @return 返回设置后考试结果
     */
    String setExamTime(String examNum, String endTime);


}
