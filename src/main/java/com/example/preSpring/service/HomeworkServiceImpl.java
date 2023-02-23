package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.preSpring.dao.HomeworkMapper;
import com.example.preSpring.pojo.Courses;
import com.example.preSpring.pojo.Examination;
import com.example.preSpring.pojo.Homework;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Resource
    HomeworkMapper homeworkMapper;

    @Override
    public String teacherCreateHomework(String homeworkTopic, String homeworkContent, String courseNum, String teacherNum) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            QueryWrapper<Homework> query = new QueryWrapper<Homework>();
            int maxId = Math.toIntExact(homeworkMapper.selectOne(query.orderByDesc("id").last("limit 1")).getId()) + 1;
            String homeworkNum = "000" + maxId;
            Homework homework = new Homework();
            homework.setTeacherNum(teacherNum);
            homework.setHomeworkTopic(homeworkTopic);
            homework.setCourseNum(courseNum);
            homework.setHomeworkNum(homeworkNum);
            homework.setHomeworkContent(homeworkContent);
            long now = System.currentTimeMillis();
            long after = now + 720000 * 5;
            Timestamp timestamp = new Timestamp(now);
            Timestamp nextTime = new Timestamp(after);
            homework.setHomeworkStartTime(timestamp);
            homework.setHomeworkEndTime(nextTime);
            homeworkMapper.insert(homework);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);

    }

    @Override
    public String uploadHomeworkWord(MultipartFile file) {
        HashMap<String, Object> map = new HashMap<>(1);
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String[] s = fileName.split("\\.");
        QueryWrapper<Homework> query = new QueryWrapper<>();
        int maxId = Math.toIntExact(homeworkMapper.selectOne(query.orderByDesc("id").last("limit 1")).getId());
        String homeworkNum = "000" + maxId;
        /*文件的存储路径和文件名*/
        String path = "../../../static/homework/" + homeworkNum + "." + s[1];
        String paths = "D:\\myIdea\\pre-try\\static\\homework\\" + homeworkNum + "." + s[1];
        File desk = new File(paths);
        try {
            /*保存文件*/
            file.transferTo(desk);
            UpdateWrapper<Homework> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("homework_num", homeworkNum);
            updateWrapper.set("homework_word", path);
            homeworkMapper.update(null, updateWrapper);
            map.put("flag", "right");
        } catch (IOException e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String getHomeworkByCourseNum(String courseNum) {
        HashMap<String, Object> map = new HashMap<>(1);
        QueryWrapper<Homework> query = new QueryWrapper<>();
        query.eq("course_num", courseNum);
        List<Homework> homeworkList = homeworkMapper.selectList(query);
        map.put("list", homeworkList);
        return JSON.toJSONString(map);
    }

    @Override
    public String setWorkTime(String homeworkNum, String endTime) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            UpdateWrapper<Homework> updateWrapper = new UpdateWrapper<Homework>();
            updateWrapper.eq("homework_num", homeworkNum);
            Timestamp time = Timestamp.valueOf(endTime);
            updateWrapper.setSql("homework_end_time = '" + time + "'");
            homeworkMapper.update(null, updateWrapper);
            QueryWrapper<Homework> query = new QueryWrapper<>();
            query.eq("homework_num", homeworkNum);
            List<Homework> list = homeworkMapper.selectList(query);
            map.put("list", list);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    @Override
    public String getHomeworkByNum(String homeworkNum) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            QueryWrapper<Homework> query = new QueryWrapper<>();
            query.eq("homework_num", homeworkNum);
            Homework homework = homeworkMapper.selectOne(query);
            map.put("homework", homework);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);
    }
}
