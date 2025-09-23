package com.arthur_pereira.custom_json_serialization.services;

import com.arthur_pereira.custom_json_serialization.dto.BookDTO;
import com.arthur_pereira.custom_json_serialization.exceptions.RequiredObjectIsNullException;
import com.arthur_pereira.custom_json_serialization.model.Book;
import com.arthur_pereira.custom_json_serialization.model.Book;
import com.arthur_pereira.custom_json_serialization.repositories.BookRepository;
import com.arthur_pereira.custom_json_serialization.repositories.BookRepository;
import com.arthur_pereira.custom_json_serialization.unittest.mocks.MockBook;
import com.arthur_pereira.custom_json_serialization.unittest.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;
    
    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById((1L))).thenReturn(Optional.of(book));
        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/book/v1/1") &&
                link.getType().equals("DEL")));

        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/book/v1/all") &&
                link.getType().equals("GET")));

        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("POST")));

        assert(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Author 1",result.getAuthor());
        assertEquals("Title 1",result.getTitle());
        assertEquals(new Date(1,1,1),result.getLaunchDate());
        assertEquals(1F,result.getPrice());
    }

    @Test
    void mock() {
    }

    @Test
    void createBook() {
        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.save((book))).thenReturn(persisted);

        var result = service.createBook(dto);

        System.out.println(result.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/book/v1/1") &&
                link.getType().equals("DEL")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/book/v1/all") &&
                link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Author 1",result.getAuthor());
        assertEquals("Title 1",result.getTitle());
        assertEquals(new Date(1,1,1),result.getLaunchDate());
        assertEquals(1F,result.getPrice());
    }

    @Test
    void testCreateWithNulLException() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.createBook(null));

        String expectedMessage = "The book can't be null";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void updateBook() {
        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById((1L))).thenReturn(Optional.of(book));
        when(repository.save((book))).thenReturn(persisted);

        var result = service.updateBook(dto);

        System.out.println(result.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/book/v1/1") &&
                link.getType().equals("DEL")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/book/v1/all") &&
                link.getType().equals("GET")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("POST")));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Author 1",result.getAuthor());
        assertEquals("Title 1",result.getTitle());
        assertEquals(new Date(1,1,1),result.getLaunchDate());
        assertEquals(1F,result.getPrice());
    }

    @Test
    void testUpdateWithNulLException() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.updateBook(null));

        String expectedMessage = "The book can't be null";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void deleteBook() {
        Book book = input.mockEntity(1);
        book.setId(1L);
        when(repository.findById((1L))).thenReturn(Optional.of(book));
        var result = service.findById(1L);

        service.deleteById(1L);
        //verify(repository, times(1)).findById(anyLong());
        // verify(repository, times(1)).delete(any(Book.class));
        // verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<BookDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(14,people.size());

        var book1 = people.get(1);

        assertNotNull(book1);
        assertNotNull(book1.getId());
        assertNotNull(book1.getLinks());
        assert(book1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/book/v1/1") &&
                link.getType().equals("DEL")));

        assert(book1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/book/v1/all") &&
                link.getType().equals("GET")));

        assert(book1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("POST")));

        assert(book1.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Author 1",book1.getAuthor());
        assertEquals("Title 1",book1.getTitle());
        assertEquals(new Date(1,1,1),book1.getLaunchDate());
        assertEquals(1F,book1.getPrice());

        var book2 = people.get(4);

        assertNotNull(book2);
        assertNotNull(book2.getId());
        assertNotNull(book2.getLinks());
        assert(book2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/book/v1/4") &&
                link.getType().equals("DEL")));

        assert(book2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/book/v1/all") &&
                link.getType().equals("GET")));

        assert(book2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("POST")));

        assert(book2.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Author 4",book2.getAuthor());
        assertEquals("Title 4",book2.getTitle());
        assertEquals(new Date(4,4,4),book2.getLaunchDate());
        assertEquals(4F,book2.getPrice());

        var book3 = people.get(13);

        assertNotNull(book3);
        assertNotNull(book3.getId());
        assertNotNull(book3.getLinks());
        assert(book3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Delete") &&
                link.getHref().contains("/book/v1/13") &&
                link.getType().equals("DEL")));

        assert(book3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Find others") &&
                link.getHref().contains("/book/v1/all") &&
                link.getType().equals("GET")));

        assert(book3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Create another one") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("POST")));

        assert(book3.getLinks().stream().anyMatch(link -> link.getRel().value().equals("Update") &&
                link.getHref().contains("/book/v1") &&
                link.getType().equals("PUT")));

        assertEquals("Author 13",book3.getAuthor());
        assertEquals("Title 13",book3.getTitle());
        assertEquals(new Date(13,13,13),book3.getLaunchDate());
        assertEquals(13F,book3.getPrice());

    }
}