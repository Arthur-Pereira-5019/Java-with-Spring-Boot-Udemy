package com.arthur_pereira.microsservices_with_java_spring_boot.repositories;

import com.arthur_pereira.microsservices_with_java_spring_boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person,Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id = :id")
    void disablePerson(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Person p SET p.enabled = true WHERE p.id = id")
    void enablePerson(@Param("id") Long id);
}
