package com.arthur_pereira.microsservices_with_java_spring_boot.services;

import com.arthur_pereira.microsservices_with_java_spring_boot.controllers.BookController;
import com.arthur_pereira.microsservices_with_java_spring_boot.dto.BookDTO;
import com.arthur_pereira.microsservices_with_java_spring_boot.exceptions.RequiredObjectIsNullException;
import com.arthur_pereira.microsservices_with_java_spring_boot.exceptions.ResourceNotFoundException;
import com.arthur_pereira.microsservices_with_java_spring_boot.mapper.ObjectMapper;
import com.arthur_pereira.microsservices_with_java_spring_boot.model.Book;
import com.arthur_pereira.microsservices_with_java_spring_boot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.arthur_pereira.microsservices_with_java_spring_boot.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    @Autowired
    BookRepository br;

    public List<BookDTO> findAll() {
        List<BookDTO> list = ObjectMapper.parseListObjects(br.findAll(), BookDTO.class);
        list.forEach(dto -> addLinks(dto));
        return list;
    }

    public BookDTO findById(Long id) {
        BookDTO finded = parseObject(br.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found Exception")),BookDTO.class);
        addLinks(finded);
        return finded;
    }

    public BookDTO createBook(BookDTO book) {
        if(book == null) {
            throw new RequiredObjectIsNullException("The book can't be null");
        }
        return parseObject(br.save(parseObject(book, Book.class)),BookDTO.class);
    }

    public BookDTO updateBook(BookDTO newBook) {
        if(newBook == null) {
            throw new RequiredObjectIsNullException("The book can't be null");
        }
        BookDTO oldBook = findById(newBook.getId());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setTitle(newBook.getTitle());
        oldBook.setPrice(newBook.getPrice());
        oldBook.setLaunchDate(newBook.getLaunchDate());

        return parseObject(br.save(parseObject(newBook, Book.class)),BookDTO.class);
    }

    public void deleteById(Long id) {
        br.delete(parseObject(findById(id),Book.class));
    }

    private void addLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("Delete").withType("DEL"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("Update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("Find others").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("Create another one").withType("POST"));
    }

}
