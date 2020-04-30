package me.github.fwfurtado.cidemo.services;

import me.github.fwfurtado.cidemo.controllers.BookController;
import me.github.fwfurtado.cidemo.converters.BookFormConverter;
import me.github.fwfurtado.cidemo.models.Book;
import me.github.fwfurtado.cidemo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static me.github.fwfurtado.cidemo.controllers.BookController.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @Spy
    private BookFormConverter converter;

    @InjectMocks
    private BookService service;

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Test
    void shouldSaveANewBook() {
        var form = new BookForm("Spring In Action", "Another book of spring");

        service.register(form);

        then(repository).should(only()).save(bookArgumentCaptor.capture());
        then(converter).should(only()).convert(form);

        var book = bookArgumentCaptor.getValue();

        assertEquals("Spring In Action", book.getTitle());
        assertEquals("Another book of spring", book.getDescription());
    }

    @Test
    void shouldListAllBooks() {
        List<Book> allBooks = List.of(new Book("Spring in Action", "Some description"));
        given(repository.findAll()).willReturn(allBooks);

        var books = service.listAll();

        assertEquals(allBooks, books);
    }
}