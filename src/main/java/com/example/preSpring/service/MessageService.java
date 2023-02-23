package com.example.preSpring.service;

/**
 * @author 小叶子
 */
public interface MessageService {
    /**
     * 完成发送通知功能,也就是对信息表进行插入操作
     * @param courseNum 课程号
     * @param teacherId 发送通知的老师工号
     * @param timeNow 发送的时间
     * @param messageTitle 通知标题
     * @param messageContext 通知内容
     * @return 返回发送是否成功
     */
    String teacherPostMessage(String courseNum,String teacherId,String timeNow, String messageTitle,String messageContext);

    /**
     * 根据学生学号获取其收信箱里的内容
     * @param studentId 学生学号
     * @return 返回收信箱中的信息
     */
    String getStudentMessage(String studentId);
}
