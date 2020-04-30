package me.github.fwfurtado.cidemo.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.github.fwfurtado.cidemo.models.Book;
import me.github.fwfurtado.cidemo.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@AllArgsConstructor
@RequestMapping("books")
public class BookController {

    private final BookService service;

    @PostMapping
    ResponseEntity<?> createBook(@RequestBody BookForm form) {
        var id = service.register(form);

        var uri = URI.create("/books/{id}").resolve(id.toString());

        return created(uri).build();
    }

    @GetMapping
    List<Book> list() {
        return service.listAll();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookForm {
        private String title;
        private String description;
    }
}
