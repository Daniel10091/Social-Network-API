package com.yolo.Yolo.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yolo.Yolo.domain.config.ServicesInterface.PersonService;
import com.yolo.Yolo.domain.entity.dto.PersonDTO;
import com.yolo.Yolo.domain.entity.mapper.PersonMapper;
import com.yolo.Yolo.domain.entity.model.Person;
import com.yolo.Yolo.domain.exception.PersonAlreadyExistException;
import com.yolo.Yolo.domain.exception.PersonNotFoundException;
import com.yolo.Yolo.domain.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

  private final PersonRepository personRepository;

  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public List<Person> getPeople() {
    return personRepository.findAll();
  }

  @Override
  public Person findPersonById(Long id) {
    return personRepository.findPersonById(id)
        .orElseThrow(() -> new PersonNotFoundException("Pessoa com o id: " + id + " não foi encontrada"));
  }

  @Override
  public Person registerPerson(PersonDTO personDTO) {
    Person saveReturn = null;
    Person newPerson = new Person();
    // Person personExist = null;

    if (personDTO.getFirstName() != null && personDTO.getLastName() != null) {
      // personExist = personRepository.findPersonByName(
      //     personDTO.getFirstName(), personDTO.getLastName())
      //     .orElseThrow(() -> new PersonNotFound(
      //         "Pessoa com o nome " + personDTO.getFirstName() + " " + personDTO.getLastName() + " não foi encontrada"));

      if (personRepository.findPersonByFirstNameAndLastName(
          personDTO.getFirstName(), personDTO.getLastName()) == null) {
        newPerson = PersonMapper.toEntity(personDTO);
        saveReturn = personRepository.save(newPerson);
      } else {
        throw new PersonAlreadyExistException(
            "A pessoa com o nome " + personDTO.getFirstName() + " " + personDTO.getLastName() + " já existe");
      }

    } else {
      throw new IllegalStateException("Não foi possível cadastar a pessoa");
    }

    return saveReturn;
  }

  @Override
  public Person updatePerson(PersonDTO personDTO) {
    return null;
  }

}
