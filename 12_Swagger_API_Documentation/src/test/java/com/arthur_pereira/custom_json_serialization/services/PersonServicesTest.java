package com.arthur_pereira.custom_json_serialization.services;

import com.arthur_pereira.custom_json_serialization.dto.PersonDTO;
import com.arthur_pereira.custom_json_serialization.exceptions.RequiredObjectIsNullException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save((person))).thenReturn(persisted);

        var result = service.createPerson(dto);

        System.out.println(result.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/person/v1/1") &&
                link.getType().equals("DEL")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/person/v1/all") &&
                link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Address Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void testCreateWithNulLException() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.createPerson(null));

        String expectedMessage = "Is not allowed to persist an empty object!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void updatePerson() {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById((1L))).thenReturn(Optional.of(person));
        when(repository.save((person))).thenReturn(persisted);

        var result = service.updatePerson(dto);

        System.out.println(result.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/person/v1/1") &&
                link.getType().equals("DEL")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/person/v1/all") &&
                link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Address Test1",result.getAddress());
        assertEquals("First Name Test1",result.getFirstName());
        assertEquals("Last Name Test1",result.getLastName());
        assertEquals("Female",result.getGender());
    }

    @Test
    void testUpdateWithNulLException() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.updatePerson(null));

        String expectedMessage = "Is not allowed to persist an empty object!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void deletePerson() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        when(repository.findById((1L))).thenReturn(Optional.of(person));
        var result = service.findById(1L);

        service.deletePerson(1L);
        //verify(repository, times(1)).findById(anyLong());
        // verify(repository, times(1)).delete(any(Person.class));
        // verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(14,people.size());

        var person1 = people.get(1);

        assertNotNull(person1);
        assertNotNull(person1.getId());
        assertNotNull(person1.getLinks());
        assert(person1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/person/v1/1") &&
                link.getType().equals("DEL")));

        assert(person1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/person/v1/all") &&
                link.getType().equals("GET")));

        assert(person1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("POST")));

        assert(person1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Address Test1",person1.getAddress());
        assertEquals("First Name Test1",person1.getFirstName());
        assertEquals("Last Name Test1",person1.getLastName());
        assertEquals("Female",person1.getGender());

        var person2 = people.get(4);

        assertNotNull(person2);
        assertNotNull(person2.getId());
        assertNotNull(person2.getLinks());
        assert(person2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/person/v1/4") &&
                link.getType().equals("DEL")));

        assert(person2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/person/v1/all") &&
                link.getType().equals("GET")));

        assert(person2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("POST")));

        assert(person2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Address Test4", person2.getAddress());
        assertEquals("First Name Test4", person2.getFirstName());
        assertEquals("Last Name Test4", person2.getLastName());
        assertEquals("Male", person2.getGender());

        var person3 = people.get(13);

        assertNotNull(person3);
        assertNotNull(person3.getId());
        assertNotNull(person3.getLinks());
        assert(person3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/person/v1/13") &&
                link.getType().equals("DEL")));

        assert(person3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/person/v1/all") &&
                link.getType().equals("GET")));

        assert(person3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("POST")));

        assert(person3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/person/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Address Test13", person3.getAddress());
        assertEquals("First Name Test13", person3.getFirstName());
        assertEquals("Last Name Test13", person3.getLastName());
        assertEquals("Female", person3.getGender());

    }
}