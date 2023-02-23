package com.example.preSpring.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小叶子
 */
@TableName("t_examination")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Examination {
    @TableId(type = IdType.AUTO, value = "id")
    private long id;
    private String examNum;
    private String examName;
    private String courseNum;
    private java.sql.Timestamp examStartTime;
    private java.sql.Timestamp examEndTime;
    private String teacherNum;
}
