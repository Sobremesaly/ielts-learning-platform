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
@TableName("t_reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @TableId(type = IdType.AUTO, value = "id")
    private long id;
    private String replyNum;
    private java.sql.Timestamp replyDate;
    private String replyContent;
    private String userName;
    private String discussNum;
}
