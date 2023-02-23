package com.example.preSpring.tools.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 小叶子
 */
public class FileUtil {

    public static void writeFile(String content, String filePath) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
