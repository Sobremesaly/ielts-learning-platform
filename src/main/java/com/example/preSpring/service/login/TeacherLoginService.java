package com.example.preSpring.service.login;

/**
 * @author 小叶子
 */
public interface TeacherLoginService {
    /**
     * 对登录结果进行数据处理
     * @param teacherId 老师用户工号
     * @param teacherPassword 老师用户密码
     * @return 返回一个处理结果
     */
    String getTeacherLogin(String teacherId, String teacherPassword);
}
