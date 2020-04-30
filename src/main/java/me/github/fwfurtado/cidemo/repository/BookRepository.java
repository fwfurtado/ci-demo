package me.github.fwfurtado.cidemo.repository;

import me.github.fwfurtado.cidemo.models.Book;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookRepository extends Repository<Book, Long> {

    void save(Book book);

    List<Book> findAll();
}
