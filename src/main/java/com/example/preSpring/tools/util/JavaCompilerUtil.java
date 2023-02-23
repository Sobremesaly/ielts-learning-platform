package com.example.preSpring.tools.util;

import com.example.preSpring.tools.Javacompiler.JavaCompiler;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JavaCompilerUtil {
    private String jAVAFILEPATH = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    private String JAVAFILENAME = "";
    private String jAVAFILECLASNAME = "";


    public String getjAVAFILECLASNAME() {
        return jAVAFILECLASNAME;
    }

    public void setjAVAFILECLASNAME(String jAVAFILECLASNAME) {
        this.jAVAFILECLASNAME = jAVAFILECLASNAME;
    }


    public String getJAVAFILENAME() {
        return JAVAFILENAME;
    }

    public void setJAVAFILENAME(String JAVAFILENAME) {
        this.JAVAFILENAME = JAVAFILENAME;
    }


    public String compilerJava(String code, String args) {

        System.out.println("args:" + args);
        FileUtil util = new FileUtil();

        String pat = "(?<=class)([\\s\\S]+?)(?=\\{)";
        Pattern pattern = Pattern.compile(pat);
        Matcher m = pattern.matcher(code);
        String classname = "";
        int i = 0;
        while (m.find()) {
            classname = classname + m.group(i);
            i++;
        }
        System.out.println(classname);

        String[] classnames = classname.split("\\s+");


        setJAVAFILENAME(classnames[1] + ".java");
        setjAVAFILECLASNAME(classnames[1]);
        util.writeFile(code, jAVAFILEPATH + getJAVAFILENAME());
        System.out.println(getJAVAFILENAME());
        JavaCompiler javaComplier = new JavaCompiler(args, jAVAFILEPATH, getJAVAFILENAME(), getjAVAFILECLASNAME());
        javaComplier.complier();
        String proError = javaComplier.getErrorMsg();
        try {
            File file1 = new File(jAVAFILEPATH + getJAVAFILENAME());
            File file2 = new File(jAVAFILEPATH + getjAVAFILECLASNAME() + ".class");
            if (file1.delete() && file2.delete()) {
                System.out.println(file1.getName() + " 文件已被删除！\n" + file2.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!proError.equals("")) {
            return proError;

        } else {
            String str = javaComplier.getOutputData();//获取结果集
            return str;
        }

    }
}
