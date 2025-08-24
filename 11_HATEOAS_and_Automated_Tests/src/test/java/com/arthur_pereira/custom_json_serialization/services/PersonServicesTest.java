package com.arthur_pereira.custom_json_serialization.services;

import com.arthur_pereira.custom_json_serialization.model.Person;
import com.arthur_pereira.custom_json_serialization.repositories.PersonRepository;
import com.arthur_pereira.custom_json_serialization.unittest.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;


    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById((1L))).thenReturn(Optional.of(person));
        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/person/v1/1") &&
                link.getType().equals("DEL")));

        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/person/v1/all") &&
                link.getType().equals("GET")));

        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("POST")));

        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Address Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());



    }

    @Test
    void mock() {
    }

    @Test
    void createPerson() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void findAll() {
    }
}