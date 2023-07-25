// package com.yolo.domain.config.datasource;

// import java.util.Objects;

// import javax.sql.DataSource;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// import com.yolo.domain.entity.model.Person;

// @Configuration
// @EnableTransactionManagement
// @EnableJpaRepositories(
//   basePackageClasses = Person.class,
//   entityManagerFactoryRef = "todosEntityManagerFactory",
//   transactionManagerRef = "todosTransactionManager"
// )
// public class TodoJpaConfiguration {
  
//   @Bean
//   public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
//       @Qualifier("todosDataSource") DataSource dataSource,
//       EntityManagerFactoryBuilder builder) {
//     return builder
//         .dataSource(dataSource)
//         .packages(Person.class)
//         .build();
//   }

//   @Bean
//   public PlatformTransactionManager todosTransactionManager(
//       @Qualifier("todosEntityManagerFactory") LocalContainerEntityManagerFactoryBean todosEntityManagerFactory) {
//     return new JpaTransactionManager(Objects.requireNonNull(todosEntityManagerFactory.getObject()));
//   }

// }
