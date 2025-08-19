package com.arthur_pereira.custom_json_serialization.dto;

import com.arthur_pereira.custom_json_serialization.serializer.GenderSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "gender", "first_name", "last_name", "adress"})
@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {
    private static final long serialVersionId=1L;

    private Long id;

    @JsonProperty("first_name")
    private String firstName;
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("last_name")
    private String lastName;
    @JsonSerialize(using = GenderSerializer.class)
    private String gender;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phoneNumber;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;

    public PersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getId(), personDTO.getId()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getAddress(), personDTO.getAddress()) && Objects.equals(getLastName(), personDTO.getLastName()) && Objects.equals(getGender(), personDTO.getGender()) && Objects.equals(getPhoneNumber(), personDTO.getPhoneNumber()) && Objects.equals(getBirthDay(), personDTO.getBirthDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getAddress(), getLastName(), getGender(), getPhoneNumber(), getBirthDay());
    }
}
