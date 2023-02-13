package com.goodhang.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@SpringBootApplication
@MapperScan("com.goodhang.service.mapper")
public class CkwbUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CkwbUserServiceApplication.class,args);
    }
}
