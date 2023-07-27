package com.yolo.domain.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.yolo.domain.entity.dto.PersonDTO;
import com.yolo.domain.entity.model.Person;

public interface PersonServiceInterface {
  
  /**
   * @return
   */
  public List<Person> getPeople();

  /**
   * @param id
   * @return
   */
  public Optional<Person> findPersonById(Long id);

  /**
   * @param personDTO
   * @return
   */
  public Person registerPerson(PersonDTO personDTO);

  /**
   * @param personDTO
   * @return
   */
  public Person updatePerson(PersonDTO personDTO);

  /**
   * @param id
   * @return
   */
  public void deletePerson(Long id);

}
