package com.example.preSpring.service.register;

/**
 * @author 小叶子
 */
public interface TeacherRegisterService {
    /**
     * 完成老师登录
     * @param teacherId 老师工号
     * @param teacherPassword 用户密码
     * @param teacherPhoneNumber 用户手机号
     * @param code 验证码
     * @return 注册结果
     */
    String getTeacherRegister(String teacherId,String teacherPassword,String teacherPhoneNumber,String code);
}
