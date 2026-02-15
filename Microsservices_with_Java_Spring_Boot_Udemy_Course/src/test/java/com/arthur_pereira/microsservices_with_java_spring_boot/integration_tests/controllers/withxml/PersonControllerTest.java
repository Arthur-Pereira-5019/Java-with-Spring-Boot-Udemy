package com.arthur_pereira.microsservices_with_java_spring_boot.integration_tests.controllers.withxml;

import com.arthur_pereira.microsservices_with_java_spring_boot.config.TestConfigs;
import com.arthur_pereira.microsservices_with_java_spring_boot.integration_tests.converter.YAMLMapper;
import com.arthur_pereira.microsservices_with_java_spring_boot.integration_tests.dtos.PersonDTO;
import com.arthur_pereira.microsservices_with_java_spring_boot.integration_tests.test_containers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static XmlMapper objectMapper;

    private static PersonDTO person;
    @BeforeAll
    static void setUp() {
        objectMapper = new XmlMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonDTO();
    }

    @Test
    @Order(1)
    void create() throws JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder().
                addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_LOCAL)
                .setBasePath("person/v1")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given(specification).
                contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
        person=createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Zelio",createdPerson.getFirstName());
        assertEquals("Moraes",createdPerson.getLastName());
        assertEquals("Rio de Janeiro",createdPerson.getAddress());
        assertEquals("M",createdPerson.getGender());
        assertTrue(createdPerson.isEnabled());
    }

    private void mockPerson() {
        person.setFirstName("Zelio");
        person.setLastName("Moraes");
        person.setAddress("Rio de Janeiro");
        person.setGender("Male");
        person.setEnabled(true);
    }

    @Test
    void mock() {
    }

    @Test
    @Order(2)
    void findById() throws JsonProcessingException {
        var content = given(specification).
                contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
        person=createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Zelio",createdPerson.getFirstName());
        assertEquals("Moraes",createdPerson.getLastName());
        assertEquals("Rio de Janeiro",createdPerson.getAddress());
        assertEquals("M",createdPerson.getGender());
        assertTrue(createdPerson.isEnabled());
    }

    @Test
    @Order(3)
    void update() throws JsonProcessingException {
        person.setLastName("Ferandino de Moraes");
        var content = given(specification).
                contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)

                .body(person)
                .when()
                .put()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
        person=createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Zelio",createdPerson.getFirstName());
        assertEquals("Ferandino de Moraes",createdPerson.getLastName());
        assertEquals("Rio de Janeiro",createdPerson.getAddress());
        assertEquals("M",createdPerson.getGender());
        assertTrue(createdPerson.isEnabled());
    }

    @Test
    @Order(4)
    void findByIdAfterUpdate() throws JsonProcessingException {
        var content = given(specification).
                contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
        person=createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Zelio",createdPerson.getFirstName());
        assertEquals("Ferandino de Moraes",createdPerson.getLastName());
        assertEquals("Rio de Janeiro",createdPerson.getAddress());
        assertEquals("M",createdPerson.getGender());
        assertTrue(createdPerson.isEnabled());
    }

    @Test
    @Order(5)
    void disableTest() throws JsonProcessingException {
        var content = given(specification).
                contentType(MediaType.APPLICATION_XML_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
                .pathParam("id", person.getId())
                .when()
                .patch("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_XML_VALUE)
                .extract()
                .body()
                .asString();

        PersonDTO createdPerson = objectMapper.readValue(content, PersonDTO.class);
        person=createdPerson;
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Zelio",createdPerson.getFirstName());
        assertEquals("Ferandino de Moraes",createdPerson.getLastName());
        assertEquals("Rio de Janeiro",createdPerson.getAddress());
        assertEquals("M",createdPerson.getGender());
        assertFalse(createdPerson.isEnabled());
    }

    @Test
    @Order(8)
    void delete() throws JsonProcessingException {
        var content = given(specification).
                contentType(MediaType.ALL_VALUE)
                .pathParam("id", person.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(7)
    void findAll() throws JsonProcessingException {
        var content = given(specification).
                when()
                .get("/all")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        List<PersonDTO> people = objectMapper.readValue(content, new TypeReference<List<PersonDTO>>() {});


        PersonDTO personOne = people.getFirst();
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getFirstName());
        assertNotNull(personOne.getLastName());
        assertNotNull(personOne.getAddress());
        assertNotNull(personOne.getGender());
        assertTrue(personOne.getId() > 0);

        assertEquals("Zelio",personOne.getFirstName());
        assertEquals("Ferandino de Moraes",personOne.getLastName());
        assertEquals("Rio de Janeiro",personOne.getAddress());
        assertEquals("M",personOne.getGender());
        assertFalse(personOne.isEnabled());
    }
}
