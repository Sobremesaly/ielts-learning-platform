package com.example.preSpring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 小叶子
 */
@SpringBootApplication
@MapperScan("com.example.preSpring.dao")
public class PreSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(PreSpringApplication.class, args);
    }
}
