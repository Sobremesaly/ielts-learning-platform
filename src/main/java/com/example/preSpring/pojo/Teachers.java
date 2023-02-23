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

@TableName("t_teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Teachers {
  @TableId(type = IdType.AUTO, value = "id")
  private long id;
  private String teacherNum;
  private String teacherPhone;
  private String teacherPassword;
  private String teacherRemark;
  private String teacherPhoto;
  private String school;
  private String teacherName;
}
