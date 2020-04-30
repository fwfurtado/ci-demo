package me.github.fwfurtado.cidemo.services;

import lombok.AllArgsConstructor;
import me.github.fwfurtado.cidemo.controllers.BookController.BookForm;
import me.github.fwfurtado.cidemo.converters.BookFormConverter;
import me.github.fwfurtado.cidemo.models.Book;
import me.github.fwfurtado.cidemo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookFormConverter formConverter;
    private final BookRepository repository;

    public Long register(BookForm form) {
        var book = formConverter.convert(form);

            repository.save(book);

        return book.getId();
    }

    public List<Book> listAll() {
        return repository.findAll();
    }
}
