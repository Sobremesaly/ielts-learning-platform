package com.example.preSpring.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小叶子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Classes {

  private long id;
  private String classNum;
  private String teacherNum;

}
