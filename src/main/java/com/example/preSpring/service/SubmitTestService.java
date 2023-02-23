package com.example.preSpring.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 小叶子
 */
public interface SubmitTestService {
    /**
     * 完成学生的实验上传
     *
     * @param studentNum    学生的学号
     * @param testNum       实验号
     * @param file          上传的word文件
     * @param submitContext 文本框中的输入
     * @return 返回提交结果
     */
    String sendTest(String studentNum, String testNum, MultipartFile file, String submitContext);
}
