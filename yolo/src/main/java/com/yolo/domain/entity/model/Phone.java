package com.yolo.domain.entity.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Phone {
  
  @Id
  @Column(name = "id", nullable = false)
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;
  
  @Column(name = "email", nullable = false)
  private String phone;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime updatedDate;

}
