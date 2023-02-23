package com.example.preSpring.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.preSpring.dao.DiscussMapper;
import com.example.preSpring.pojo.Discuss;
import com.example.preSpring.pojo.Reply;
import com.example.preSpring.pojo.Students;
import com.example.preSpring.pojo.Teachers;
import com.example.preSpring.service.DiscussService;
import com.example.preSpring.service.ReplyService;
import com.example.preSpring.service.StudentService;
import com.example.preSpring.service.TeacherService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class DiscussController {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private DiscussService discussService;

    @Resource
    private ReplyService replyService;

    @Resource
    private DiscussMapper discussMapper;

    @RequestMapping(value = "/getDiscussByCourseNum")
    public String getDiscuss(@RequestParam("courseNum") String courseNum) {
        return JSON.toJSONString(discussService.getDiscuss(courseNum));
    }

    @RequestMapping(value = "/postDiscuss")
    public String insertDiscuss(MultipartFile file,
                                @RequestParam("courseNum") String courseNum,
                                @RequestParam("discussTitle") String discussTitle,
                                @RequestParam("userId") String userId,
                                @RequestParam("userType") String userType,
                                @RequestParam("discussContent") String discussContent) {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String[] s = fileName.split("\\.");
        QueryWrapper<Discuss> wrappers = new QueryWrapper<>();
        int maxId = Math.toIntExact(discussMapper.selectOne(wrappers.orderByDesc("id").last("limit 1")).getId()) + 1;
        String discussNum = "000" + maxId;
        /*文件的存储路径和文件名*/
        String path = "../../../static/discuss/" + discussNum + Calendar.getInstance().getTimeInMillis() + "." + s[1];
        String paths = "D:\\myIdea\\pre-try\\static\\discuss\\" + discussNum + Calendar.getInstance().getTimeInMillis() + "." + s[1];
        File desk = new File(paths);
        try {
            /*保存文件*/
            file.transferTo(desk);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Discuss discuss = new Discuss();
        discuss.setDiscussPhoto(path);
        discuss.setCourseNum(courseNum);
        discuss.setDiscussNum(discussNum);
        discuss.setDiscussContent(discussContent);
        discuss.setDiscussTitle(discussTitle);
        String studentType = "student";
        if (studentType.equals(userType)) {
            Map<String, Students> map = JSON.parseObject(studentService.getStudentInformation(userId), new TypeReference<Map<String, Students>>() {
            });
            Students student = map.get("user");
            discuss.setUserName(student.getStudentName());
        } else {
            Map<String, Teachers> map = JSON.parseObject(teacherService.getTeacherInformation(userId), new TypeReference<Map<String, Teachers>>() {
            });
            Teachers teacher = (Teachers) map.get("user");
            discuss.setUserName(teacher.getTeacherName());
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
        discuss.setDiscussDate(Timestamp.valueOf(date));
        return JSON.toJSONString(discussService.insertDiscuss(discuss));
    }


    @RequestMapping(value = "/postReply")
    public String insertReply(String userId, String replyContent, String discussNum, String userType) {

        Reply reply = new Reply();
        String studentType = "student";
        reply.setDiscussNum(discussNum);
        if (studentType.equals(userType)) {
            Map<String, Students> map = JSON.parseObject(studentService.getStudentInformation(userId), new TypeReference<Map<String, Students>>() {
            });
            Students student = map.get("user");
            reply.setUserName(student.getStudentName());
        } else {
            Map<String, Teachers> map = JSON.parseObject(teacherService.getTeacherInformation(userId), new TypeReference<Map<String, Teachers>>() {
            });
            Teachers teacher = (Teachers) map.get("user");
            reply.setUserName(teacher.getTeacherName());
        }
        reply.setReplyContent(replyContent);
        LocalDateTime localDateTime = LocalDateTime.now();
        String dates = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
        reply.setReplyDate(Timestamp.valueOf(dates));
        return JSON.toJSONString(replyService.insertDiscuss(reply));
    }

}



