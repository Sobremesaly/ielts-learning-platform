package com.example.preSpring;


import com.example.preSpring.util.WordUtil;
import com.sun.beans.decoder.DocumentHandler;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class PreSpringApplicationTests {

    @Test
    public void test02() throws IOException {
        String fileName = "D:\\myIdea\\pre-try\\static\\templateWord\\测试.doc";
        String templateName = "南昌航空大学实验报.ftl";
        String templatePath = "D:\\myIdea\\pre-try\\static\\templateWord";
        Map<String, Object> map = new HashMap<>();
        map.put("year", "sdsd");
        map.put("month", "122");
        map.put("day", "w");
        map.put("className", "wda");
        map.put("testName", "w");
        map.put("testObjective", "wd");
        map.put("testTask", "wda");
        map.put("testProcedure", "lo");
        try {
            WordUtil.generateWord(map, fileName, templateName, templatePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常");
        }
        System.out.println("生成成功");
    }


}
