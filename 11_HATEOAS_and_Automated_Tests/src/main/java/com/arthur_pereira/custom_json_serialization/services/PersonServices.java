package com.arthur_pereira.custom_json_serialization.services;


import com.arthur_pereira.custom_json_serialization.controllers.PersonController;
import com.arthur_pereira.custom_json_serialization.dto.PersonDTO;
import com.arthur_pereira.custom_json_serialization.exceptions.ResourceNotFoundException;
import com.arthur_pereira.custom_json_serialization.model.Person;
import com.arthur_pereira.custom_json_serialization.repositories.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Date;
import java.util.List;

import static com.arthur_pereira.custom_json_serialization.mapper.ObjectMapper.parseListObjects;
import static com.arthur_pereira.custom_json_serialization.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());


    public List<PersonDTO> findAll() {
        logger.info("Finding all people");
        var dto = parseListObjects(repository.findAll(), PersonDTO.class);
        for(var d: dto) {
            addLinks(d.getId(),d);
        }
        return dto;
    }

    public PersonDTO mock() {
        PersonDTO p = new PersonDTO();
        try {
            p.setAddress("Longe");
            p.setBirthDay(new Date(2008,02,20));
            p.setLastName("5019");
            p.setFirstName("Art");
            p.setPhoneNumber("40028922");
            p.setGender("M");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }


    public PersonDTO createPerson(PersonDTO person) {
        logger.debug("Creating new person");
        var entity = parseObject(person, Person.class);
        var dto = parseObject(repository.save(entity),PersonDTO.class);
        addLinks(dto.getId(), dto);
        return parseObject(repository.save(entity),PersonDTO.class);
    }

    public PersonDTO updatePerson(PersonDTO person) {
        logger.info("Updated someone");
        Person entity = parseObject(findById(person.getId()), Person.class);
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        var dto = parseObject(repository.save(entity), PersonDTO.class);
        addLinks(dto.getId(), dto);
        return dto;
    }

    public void deletePerson(Long id) {
        logger.info("Deleted a person!");
        repository.deleteById(findById(id).getId());
    }

    public PersonDTO findById(Long id) {
        logger.debug("Finding one person!");
        var person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find the user"));
        var dto = parseObject(person, PersonDTO.class);
        addLinks(id, dto);
        return dto;
    }

    private static void addLinks(Long id, PersonDTO dto) {
        // dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(id)).withRel("Delete").withType("DEL"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("Update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("Find others").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("Create another one").withType("POST"));
    }
}
