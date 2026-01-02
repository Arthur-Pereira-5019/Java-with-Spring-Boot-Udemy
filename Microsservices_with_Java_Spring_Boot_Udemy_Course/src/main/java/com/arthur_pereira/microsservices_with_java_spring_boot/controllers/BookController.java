package com.arthur_pereira.microsservices_with_java_spring_boot.controllers;

import com.arthur_pereira.microsservices_with_java_spring_boot.dto.BookDTO;
import com.arthur_pereira.microsservices_with_java_spring_boot.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/v1")
@Tag(name = "Book", description = "Endpoint for managing books")
public class BookController implements BookControllerDocs {

    @Autowired
    private BookServices service;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    @Override
    public BookDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @Override
    public List<BookDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public BookDTO create(@RequestBody BookDTO bookDTO) {
        return service.createBook(bookDTO);
    }

    @PutMapping(value = "",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public BookDTO update(@RequestBody BookDTO bookDTO) {
        return service.updateBook(bookDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
