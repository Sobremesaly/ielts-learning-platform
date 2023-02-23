package com.example.preSpring.controller;

import com.example.preSpring.tools.util.CCompilerUtil;
import com.example.preSpring.tools.util.JavaCompilerUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 小叶子
 */
@RestController

public class CompileController {

    @Resource
    private JavaCompilerUtil javaCompilerUtil;
    @Resource
    private CCompilerUtil cCompilerUtil;

    @PostMapping("/CompilerRes")
    @CrossOrigin
    public String getJavaResult(@RequestBody Map<String, Object> map) {
        /*System.out.println((String) map.get("value"));*/
        if (map.get("type").equals("java(javac)")) {
            return javaCompilerUtil.compilerJava((String) map.get("code"), (String) map.get("args"));
        } else {
            return cCompilerUtil.CCompeiler((String) map.get("code"), (String) map.get("args"));
        }
    }
}
