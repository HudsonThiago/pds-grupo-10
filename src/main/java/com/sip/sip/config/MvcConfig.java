package com.sip.sip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String rootPath = Paths.get("").toAbsolutePath().toString();
        registry.addResourceHandler("/imagens/**")
                .addResourceLocations("file:/" + rootPath + "/imagens/")
                .setCacheControl(CacheControl.noCache());
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/" + rootPath + "/upload/")
                .setCacheControl(CacheControl.noCache());
    }
}