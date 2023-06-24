package com.yolo.yolo.domain.entity.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yolo.yolo.domain.entity.dto.PersonDTO;
import com.yolo.yolo.domain.entity.model.Person;

@Mapper
public interface PersonMapper {
  
  @Mapping(source = "person.id", target = "code")
  PersonDTO toDto(Person person);

  @InheritInverseConfiguration
  Person toEntity(PersonDTO personDTO);

}
