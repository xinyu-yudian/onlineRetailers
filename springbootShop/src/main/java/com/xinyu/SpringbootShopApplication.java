package com.xinyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xinyu.mapper")
public class SpringbootShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShopApplication.class, args);
    }

}
