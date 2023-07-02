package com.yolo.Yolo.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yolo.Yolo.domain.config.ServicesInterface.PersonService;
import com.yolo.Yolo.domain.entity.dto.PersonDTO;
import com.yolo.Yolo.domain.entity.mapper.PersonMapper;
import com.yolo.Yolo.domain.entity.model.Person;
import com.yolo.Yolo.domain.exception.PersonAlreadyExistException;
import com.yolo.Yolo.domain.exception.PersonNotFoundException;

import jakarta.annotation.security.RolesAllowed;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/test/login")
  public String loginEndpoint() {
    return "Login!";
  }

  @GetMapping("/test/admin")
  public String adminEndpoint() {
    return "Admin!";
  }

  @GetMapping("/test/user")
  public String userEndpoint() {
    return "User!";
  }

  @GetMapping("/test/all")
  public String allRolesEndpoint() {
    return "All Roles!";
  }

  @DeleteMapping("/test/delete")
  public String deleteEndpoint(@RequestBody String s) {
    return "I am deleting " + s;
  }

  @RolesAllowed("ROLE_USER")
  @GetMapping("/listAll")
  public ResponseEntity<List<PersonDTO>> getPeople() {
    List<Person> people = personService.getPeople();
    var result = people.stream().map(PersonMapper::toDto).collect(Collectors.toList());
    return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
  }

  @GetMapping("/find")
  public ResponseEntity<?> findPerson(@PathParam("id") Long id) throws Exception {
    try {
      Person person = personService.findPersonById(id);
      return ResponseEntity.ok(PersonMapper.toDto(person));
    } catch (PersonNotFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/register")
  public ResponseEntity<PersonDTO> registerPerson(@RequestBody PersonDTO personDTO) throws PersonAlreadyExistException {
    var newPerson = personService.registerPerson(personDTO);
    return ResponseEntity.ok(PersonMapper.toDto(newPerson));
  }

  @PutMapping("/update")
  public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) throws Exception {
    var updatedPerson = personService.updatePerson(personDTO);
    return ResponseEntity.ok(PersonMapper.toDto(updatedPerson));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deletePerson(@PathParam("id") Long id) throws Exception {
    try {
      personService.deletePerson(id);
      return ResponseEntity.ok().build();
    } catch (PersonNotFoundException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
