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


public class Selections {

  private long id;
  private String selectNum;
  private String selectOptions;
  private String selectRight;
  private String examNum;
  private String courseNum;
  private long selectScore;
  private String selectContext;

}
