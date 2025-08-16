package com.arthur_pereira.DTO_Pattern.services;


import com.arthur_pereira.DTO_Pattern.dto.PersonDTO;
import com.arthur_pereira.DTO_Pattern.exceptions.ResourceNotFoundException;
import com.arthur_pereira.DTO_Pattern.mapper.ObjectMapper;
import com.arthur_pereira.DTO_Pattern.model.Person;
import com.arthur_pereira.DTO_Pattern.repositories.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.arthur_pereira.DTO_Pattern.mapper.ObjectMapper.parseListObjects;
import static com.arthur_pereira.DTO_Pattern.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {


    private final AtomicLong counter = new AtomicLong();
    @Autowired
    PersonRepository repository;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());



    public List<PersonDTO> findAll() {
        logger.info("Finding all people");
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }


    public PersonDTO createPerson(PersonDTO person) {
        logger.debug("Creating new person");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity),PersonDTO.class);
    }

    public PersonDTO updatePerson(PersonDTO person) {
        logger.info("Updated someone");
        Person entity = parseObject(findById(person.getId()), Person.class);
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public void deletePerson(Long id) {
        logger.info("Deleted a person!");
        repository.deleteById(findById(id).getId());
    }

    public PersonDTO findById(Long id) {
        logger.debug("Finding one person!");
        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find the user"));
        return parseObject(person, PersonDTO.class);
    }
}
