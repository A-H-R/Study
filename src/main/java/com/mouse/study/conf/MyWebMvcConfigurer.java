package com.mouse.study.conf;
/*
 *  created by mouse on 2020/2/29
 *  文件上传配置文件，定义本地资源文件夹
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:F:/project/study/src/main/resources/static/images/");
    }
}
