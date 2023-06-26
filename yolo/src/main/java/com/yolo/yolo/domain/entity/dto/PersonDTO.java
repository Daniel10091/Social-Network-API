package com.yolo.Yolo.domain.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonDTO {
  
  private Long code;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String gender;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

}
