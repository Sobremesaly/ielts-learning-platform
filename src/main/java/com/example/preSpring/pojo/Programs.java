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
public class Programs {

  private long id;
  private String programNum;
  private String programName;
  private String program_Context;
  private String programRight;
  private String programInputFormat;
  private String programOutputFormat;
  private String program_InputSample;
  private String program_OutputSample;
  private String examNum;
  private String courseNum;
  private long programScore;

}
