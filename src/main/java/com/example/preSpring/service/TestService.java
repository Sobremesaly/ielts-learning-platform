package com.example.preSpring.service;

import com.example.preSpring.pojo.Test;

/**
 * @author 小叶子
 */
public interface TestService {

    /**
     * 完成老师对实验的创建
     *
     * @param courseNum   课程号
     * @param testPurpose 实验目的
     * @param testTask    实验任务
     * @param testStep    实验步骤
     * @param testEndTime 实验结束时间
     * @param testName    实验名称
     * @return 返回创建结果
     */
    String teacherCreateTest(String courseNum, String testPurpose, String testTask, String testStep, String testEndTime, String testName);

    /**
     * 根据课程号获取实验
     *
     * @param courseNum 课程号
     * @return 返回课程 list和获取结果，可能获取失败
     */
    String getTestByCourseNum(String courseNum);

    /**
     * 设置实验的截止时间
     *
     * @param testNum 实验号
     * @param endTime 实验截止时间
     * @return 返回更新后的实验和设置结果
     */
    String setTestTime(String testNum, String endTime);

    /**
     * 根据实验号获取实验
     *
     * @param testNum 实验号
     * @return 返回实验实体
     */
    String getTestByNum(String testNum);
}
