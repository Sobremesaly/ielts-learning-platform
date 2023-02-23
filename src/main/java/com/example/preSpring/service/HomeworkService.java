package com.example.preSpring.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 小叶子
 */
public interface HomeworkService {
    /**
     * 完成老师发布作业的功能
     *
     * @param homeworkTopic   作业标题
     * @param homeworkContent 作业内容
     * @param courseNum       课程号
     * @param teacherNum      老师工号
     * @return 返回创建结果
     */
    String teacherCreateHomework(String homeworkTopic, String homeworkContent, String courseNum, String teacherNum);

    /**
     * 完成老师上传work文件功能
     *
     * @param file work文件
     * @return 返回提交情况
     */
    String uploadHomeworkWord(MultipartFile file);

    /**
     * 根据课程号获取其发布的作业
     *
     * @param courseNum 课程号
     * @return 返回作业list
     */
    String getHomeworkByCourseNum(String courseNum);

    /**
     * 设置作业的截止时间
     *
     * @param homeworkNum 作业号
     * @param endTime     作业的截止时间
     * @return 返回设置结果 ，把更新后的作业集返回回去
     */
    String setWorkTime(String homeworkNum, String endTime);

    /**
     * 根据考试号获取考试
     *
     * @param homeworkNum 考试号
     * @return 返回考试实例
     */
    String getHomeworkByNum(String homeworkNum);
}
