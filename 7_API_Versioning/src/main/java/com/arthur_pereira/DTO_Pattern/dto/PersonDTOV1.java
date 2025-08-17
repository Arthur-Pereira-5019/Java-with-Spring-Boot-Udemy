package com.arthur_pereira.DTO_Pattern.dto;

import java.io.Serializable;
import java.util.Objects;

public class PersonDTOV1 implements Serializable {
    private static final long serialVersionId=1L;

    private Long id;

    private String firstName;
    private String address;
    private String lastName;
    private String gender;

    public PersonDTOV1() {
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTOV1 person)) return false;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getGender(), person.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getAddress(), getLastName(), getGender());
    }
}
