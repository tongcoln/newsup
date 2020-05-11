package com.tt.newsup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author ：tt
 * @date ：Created in 2020/4/28 3:40 下午
 * @description：文件上传配置
 * @modified By：
 * @version:
 */
@Configuration
public class UploadFileConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/Users/mac/Documents/upload/"); //mac版本
        //registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/images/"); //windeows版本
        super.addResourceHandlers(registry);
    }

}