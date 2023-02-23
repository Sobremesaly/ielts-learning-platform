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
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_message")
public class Message {
  @TableId(type = IdType.AUTO, value = "id")
  private long id;
  private String teacherNum;
  private String courseNum;
  private String messageTitle;
  private String messageContext;
  private java.sql.Timestamp messageTime;
  private String teacherName;
  private String studentNum;
}
