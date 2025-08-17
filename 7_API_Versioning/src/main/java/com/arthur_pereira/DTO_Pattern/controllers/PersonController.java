package com.arthur_pereira.DTO_Pattern.controllers;

import com.arthur_pereira.DTO_Pattern.dto.PersonDTOV1;
import com.arthur_pereira.DTO_Pattern.dto.PersonDTOV2;
import com.arthur_pereira.DTO_Pattern.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV1 findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<PersonDTOV1> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV1 create(@RequestBody PersonDTOV1 PersonDTOV1) {
        return service.createPerson(PersonDTOV1);
    }

    @PutMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV1 update(@RequestBody PersonDTOV1 PersonDTOV1) {
        return service.updatePerson(PersonDTOV1);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping(value = "/v2/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 personDTOV2) {
        return service.createPersonV2(personDTOV2);
    }
}

