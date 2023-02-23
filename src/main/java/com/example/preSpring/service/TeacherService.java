package com.example.preSpring.service;

/**
 * @author 小叶子
 */
public interface TeacherService {
    /**
     * 根据老师工号获取其所有的信息
     * @param teacherId 老师的工号
     * @return 其数据库中所有信息
     */
    String getTeacherInformation(String teacherId);

    /**
     * 根据老师的工号获取其所创建的全部课程
     * @param teacherId 老师工号
     * @return 返回所有课程
     */
    String getTeacherAllCourse(String teacherId);
}
