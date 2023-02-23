package com.example.preSpring.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.preSpring.dao.CourseMapper;
import com.example.preSpring.dao.TestMapper;
import com.example.preSpring.pojo.Courses;
import com.example.preSpring.pojo.Homework;
import com.example.preSpring.pojo.Test;
import com.example.preSpring.util.WordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    CourseMapper courseMapper;

    @Resource
    TestMapper testMapper;


    @Override
    public String teacherCreateTest(String courseNum, String testPurpose, String testTask, String testStep, String testEndTime, String testName) {
        Test test = new Test();
        /*把标准的日期时间转换成时间戳*/
        test.setTestEndTime(Timestamp.valueOf(testEndTime));
        test.setTestTask(testTask);
        test.setTestStep(testStep);
        test.setCourseNum(courseNum);
        test.setTestPurpose(testPurpose);
        /*根据课程号获取课程名*/
        QueryWrapper<Courses> query = new QueryWrapper<>();
        query.eq("course_num", courseNum);
        Courses course = courseMapper.selectOne(query);
        test.setCourseName(course.getCourseName());
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp start = Timestamp.valueOf(formatter.format(calendar.getTime()));
        test.setTestStartTime(start);
        QueryWrapper<Test> queryWrap = new QueryWrapper<>();
        /*获取最大的id+1来作为实验号*/
        int maxId = Math.toIntExact(testMapper.selectOne(queryWrap.orderByDesc("id").last("limit 1")).getId()) + 1;
        String testNum = "000" + maxId;
        test.setTestNum(testNum);
        /*借助自动生成word工具类来生成指定word*/
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("year", DateUtil.thisYear());
        map.put("month", DateUtil.thisMonth());
        map.put("day", DateUtil.thisDayOfMonth());
        map.put("className", course.getCourseName());
        map.put("testName", testName);
        map.put("testObjective", testPurpose);
        map.put("testTask", testTask);
        map.put("testProcedure", testStep);
        String fileName = "D:\\myIdea\\pre-try\\static\\testWord\\" + testNum + ".doc";
        String templateName = "南昌航空大学实验报.ftl";
        String templatePath = "D:\\myIdea\\pre-try\\static\\templateWord";
        String path = "../../../static/testWord/" + testNum + ".doc";
        test.setTestWord(path);
        test.setTestName(testName);
        testMapper.insert(test);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        try {
            WordUtil.generateWord(map, fileName, templateName, templatePath);
            hashMap.put("flag", "right");
        } catch (Exception e) {
            hashMap.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(hashMap);
    }

    @Override
    public String getTestByCourseNum(String courseNum) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            QueryWrapper<Test> query = new QueryWrapper<>();
            query.eq("course_num", courseNum);
            List<Test> list = testMapper.selectList(query);
            map.put("list", list);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String setTestTime(String testNum, String endTime) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            UpdateWrapper<Test> updateWrapper = new UpdateWrapper<Test>();
            updateWrapper.eq("test_num", testNum);
            Timestamp time = Timestamp.valueOf(endTime);
            updateWrapper.setSql("test_end_time = '" + time + "'");
            testMapper.update(null, updateWrapper);
            QueryWrapper<Test> query = new QueryWrapper<>();
            query.eq("test_num", testNum);
            List<Test> list = testMapper.selectList(query);
            map.put("list", list);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String getTestByNum(String testNum) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            QueryWrapper<Test> queryWrap = new QueryWrapper<Test>();
            queryWrap.eq("test_num", testNum);
            Test test = testMapper.selectOne(queryWrap);
            map.put("test", test);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }
}
