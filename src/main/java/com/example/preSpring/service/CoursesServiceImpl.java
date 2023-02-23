package com.example.preSpring.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.preSpring.dao.CourseMapper;
import com.example.preSpring.dao.JoinCourseMapper;
import com.example.preSpring.dao.TeacherMapper;
import com.example.preSpring.pojo.Courses;
import com.example.preSpring.pojo.JoinCourse;
import com.example.preSpring.pojo.Teachers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 小叶子
 */
@Service
public class CoursesServiceImpl implements CoursesService {
    @Resource
    CourseMapper courseMapper;

    @Override
    public String getAllCourses() {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            List<Courses> courses;
            courses = courseMapper.selectList(null);
            map.put("flag", "right");
            map.put("list", courses);
        } catch (Exception e) {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String selectCourseBy(String input) {
        QueryWrapper<Courses> wrapper = new QueryWrapper<>();
        wrapper.eq("course_name", input).or().eq("course_major", input).or().eq("course_num", input);
        List<Courses> list = courseMapper.selectList(wrapper);
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("list", list);
        System.out.println(list);
        return JSON.toJSONString(map);
    }

    @Override
    public String selectCourse(String courseMajor) {
        QueryWrapper<Courses> wrappers = new QueryWrapper<>();
        wrappers.eq("course_major", courseMajor);
        List<Courses> list = courseMapper.selectList(wrappers);
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("list", list);
        return JSON.toJSONString(map);
    }

    @Resource
    TeacherMapper teacherMapper;

    @Override
    public String teacherCreateCourse(Courses course) {
        HashMap<String, Object> map = new HashMap<>(1);
        try {
            QueryWrapper<Courses> wrappers = new QueryWrapper<>();
            int maxId = Math.toIntExact(courseMapper.selectOne(wrappers.orderByDesc("id").last("limit 1")).getId()) + 1;
            String id = "000" + maxId;
            course.setCourseNum(id);
            QueryWrapper query = new QueryWrapper();
            query.eq("teacher_num", course.getTeacherNum());
            Teachers teachers = teacherMapper.selectOne(query);
            course.setTeacherName(teachers.getTeacherName());
            courseMapper.insert(course);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String uploadCourseImg(MultipartFile file, String teacherNum) {
        HashMap<String, Object> map = new HashMap<>(1);
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String[] s = fileName.split("\\.");
        /*文件的存储路径和文件名*/
        QueryWrapper<Courses> wrappers = new QueryWrapper<>();
        int maxId = Math.toIntExact(courseMapper.selectOne(wrappers.orderByDesc("id").last("limit 1")).getId());
        String courseId = "000" + maxId;
        String path = "../../../static/course/" + courseId + "." + s[1];
        String paths = "D:\\myIdea\\pre-try\\static\\course\\" + courseId + "." + s[1];
        File desk = new File(paths);
        try {
            /*保存文件*/
            file.transferTo(desk);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("course_num", courseId);
            updateWrapper.set("course_picture", path);
            courseMapper.update(null, updateWrapper);
            QueryWrapper query = new QueryWrapper();
            query.eq("teacher_num", teacherNum);
            List<Courses> list = courseMapper.selectList(query);
            map.put("list", list);
            map.put("flag", "right");
        } catch (IOException e) {
            map.put("flag", "error");
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

    /*注入加入课程表的映射对象来辅助查询,有学号查询加入的课程号*/
    @Resource
    JoinCourseMapper joinCourseMapper;

    @Resource
    CourseMapper getCourseMapper;

    @Override
    public String getStudentAllCourse(String studentNum) {
        HashMap<String, Object> map = new HashMap<>(2);
        try {
            QueryWrapper<JoinCourse> query = new QueryWrapper<JoinCourse>();
            query.eq("student_num", studentNum);
            List<JoinCourse> joinList = joinCourseMapper.selectList(query);
            List<Courses> courseList = new ArrayList<>();
            for (JoinCourse joinCourse : joinList) {
                QueryWrapper<Courses> queries = new QueryWrapper<Courses>();
                queries.eq("course_num", joinCourse.getCourseNum());
                if (courseMapper.selectOne(queries) != null) {
                    courseList.add(courseMapper.selectOne(queries));
                }
            }
            map.put("list", courseList);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
        }

        return JSON.toJSONString(map);
    }

    @Override
    public String checkStudentJoin(String studentNum, String courseNum) {
        HashMap<String, Object> map = new HashMap<String, Object>(2);
        QueryWrapper<JoinCourse> query = new QueryWrapper<JoinCourse>();
        query.eq("student_num", studentNum);
        query.eq("course_num", courseNum);
        JoinCourse join = joinCourseMapper.selectOne(query);
        if (join != null) {
            map.put("join", "yes");
            return JSON.toJSONString(map);
        }
        map.put("join", "no");
        return JSON.toJSONString(map);
    }

    @Override
    public String studentJoinCourse(String studentNum, String courseNum) {
        HashMap<String, Object> map = new HashMap<String, Object>(2);
        try {
            JoinCourse joinCourse = new JoinCourse();
            joinCourse.setStudentNum(studentNum);
            joinCourse.setCourseNum(courseNum);
            joinCourseMapper.insert(joinCourse);
            UpdateWrapper<Courses> updateWrapper = new UpdateWrapper<Courses>();
            updateWrapper.eq("course_num", courseNum);
            updateWrapper.setSql("course_student_num = course_student_num + 1");
            courseMapper.update(null, updateWrapper);
            map.put("flag", "right");
        } catch (Exception e) {
            map.put("flag", "error");
        }
        return JSON.toJSONString(map);
    }

    @Override
    public String getCourseByNum(String courseNum) {
        HashMap<String, Object> map = new HashMap<String, Object>(2);
        QueryWrapper<Courses> query = new QueryWrapper<Courses>();
        query.eq("course_num", courseNum);
        Courses course = courseMapper.selectOne(query);
        map.put("course", course);
        map.put("flag", "right");
        return JSON.toJSONString(map);
    }
}
