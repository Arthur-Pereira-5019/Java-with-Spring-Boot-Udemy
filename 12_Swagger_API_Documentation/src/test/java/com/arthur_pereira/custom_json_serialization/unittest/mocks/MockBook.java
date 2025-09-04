package com.arthur_pereira.custom_json_serialization.unittest.mocks;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.arthur_pereira.custom_json_serialization.dto.BookDTO;
import com.arthur_pereira.custom_json_serialization.model.Book;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDTO> mockDTOList() {
        List<BookDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setAuthor("Author " + number);
        book.setPrice(number.floatValue());
        book.setLaunchDate(new Date(number,number,number));
        book.setTitle("Title " + number);
        return book;
    }

    public BookDTO mockDTO(Integer number) {
        BookDTO book = new BookDTO();
        book.setId(number.longValue());
        book.setAuthor("Author " + number);
        book.setPrice(number.floatValue());
        book.setLaunchDate(new Date(number,number,number));
        book.setTitle("Title " + number);
        return book;
    }

}