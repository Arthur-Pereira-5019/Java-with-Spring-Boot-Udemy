package com.arthur_pereira.custom_json_serialization.repositories;

import com.arthur_pereira.custom_json_serialization.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
