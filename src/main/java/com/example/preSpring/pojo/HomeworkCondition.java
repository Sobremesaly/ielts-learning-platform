package com.example.preSpring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小叶子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HomeworkCondition {

  private long id;
  private String courseNum;
  private String homeworkNum;
  private long homeworkStatus;
  private String studentNum;


}
