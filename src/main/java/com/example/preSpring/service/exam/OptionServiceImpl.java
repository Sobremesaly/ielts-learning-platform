package com.example.preSpring.service.exam;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.OptionMapper;
import com.example.preSpring.pojo.Option;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class OptionServiceImpl implements OptionService {
    @Resource
    OptionMapper optionMapper;

    @Override
    public String getOptionByExamNum(String examNum) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper<Option> query = new QueryWrapper<>();
        query.eq("exam_num", examNum);
        List<Option> optionList = optionMapper.selectList(query);
        map.put("optionList", optionList);
        return JSON.toJSONString(map);
    }
}
