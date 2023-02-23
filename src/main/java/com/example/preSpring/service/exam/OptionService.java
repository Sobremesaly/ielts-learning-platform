package com.example.preSpring.service.exam;

/**
 * @author 小叶子
 */
public interface OptionService {
    /**
     * 根据考试号获取题目选项
     *
     * @param examNum 考试号
     * @return 返回选项集
     */
    String getOptionByExamNum(String examNum);
}
