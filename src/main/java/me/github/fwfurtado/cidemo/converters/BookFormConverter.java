package me.github.fwfurtado.cidemo.converters;

import me.github.fwfurtado.cidemo.controllers.BookController;
import me.github.fwfurtado.cidemo.models.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookFormConverter implements Converter<BookController.BookForm, Book> {
    @Override
    public Book convert(BookController.BookForm bookForm) {
        return new Book(bookForm.getTitle(), bookForm.getDescription());
    }
}
