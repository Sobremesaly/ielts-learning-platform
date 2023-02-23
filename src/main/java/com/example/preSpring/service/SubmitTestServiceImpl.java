package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.SubmitTestMapper;
import com.example.preSpring.pojo.SubmitHomework;
import com.example.preSpring.pojo.SubmitTest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 小叶子
 */
@Service
public class SubmitTestServiceImpl implements SubmitTestService {

    @Resource
    SubmitTestMapper submitTestMapper;

    @Override
    public String sendTest(String studentNum, String testNum, MultipartFile file, String submitContext) {
        SubmitTest submitTest = new SubmitTest();
        submitContext = submitContext.replaceAll("<p>", "");
        submitContext = submitContext.replaceAll("</p>", "");
        submitTest.setStudentNum(studentNum);
        submitTest.setTestNum(testNum);
        HashMap<String, Object> map = new HashMap<>(1);
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String[] s = fileName.split("\\.");
        QueryWrapper<SubmitTest> query = new QueryWrapper<>();
        query.eq("test_num", testNum);
        submitTestMapper.delete(query);
        String submitPath = studentNum + testNum;
        /*文件的存储路径和文件名*/
        String path = "../../../static/submitTest/" + submitPath + "." + s[1];
        String paths = "D:\\myIdea\\pre-try\\static\\submitTest\\" + submitPath + "." + s[1];
        submitTest.setTestWord(path);
        submitTest.setSubmitContext(submitContext);
        File desk = new File(paths);
        try {
            /*保存文件*/
            file.transferTo(desk);
            submitTestMapper.insert(submitTest);
            map.put("flag", "right");
        } catch (IOException e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
