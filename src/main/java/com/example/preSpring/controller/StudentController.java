package com.example.preSpring.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.preSpring.dao.StudentMapper;
import com.example.preSpring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author 小叶子
 */
@RestController
public class StudentController {

    @Resource
    private StudentMapper studentMapper;
    @CrossOrigin
    @RequestMapping("/studentUploadAvatar")
    public String studentUploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String[] s = fileName.split("\\.");
        /*文件的存储路径和文件名*/
        String path = "../../../static/userAvatar/student/" + userId + "." + s[1];
        String paths = "D:\\myIdea\\pre-try\\static\\userAvatar\\student\\" + userId + "." + s[1];
        File desk = new File(paths);
        try {
            /*保存文件*/
            file.transferTo(desk);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = new HashMap<String, String>(1);
        map.put("path", path);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("student_num", userId);
        updateWrapper.set("student_photo", path);
        studentMapper.update(null,updateWrapper);
        return JSON.toJSONString(map);
    }

    @Resource
    StudentService studentService;
    @CrossOrigin
    @RequestMapping("/getStudentInformation")
    public String getStudentInformation(@RequestParam("userId") String studentId){
        return studentService.getStudentInformation(studentId);
    }
}
