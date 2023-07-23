package com.yolo.domain.entity.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yolo.domain.entity.dto.UserDTO;
import com.yolo.domain.entity.model.User;

@Mapper
public interface UserMapper {
  
  @Mapping(source = "user.id", target = "code")
  static UserDTO toDto(User user) {
    throw new UnsupportedOperationException("Unimplemented method 'toDto'");
  }

  @InheritInverseConfiguration
  static User toEntiry(UserDTO userDto) {
    throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
  }
  
}
