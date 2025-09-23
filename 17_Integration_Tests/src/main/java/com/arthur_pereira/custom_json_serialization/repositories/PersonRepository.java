package com.arthur_pereira.custom_json_serialization.repositories;

import com.arthur_pereira.custom_json_serialization.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
