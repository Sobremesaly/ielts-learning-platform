package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.JoinCourseMapper;
import com.example.preSpring.dao.MessageMapper;
import com.example.preSpring.dao.TeacherMapper;
import com.example.preSpring.pojo.JoinCourse;
import com.example.preSpring.pojo.Message;
import com.example.preSpring.pojo.Teachers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    MessageMapper messageMapper;
    @Resource
    TeacherMapper teacherMapper;
    @Resource
    JoinCourseMapper joinCourseMapper;

    @Override
    public String teacherPostMessage(String courseNum, String teacherId, String timeNow, String messageTitle, String messageContext) {
        HashMap<String, String> map = new HashMap<String, String>(1);

        try {
            Message message = new Message();
            List<JoinCourse> list;
            message.setCourseNum(courseNum);
            message.setMessageTime(Timestamp.valueOf(timeNow));
            message.setMessageTitle(messageTitle);
            message.setMessageContext(messageContext);
            message.setTeacherNum(teacherId);
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("teacher_num", teacherId);
            Teachers teacher = teacherMapper.selectOne(wrapper);
            QueryWrapper wrappers = new QueryWrapper();
            wrappers.eq("course_num", courseNum);
            list = joinCourseMapper.selectList(wrappers);
            message.setTeacherName(teacher.getTeacherName());
            /*循环对表插入，因为要给每个在班级中的人发送信息*/
            for (JoinCourse joinCourse : list) {
                message.setStudentNum(joinCourse.getStudentNum());
                messageMapper.insert(message);
            }
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String getStudentMessage(String studentId) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            QueryWrapper query = new QueryWrapper();
            query.eq("student_num", studentId);
            List<Message> list = messageMapper.selectList(query);
            map.put("flag", "right");
            map.put("list", list);
        } catch (Exception e) {
            map.put("flag", "error");
        }

        return JSON.toJSONString(map);
    }
}
