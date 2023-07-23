package com.yolo.domain.config.ServicesInterface;

import java.util.List;

import com.yolo.domain.entity.dto.PersonDTO;
import com.yolo.domain.entity.model.Person;

public interface PersonService {
  
  /**
   * @return
   */
  public List<Person> getPeople();

  /**
   * @param id
   * @return
   */
  public Person findPersonById(Long id);

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