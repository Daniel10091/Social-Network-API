package com.yolo.Yolo.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yolo.Yolo.domain.entity.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  
  Optional<Person> findPersonById(Long id);

  @Query(" SELECT person FROM Person person " +
         " WHERE person.firstName LIKE '%:firstName%' " +
         " AND person.lastName LIKE '%:lastName%'")
  Optional<Person> findPersonByName(
    @Param("firstName") String firstName,
    @Param("lastName") String lastName
  );

  Optional<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);

}
