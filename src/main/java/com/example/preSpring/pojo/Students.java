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

@TableName("t_students")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Students {
  @TableId(type = IdType.AUTO, value = "id")
  private long id;
  private String studentNum;
  private String classNum;
  private String studentName;
  private String studentPhoneNum;
  private String studentPassword;
  private String studentPhoto;
  private String studentRemark;
}
