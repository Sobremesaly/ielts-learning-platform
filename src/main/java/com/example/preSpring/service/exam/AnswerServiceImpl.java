package com.example.preSpring.service.exam;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.AnswerMapper;
import com.example.preSpring.pojo.Answer;
import com.example.preSpring.tools.util.CCompilerUtil;
import com.example.preSpring.tools.util.JavaCompilerUtil;
import com.example.preSpring.util.DegreeOfSimilarity;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private JavaCompilerUtil javaCompilerUtil;

    @Resource
    private CCompilerUtil cCompilerUtil;

    @Override
    public String verifyAnswer(String examNum, String topicNum, String answer) {
        HashMap<String, String> answerMap = new HashMap<>(1);
        QueryWrapper<Answer> query = new QueryWrapper<>();
        query.eq("exam_num", examNum)
                .eq("topic_num", topicNum);
        Answer confirm = answerMapper.selectOne(query);
        if (confirm.getAnswerContext().equals(answer)) {
            answerMap.put("flag", "right");
        } else {
            answerMap.put("flag", "false");
        }
        answerMap.put("answer", confirm.getAnswerContext());
        return JSON.toJSONString(answerMap);
    }

    @Override
    public String verifyAnswer(String examNum, String topicNum, String answer, String essay) {
        HashMap<String, Double> answerMap = new HashMap<>(1);
        QueryWrapper<Answer> query = new QueryWrapper<>();
        query.eq("exam_num", examNum)
                .eq("topic_num", topicNum);
        Answer confirm = answerMapper.selectOne(query);
        double degree = DegreeOfSimilarity.getDegree(answer, confirm.getAnswerContext());
        answerMap.put("degree", degree);
        return JSON.toJSONString(answerMap);
    }

    @Override
    public String verifyMultiply(String examNum, String topicNum, String answer) {
        HashMap<String, Object> answerMap = new HashMap<>(1);
        QueryWrapper<Answer> query = new QueryWrapper<>();
        query.eq("exam_num", examNum)
                .eq("topic_num", topicNum);
        /*多选题的答案有多个，先把答案对象保存在list中，然后取出来，做循环比较*/
        List<Answer> confirm = answerMapper.selectList(query);
        String[] answerList = answer.split(",");
        String[] rightAnswer = new String[confirm.size()];
        for (int i = 0; i < confirm.size(); i++) {
            rightAnswer[i] = confirm.get(i).getAnswerContext();
        }
        answerMap.put("answerList", rightAnswer);
        if (confirm.size() != answerList.length) {
            answerMap.put("flag", "false");
            return JSON.toJSONString(answerMap);
        }

        for (String value : rightAnswer) {
            if (!Arrays.asList(answerList).contains(value)) {
                answerMap.put("flag", "false");
                return JSON.toJSONString(answerMap);
            }
        }
        answerMap.put("flag", "right");
        return JSON.toJSONString(answerMap);
    }

    @Override
    public String verifyProgramme(String examNum, String topicNum, String answer, String type) {
        HashMap<String, Object> map = new HashMap<>(1);
        String javaType = "java(javac)";
        QueryWrapper<Answer> query = new QueryWrapper<>();
        query.eq("exam_num", examNum)
                .eq("topic_num", topicNum);
        /*测试用例和结果都被看做了答案，但是用例在前结果在后*/
        List<Answer> answerList = answerMapper.selectList(query);
        /*记录一下通过次数*/
        int rightNum = 0;
        /*循环步长应该是2，因为用例和结果是一对*/
        int step = 2;
        if (type.equals(javaType)) {
            for (int i = 0; i < answerList.size(); i = i + step) {
                String result = javaCompilerUtil.compilerJava(answer, answerList.get(i).getAnswerContext());
                if (answerList.get(i + 1).getAnswerContext().equals(result)) {
                    rightNum++;
                }
            }
        } else {
            for (int i = 0; i < answerList.size(); i = i + step) {
                String result = cCompilerUtil.CCompeiler(answer, answerList.get(i).getAnswerContext());
                if (answerList.get(i + 1).getAnswerContext().replaceAll("\\s*", "").equals(result.replaceAll("\\s*", ""))) {
                    rightNum++;
                }
            }
        }
        /*正确率就是正确次数除问题数目*/
        double accuracy = (double) rightNum / (double) answerList.size() * 2;
        map.put("accuracy", accuracy);
        return JSON.toJSONString(map);
    }
}
