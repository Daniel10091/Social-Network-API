package com.yolo.domain.entity.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_people")
@SecondaryTable(name = "tbl_users", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Person {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private UUID id;
  
  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "age")
  private Integer age;

  @Column(name = "gender")
  private String gender;
  
  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime createdDate;
  
  @Column(name = "updated_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime updatedDate;

  @OneToOne(mappedBy = "tbl_people", cascade = CascadeType.ALL, optional = true)
  private User user;

  @OneToMany(mappedBy = "tbl_people", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Email> emails = new LinkedHashSet<>();

  @OneToMany(mappedBy = "tbl_people")
  private Set<Address> addresses = new LinkedHashSet<>();

  @OneToMany(mappedBy = "tbl_people")
  private Set<Phone> phones = new LinkedHashSet<>();

}
