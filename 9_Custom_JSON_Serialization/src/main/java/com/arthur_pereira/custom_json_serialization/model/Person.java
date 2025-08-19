package com.arthur_pereira.custom_json_serialization.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionId=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String firstName;
    @Column(length = 100, nullable = false)
    private String address;
    @Column(length = 100, nullable = false)
    private String lastName;
    @Column(length = 2, nullable = false)
    private String gender;
    @Column(nullable = true)
    private Date birthDay;

    public Person() {
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
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getGender(), person.getGender()) && Objects.equals(getBirthDay(), person.getBirthDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getAddress(), getLastName(), getGender(), getBirthDay());
    }
}
