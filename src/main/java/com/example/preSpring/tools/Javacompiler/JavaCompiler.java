package com.example.preSpring.tools.Javacompiler;

import java.io.*;
import java.nio.charset.Charset;

/**
 * java编译器
 *
 * @author tpw
 */
public class JavaCompiler {

    private String jAVAFILEPATH = "";
    private String JAVAFILENAME = "";
    private String jAVAFILECLASNAME = "";

    public String getjAVAFILEPATH() {
        return jAVAFILEPATH;
    }

    public String getJAVAFILENAME() {
        return JAVAFILENAME;
    }

    public String getjAVAFILECLASNAME() {
        return jAVAFILECLASNAME;
    }

    public void setjAVAFILECLASNAME(String jAVAFILECLASNAME) {
        this.jAVAFILECLASNAME = jAVAFILECLASNAME;
    }

    public void setJAVAFILENAME(String JAVAFILENAME) {
        this.JAVAFILENAME = JAVAFILENAME;
    }

    public void setjAVAFILEPATH(String jAVAFILEPATH) {
        this.jAVAFILEPATH = jAVAFILEPATH;
    }

    private String inputData; //输入数据
    private String outputData; //输出数据
    private String errorMsg; //程序错误信息

    public JavaCompiler() {
        this.inputData = "";
        this.errorMsg = "";
    }

    public JavaCompiler(String inputData, String jAVAFILEPATH, String JAVAFILENAME, String jAVAFILECLASNAME) {
        super();
        this.inputData = inputData;
        this.errorMsg = "";
        this.jAVAFILEPATH = jAVAFILEPATH;
        this.JAVAFILENAME = JAVAFILENAME;
        this.jAVAFILECLASNAME = jAVAFILECLASNAME;

    }

    public JavaCompiler(String inputData, String outputData, String errorMsg) {
        super();
        this.inputData = inputData;
        this.outputData = outputData;
        this.errorMsg = errorMsg;
    }

    public void complier() {
        // 进入java代码存放文件夹
        // 使用javac命令来编译java文件,来生成一个class文件
        // 使用java命令来运行类

        // cmd命令：cd /d d:\javaworkspace\complier\javaCode
        // cmd命令：javac Main.java
        // cmd命令：java Main

        StringBuffer errorInfo = new StringBuffer();
        Process p = null;
        try {
            //1.编译java文件
            p = Runtime.getRuntime().exec("javac " + this.JAVAFILENAME, null, new File(this.jAVAFILEPATH));
            // 获取进程的错误流
            final InputStream is1 = p.getErrorStream();
            // 开一个线程,读标准错误流
            new Thread() {
                public void run() {
                    try {
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(is1, Charset.forName("GBK")));
                        String line1 = null;
                        while ((line1 = br1.readLine()) != null) {
                            if (line1 != null) {
                                errorInfo.append(line1 + "\n");
                            }
                        }
                        if (!errorInfo.toString().equals("")) {
                            errorMsg = errorInfo.toString();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            is1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
            p.waitFor();
            p.destroy();
            //2.如果没错的话就运行java文件
            if (errorInfo.toString().equals("")) {
                try {
                    Process process = Runtime.getRuntime().exec("java " + this.jAVAFILECLASNAME, null, new File(this.jAVAFILEPATH));
                    BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
                    bout.write(this.inputData);
                    bout.close();
                    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
                    String line = null;
                    StringBuffer b = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        b.append(line + "\n");
                    }
                    this.outputData = b.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

