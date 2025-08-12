package com.arthur_pereira.First.REST.Endpoint.repositories;

import com.arthur_pereira.First.REST.Endpoint.model.Person;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
