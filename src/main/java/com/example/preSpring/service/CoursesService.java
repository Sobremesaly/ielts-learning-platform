package com.example.preSpring.service;

import com.example.preSpring.pojo.Courses;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 小叶子
 */
public interface CoursesService {
    /**
     * 获取平台所有课程
     *
     * @return 返回一个List包含所有课程信息
     */
    String getAllCourses();

    /**
     * 根据输入混合检索，可以是课程号，课程名，课程专业
     *
     * @param input 课程号或者课程名
     * @return 返回符合的课程
     */
    String selectCourseBy(String input);

    /**
     * 根据课程专业检索
     *
     * @param courseMajor 输入的课程专业
     * @return 返回符合专业要求的课程
     */

    String selectCourse(String courseMajor);

    /**
     * 完成老师创建课程
     *
     * @param course 一个course对象，包含了创建一个课程的全部信息，详细见pojo中的类
     * @return 返回创建结果，可以是成功或者失败
     */
    String teacherCreateCourse(Courses course);

    /**
     * 完成对课程图片的上传，创建课程的实际操作是先写入各种信息最后才把图片路径写入
     *
     * @param file       文件类型，这里是图片类型
     * @param teacherNum 创建课程的老师的工号
     * @return 返回创建结果
     */
    String uploadCourseImg(MultipartFile file, String teacherNum);

    /**
     * 获得某个学生所加入的所有课程
     *
     * @param studentNum 学生的学号
     * @return 返回一个包含创建结果和课程list的JSON对象
     */
    String getStudentAllCourse(String studentNum);

    /**
     * 查看这名学生是否加入该课程
     *
     * @param studentNum 学生学号
     * @param courseNum  课程号
     * @return 返回是否加入
     */
    String checkStudentJoin(String studentNum, String courseNum);

    /**
     * 完成学生加入课程的功能，也就是向加入课程表插入一条数据并修改课程表让人数加一
     *
     * @param studentNum 学生学号
     * @param courseNum  课程号
     * @return 返回加入结果
     */
    String studentJoinCourse(String studentNum, String courseNum);

    /**
     * 根据课程号来获取课程信息
     *
     * @param courseNum 课程号
     * @return 返回课程号符合的课程
     */
    String getCourseByNum(String courseNum);
}
