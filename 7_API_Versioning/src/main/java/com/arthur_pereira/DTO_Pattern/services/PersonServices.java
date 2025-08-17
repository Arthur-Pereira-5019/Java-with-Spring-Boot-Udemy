package com.arthur_pereira.DTO_Pattern.services;


import com.arthur_pereira.DTO_Pattern.dto.PersonDTOV1;
import com.arthur_pereira.DTO_Pattern.dto.PersonDTOV2;
import com.arthur_pereira.DTO_Pattern.exceptions.ResourceNotFoundException;
import com.arthur_pereira.DTO_Pattern.mapper.PersonMapper;
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
    @Autowired
    PersonMapper converter;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());



    public List<PersonDTOV1> findAll() {
        logger.info("Finding all people");
        return parseListObjects(repository.findAll(), PersonDTOV1.class);
    }


    public PersonDTOV1 createPerson(PersonDTOV1 person) {
        logger.debug("Creating new person");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTOV1.class);
    }

    public PersonDTOV2 createPersonV2(PersonDTOV2 person) {
        logger.debug("Creating new v2 person!");
        var entity = converter.convertDTOtoEntity(person);
        return converter.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTOV1 updatePerson(PersonDTOV1 person) {
        logger.info("Updated someone");
        Person entity = parseObject(findById(person.getId()), Person.class);
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return parseObject(repository.save(entity), PersonDTOV1.class);
    }

    public void deletePerson(Long id) {
        logger.info("Deleted a person!");
        repository.deleteById(findById(id).getId());
    }

    public PersonDTOV1 findById(Long id) {
        logger.debug("Finding one person!");
        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find the user"));
        return parseObject(person, PersonDTOV1.class);
    }

}
