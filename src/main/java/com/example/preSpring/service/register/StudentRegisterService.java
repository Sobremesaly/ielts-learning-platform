package com.example.preSpring.service.register;

/**
 * @author 小叶子
 */
public interface StudentRegisterService {
    /**
     * 完成学生的注册功能
     * @param studentId 学生学号
     * @param studentPassword 学生密码
     * @param studentPhoneNumber 学生手机号
     * @param code 短信验证码
     * @return 返回注册结果
     */
    String getStudentRegister(String studentId,String studentPassword,String studentPhoneNumber,String code);
}
