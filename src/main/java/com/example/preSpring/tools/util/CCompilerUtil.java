package com.example.preSpring.tools.util;

import com.example.preSpring.tools.CCompiler.CComplier;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class CCompilerUtil {
    private final static String CplusplusFILEPATH = System.getProperty("user.dir") + "\\src\\main\\resources\\";
    private final static String CplusplusFILENAME = "main.c";
    private final static String CplusplusEXE = "main.exe";

    public String CCompeiler(String code, String args) {
        System.out.println("args:" + args);
        FileUtil util = new FileUtil();
        util.writeFile(code, CplusplusFILEPATH + CplusplusFILENAME);

        CComplier CComplier = new CComplier(args);
        CComplier.complier();
        String proError = CComplier.getErrorMsg();
        try {
            File file1 = new File(CplusplusFILEPATH + CplusplusFILENAME);
            File file2 = new File(CplusplusFILEPATH + CplusplusEXE);
            if (file1.delete() && file2.delete()) {
                System.out.println(file1.getName() + " 文件已被删除！\n" + file2.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!proError.equals("")) {

            System.err.println(proError);
            return proError;
        } else {
            System.out.println(CComplier.getOutputData());
            return CComplier.getOutputData();
        }

    }


}
