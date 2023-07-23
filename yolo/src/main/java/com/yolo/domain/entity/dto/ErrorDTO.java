package com.yolo.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDTO {
  
  private int status;
  private String title;
  private String detail;
  private String instance;

}
