package com.yolo.domain.entity.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
  
  private UUID code;
  private byte[] avatar;
  private byte[] background;
  private String username;
  private String salt;
  private String password;
  private Boolean online;
  private LocalDate createdDate;

}
