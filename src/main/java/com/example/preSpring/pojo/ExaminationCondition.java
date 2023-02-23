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
@TableName("t_examination_condition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExaminationCondition {
    @TableId(type = IdType.AUTO, value = "id")
    private long id;
    private String examNum;
    private String studentNum;
    private long examScore;
    private String courseNum;
}
