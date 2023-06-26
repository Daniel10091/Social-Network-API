package com.yolo.Yolo.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.yolo.Yolo.domain.entity.mapper.PersonMapper;
import com.yolo.Yolo.domain.repository.PersonRepository;

@Configuration
public class BeanConfig {

  @Bean
  PersonMapper personMapper() throws Exception {
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8080")
        .build();
    HttpServiceProxyFactory factory = HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(webClient)).build();
    return factory.createClient(PersonMapper.class);
  }

  @Bean
  PersonRepository personRepository() throws Exception {
    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8080")
        .build();
    HttpServiceProxyFactory factory = HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(webClient)).build();
    return factory.createClient(PersonRepository.class);
  }

}
