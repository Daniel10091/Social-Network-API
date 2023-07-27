package com.yolo.domain.service;

import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yolo.domain.entity.dto.PersonDTO;
import com.yolo.domain.entity.mapper.PersonMapper;
import com.yolo.domain.entity.model.Person;
import com.yolo.domain.exception.PersonAlreadyExistException;
import com.yolo.domain.exception.PersonNotFoundException;
import com.yolo.domain.repository.PersonRepository;
import com.yolo.domain.utils.PasswordUtil;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonService {

  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Person> getPeople() {
    return personRepository.findAll();
  }

  public Person findPersonById(Long id) {
    return personRepository.findPersonById(id)
        .orElseThrow(() -> new PersonNotFoundException("Pessoa com o id: " + id + " não foi encontrada"));
  }

  public Person registerPerson(PersonDTO personDTO) {
    Person saveReturn = null;
    Person newPerson = new Person();
    Person personExist = null;

    if (personDTO.getFirstName() != null && personDTO.getLastName() != null) {

      personExist = personRepository.findPersonByFirstNameAndLastName(
            personDTO.getFirstName(), 
            personDTO.getLastName()
          ).get();

      if (personExist == null) {
        newPerson = PersonMapper.toEntity(personDTO);

        byte[] salt = PasswordUtil.generateSalt();
        String encryptedPassword = PasswordUtil.encryptPassword(personDTO.getPassword().trim(), salt);

        newPerson.getUser().setPassword(encryptedPassword);
        newPerson.getUser().setSalt(Base64.getEncoder().encodeToString(salt));

        newPerson.getUser().setPerson(newPerson);
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

  public Person updatePerson(PersonDTO personDTO) {
    return null;
  }

  public void deletePerson(Long id) {
    personRepository.deleteById(id);
  }

}
