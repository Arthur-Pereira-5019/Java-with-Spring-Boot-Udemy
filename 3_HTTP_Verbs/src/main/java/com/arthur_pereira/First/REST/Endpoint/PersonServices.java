package com.arthur_pereira.First.REST.Endpoint;


import com.arthur_pereira.First.REST.Endpoint.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {


    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setAdress("SC");
        person.setFirstName("Arthur");
        person.setLastName("Pereira");
        person.setGender("M");
        return person;
    }
}
