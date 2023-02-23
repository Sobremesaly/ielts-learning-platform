package com.example.preSpring.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author 小叶子
 */
@TableName("t_test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @TableId(type = IdType.AUTO, value = "id")
    private long id;
    private String testNum;
    private String testPurpose;
    private String testTask;
    private String testStep;
    private String courseNum;
    private String courseName;
    private String testWord;
    private String testName;
    private java.sql.Timestamp testStartTime;
    private java.sql.Timestamp testEndTime;
}
