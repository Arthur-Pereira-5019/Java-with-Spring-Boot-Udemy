package com.arthur_pereira.microsservices_with_java_spring_boot.repositories;

import com.arthur_pereira.microsservices_with_java_spring_boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
