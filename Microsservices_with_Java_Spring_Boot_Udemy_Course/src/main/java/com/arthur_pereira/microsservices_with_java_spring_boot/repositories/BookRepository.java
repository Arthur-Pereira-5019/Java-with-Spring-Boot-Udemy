package com.arthur_pereira.microsservices_with_java_spring_boot.repositories;

import com.arthur_pereira.microsservices_with_java_spring_boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
