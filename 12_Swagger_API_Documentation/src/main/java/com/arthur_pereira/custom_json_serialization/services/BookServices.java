package com.arthur_pereira.custom_json_serialization.services;

import com.arthur_pereira.custom_json_serialization.controllers.BookController;
import com.arthur_pereira.custom_json_serialization.controllers.PersonController;
import com.arthur_pereira.custom_json_serialization.dto.BookDTO;
import com.arthur_pereira.custom_json_serialization.dto.PersonDTO;
import com.arthur_pereira.custom_json_serialization.exceptions.ResourceNotFoundException;
import com.arthur_pereira.custom_json_serialization.mapper.ObjectMapper;
import com.arthur_pereira.custom_json_serialization.model.Book;
import com.arthur_pereira.custom_json_serialization.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.arthur_pereira.custom_json_serialization.mapper.ObjectMapper.parseObject;
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
        return parseObject(br.save(parseObject(book, Book.class)),BookDTO.class);
    }

    public BookDTO updateBook(BookDTO newBook) {
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
