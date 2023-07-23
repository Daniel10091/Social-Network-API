package com.yolo.domain.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.yolo.Yolo.domain.controller"})
@EntityScan("com.yolo.Yolo.entity.model")
@EnableJpaRepositories("com.yolo.Yolo.repository")
public class WebServiceApplication extends SpringBootServletInitializer {
  
}
