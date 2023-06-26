package com.yolo.Yolo.domain.entity.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yolo.Yolo.domain.entity.dto.PersonDTO;
import com.yolo.Yolo.domain.entity.model.Person;

@Mapper
public interface PersonMapper {
  
  @Mapping(source = "person.id", target = "code")
  static PersonDTO toDto(Person person) {
    throw new UnsupportedOperationException("Unimplemented method 'toDto'");
  }

  @InheritInverseConfiguration
  static Person toEntity(PersonDTO personDTO) {
    throw new UnsupportedOperationException("Unimplemented method 'toEntity'");
  }

}
