package com.xinyet.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xinyet.mybatis.mapper")
public class MybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApp.class, args);
    }
}
