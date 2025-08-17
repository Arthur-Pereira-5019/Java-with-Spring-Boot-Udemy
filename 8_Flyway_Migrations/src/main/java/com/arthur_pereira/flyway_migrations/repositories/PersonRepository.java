package com.arthur_pereira.flyway_migrations.repositories;

import com.arthur_pereira.flyway_migrations.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
