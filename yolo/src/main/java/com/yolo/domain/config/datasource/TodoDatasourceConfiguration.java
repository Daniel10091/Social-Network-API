// package com.yolo.domain.config.datasource;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.core.JdbcTemplate;

// @Configuration
// public class TodoDatasourceConfiguration {

//   @Bean
//   @ConfigurationProperties("spring.datasource.todos")
//   public DataSourceProperties todosDataSourceProperties() {
//     return new DataSourceProperties();
//   }

//   @Bean
//   public DataSource todosDataSource() {
//     return todosDataSourceProperties()
//         .initializeDataSourceBuilder()
//         .build();
//   }

//   @Bean
//   public JdbcTemplate todosJdbcTemplate(@Qualifier("todosDataSource") DataSource dataSource) {
//     return new JdbcTemplate(dataSource);
//   }

// }
