package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.preSpring.dao.SubmitHomeworkMapper;
import com.example.preSpring.pojo.Homework;
import com.example.preSpring.pojo.SubmitHomework;
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
public class SubmitHomeworkServiceImpl implements SubmitHomeworkService {

    @Resource
    SubmitHomeworkMapper submitHomeworkMapper;

    @Override
    public String sendHomework(String studentNum, String homeworkNum, MultipartFile file, String submitContext) {
        SubmitHomework submitHomework = new SubmitHomework();
        submitContext = submitContext.replaceAll("<p>", "");
        submitContext = submitContext.replaceAll("</p>", "");
        submitHomework.setStudentNum(studentNum);
        submitHomework.setHomeworkNum(homeworkNum);
        HashMap<String, Object> map = new HashMap<>(1);
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String[] s = fileName.split("\\.");
        QueryWrapper<SubmitHomework> query = new QueryWrapper<>();
        query.eq("homework_num", homeworkNum);
        submitHomeworkMapper.delete(query);
        String submitPath = studentNum + homeworkNum;
        /*文件的存储路径和文件名*/
        String path = "../../../static/submitHomework/" + submitPath + "." + s[1];
        String paths = "D:\\myIdea\\pre-try\\static\\submitHomework\\" + submitPath + "." + s[1];
        submitHomework.setHomeworkWord(path);
        submitHomework.setSubmitContext(submitContext);
        File desk = new File(paths);
        try {
            /*保存文件*/
            file.transferTo(desk);
            submitHomeworkMapper.insert(submitHomework);
            map.put("flag", "right");
        } catch (IOException e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
