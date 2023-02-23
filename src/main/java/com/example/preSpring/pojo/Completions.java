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
public class Completions {

  private long id;
  private String completionNum;
  private String completionContext;
  private String completionRights;
  private String examNum;
  private String courseNum;
  private long completionScore;

}
