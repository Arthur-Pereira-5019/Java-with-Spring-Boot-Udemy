package com.arthur_pereira.DTO_Pattern.repositories;

import com.arthur_pereira.DTO_Pattern.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
