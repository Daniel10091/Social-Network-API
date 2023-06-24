package com.yolo.yolo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yolo.yolo.domain.entity.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  
}
