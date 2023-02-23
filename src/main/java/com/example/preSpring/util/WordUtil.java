package com.example.preSpring.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * @author 小叶子
 */
public class WordUtil {

    /**
     * 使用FreeMarker自动生成Word文档
     *
     * @param dataMap      生成Word文档所需要的数据
     * @param fileName     生成Word文档的全路径名称
     * @param templateName 模板文件
     * @param templatePath 模板路径
     */
    public static void generateWord(Map<String, Object> dataMap, String fileName, String templateName, String templatePath) throws Exception {
        // 设置FreeMarker的版本和编码格式
        //Configuration configuration = new Configuration(new Version("2.3.28"));
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        // 设置FreeMarker生成Word文档所需要的模板
        Template t = configuration.getTemplate(templateName, "UTF-8");
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8"));
        //FreeMarker使用Word模板和数据生成Word文档
        t.process(dataMap, out);
        out.flush();
        out.close();
    }
}