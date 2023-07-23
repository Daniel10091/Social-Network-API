package com.yolo.domain.entity.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yolo.domain.entity.dto.PersonDTO;
import com.yolo.domain.entity.model.Person;

@Mapper
public interface PersonMapper {
  
  @Mapping(source = "person.id", target = "code")
  @Mapping(source = "person.user.avatar", target = "avatar")
  @Mapping(source = "person.user.background", target = "background")
  @Mapping(source = "person.user.username", target = "username")
  @Mapping(source = "person.user.enabled", target = "enabled")
  @Mapping(source = "person.user.password", target = "password")
  static PersonDTO toDto(Person person) {
    throw new UnsupportedOperationException("Unimplemented method 'toDto'");
  }

  @InheritInverseConfiguration
  static Person toEntity(PersonDTO personDTO) {
    throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
  }

}
