package com.example.preSpring.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 小叶子
 */
@TableName("t_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courses {
  @TableId(type = IdType.AUTO, value = "id")
  private long id;
  private long courseStudentNum;
  private String courseNum;
  private String courseName;
  private String courseRemark;
  private long courseType;
  private Timestamp courseStartTime;
  private Timestamp courseEndTime;
  private String coursePicture;
  private String teacherNum;
  private String courseMajor;
  private String teacherName;
}
