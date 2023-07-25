package com.yolo.domain.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.yolo.domain.entity.model.Person;

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
  private Integer age;
  private String gender;
  private byte[] avatar;
  private byte[] background;
  private String username;
  private String password;
  private Boolean enabled = true;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  public PersonDTO(Person person) {
    this.code = person.getId();
    this.firstName = person.getFirstName();
    this.lastName = person.getLastName();
    this.birthDate = person.getBirthDate();
    this.age = person.getAge();
    this.gender = person.getGender();
    this.avatar = person.getUser().getAvatar();
    this.background = person.getUser().getBackground();
    this.username = person.getUser().getUsername();
    this.password = person.getUser().getPassword();
    this.enabled = person.getUser().getEnabled();
  }

}
