package com.example.preSpring.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 小叶子
 */
@TableName("t_discuss")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discuss {
    @TableId(type = IdType.AUTO, value = "id")
    private long id;
    private String discussNum;
    private java.sql.Timestamp discussDate;
    private String discussTitle;
    private String discussPhoto;
    private String discussContent;
    private String courseNum;
    private String userName;
    private List<Reply> reply;
}
