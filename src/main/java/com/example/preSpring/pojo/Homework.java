package com.example.preSpring.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小叶子
 */
@TableName("t_homework")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Homework {

    private long id;
    private String homeworkNum;
    private String homeworkTopic;
    private String homeworkContent;
    private java.sql.Timestamp homeworkStartTime;
    private java.sql.Timestamp homeworkEndTime;
    private String courseNum;
    private String teacherNum;
    private String homeworkWord;
}
