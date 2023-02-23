package com.example.preSpring.service.login;

/**
 * @author 小叶子
 */
public interface StudentLoginService {
    /**
     * 对登录结果进行数据处理
     * @param studentId 学生用户id
     * @param studentPassword 学生用户密码
     * @return 返回一个处理结果
     */
    String getStudentLogin(String studentId,String studentPassword);
}
