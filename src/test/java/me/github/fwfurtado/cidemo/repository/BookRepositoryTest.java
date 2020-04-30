package me.github.fwfurtado.cidemo.repository;

import me.github.fwfurtado.cidemo.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddlAuto:create-drop", "DB_NAME=ci_test"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository repository;

    @Test
    void shouldSaveAnewBook() {

        var book = new Book("Spring in Action", "Some description");


        repository.save(book);

        var returnedBook = entityManager.find(Book.class, book.getId());

        assertEquals(book.getTitle(), returnedBook.getTitle());
        assertEquals(book.getDescription(), returnedBook.getDescription());

    }
}