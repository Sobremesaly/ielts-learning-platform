package com.example.preSpring.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 小叶子
 */
public interface SubmitHomeworkService {
    /**
     * 完成作业的提交
     *
     * @param studentNum    学生学号
     * @param homeworkNum   作业号
     * @param file          上传的word文件
     * @param submitContext 文本框中的内容
     * @return 返回提交结果
     */
    String sendHomework(String studentNum, String homeworkNum, MultipartFile file, String submitContext);
}
