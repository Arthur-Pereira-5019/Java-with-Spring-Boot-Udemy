package com.arthur_pereira.DTO_Pattern.mapper;

import com.arthur_pereira.DTO_Pattern.dto.PersonDTOV2;
import com.arthur_pereira.DTO_Pattern.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 entity = new PersonDTOV2();

        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setBirthDate(new Date());
        entity.setId(person.getId());

        return entity;
    }

    public Person convertDTOtoEntity(PersonDTOV2 person) {
        Person entity = new Person();

        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        //entity.setBirthDate(new Date());
        entity.setId(person.getId());

        return entity;
    }
}
