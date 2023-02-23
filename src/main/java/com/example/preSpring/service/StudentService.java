package com.example.preSpring.service;

/**
 * @author 小叶子
 */
public interface StudentService {
    /**
     * 根据学生的学号获取其个人信息
     *
     * @param studentId 学号
     * @return 返回其个人信息
     */
    String getStudentInformation(String studentId);
}
