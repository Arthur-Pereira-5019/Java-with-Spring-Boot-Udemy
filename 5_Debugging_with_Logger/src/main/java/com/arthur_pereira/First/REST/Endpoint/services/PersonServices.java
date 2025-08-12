package com.arthur_pereira.First.REST.Endpoint.services;


import com.arthur_pereira.First.REST.Endpoint.exceptions.ResourceNotFoundException;
import com.arthur_pereira.First.REST.Endpoint.model.Person;
import com.arthur_pereira.First.REST.Endpoint.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {


    private final AtomicLong counter = new AtomicLong();
    @Autowired
    PersonRepository repository;
    private org.slf4j.Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());



    public List<Person> findAll() {
        logger.info("Finding all people");
        return repository.findAll();
    }

    private Person mockPerson(Integer i) {
        if (i == null) {
            i = 0;
        }
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setAdress("SC " + i);
        person.setFirstName("Arthur " + i);
        person.setLastName("Pereira " + i);
        person.setGender("M "+i );
        return person;
    }

    public Person createPerson(Person person) {
        logger.debug("Creating new person");
        return repository.save(person);
    }

    public Person updatePerson(Person person) {
        logger.info("Updated someone");
        Person entity = findById(person.getId());
        entity.setAdress(person.getAdress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        return repository.save(entity);
    }

    public void deletePerson(Long id) {
        logger.info("Deleted a person!");


        repository.deleteById(findById(id).getId());
    }

    public Person findById(Long id) {
        logger.debug("Finding one person!");
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find the user"));
    }
}
