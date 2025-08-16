package com.arthur_pereira.DTO_Pattern.controllers;

import com.arthur_pereira.DTO_Pattern.dto.PersonDTO;
import com.arthur_pereira.DTO_Pattern.services.PersonServices;
import com.arthur_pereira.DTO_Pattern.model.Person;
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
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO create(@RequestBody PersonDTO PersonDTO) {
        return service.createPerson(PersonDTO);
    }

    @PutMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@RequestBody PersonDTO PersonDTO) {
        return service.updatePerson(PersonDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
