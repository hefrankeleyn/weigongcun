package com.edm.edmfetchdataplatform;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *  配置这个文件后就可以将打包后的war文件放到tomcat容器中启动了
 * @Date 2019-05-15
 * @Author lifei
 */
public class EdmFetchDataPlatFormServerInitalizer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指定Spring配置
        return builder.sources(EdmFetchDataPlatFormApplication.class);
    }
}
