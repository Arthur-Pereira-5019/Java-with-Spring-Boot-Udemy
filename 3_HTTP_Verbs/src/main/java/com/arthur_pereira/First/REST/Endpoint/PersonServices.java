package com.arthur_pereira.First.REST.Endpoint;


import com.arthur_pereira.First.REST.Endpoint.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {


    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());



    public ArrayList<Person> findAll() {
        logger.info("Finding all people");
        ArrayList<Person> persons = new ArrayList<Person>();

        for(int i = 0; i < 8; i++) {
            persons.add(mockPerson(i));
        }
        return persons;
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
        logger.info("Creating new person");
        return person;
    }

    public Person updatePerson(Person person) {
        logger.info("Updating a person");
        return person;
    }

    public void deletePerson(String id) {
        logger.info("Deleted a person!");
    }

    public Person findById(String id) {
        logger.info("Finding one person!");

        return mockPerson(0);
    }
}
