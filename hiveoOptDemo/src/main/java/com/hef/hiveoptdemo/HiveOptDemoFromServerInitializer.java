package com.hef.hiveoptdemo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *  配置这个文件后就可以将打包后的war文件放到tomcat容器中启动了
 * @Date 2019-08-04
 * @Author lifei
 */
public class HiveOptDemoFromServerInitializer  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指定Spring配置
        return builder.sources(HiveoptdemoApplication.class);
    }
}
