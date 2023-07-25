// package com.yolo.domain.config.datasource;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.core.JdbcTemplate;

// @Configuration
// public class TopicDatasourceConfiguration {

//   @Bean
//   @ConfigurationProperties("spring.datasource.topics")
//   public DataSourceProperties topicsDataSourceProperties() {
//     return new DataSourceProperties();
//   }

//   @Bean
//   public DataSource topicsDataSource() {
//     return topicsDataSourceProperties()
//         .initializeDataSourceBuilder()
//         .build();
//   }

//   @Bean
//   public JdbcTemplate topicsJdbcTemplate(@Qualifier("topicsDataSource") DataSource dataSource) {
//     return new JdbcTemplate(dataSource);
//   }

// }
