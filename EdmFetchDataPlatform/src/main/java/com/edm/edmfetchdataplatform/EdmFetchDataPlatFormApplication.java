package com.edm.edmfetchdataplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.edm.edmfetchdataplatform.mapper"})
public class EdmFetchDataPlatFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdmFetchDataPlatFormApplication.class, args);
    }

}
