package com.yolo.domain.entity.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "tbl_users")
public class User {
  
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @OneToOne(optional = true)
  @JoinColumn(name = "id")
  @MapsId
  private Person person;

  @Column(name = "avatar")
  private byte[] avatar;

  @Column(name = "background")
  private byte[] background;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "salt", nullable = false)
  private String salt;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime updatedDate;

}
