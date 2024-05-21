package it.epicode.restapiblog.controllers;

import it.epicode.restapiblog.entities.Author;
import it.epicode.restapiblog.entities.Post;
import it.epicode.restapiblog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    AuthorService author;


    @PostMapping ResponseEntity<Author> addAuthor(@RequestBody Author authorToSave) {
        var a = author.create(authorToSave);

        if (a == null)
            throw new RuntimeException("Error while saving new Post");
        return new ResponseEntity<Author>(a, null, HttpStatus.CREATED);
    }


    @GetMapping ResponseEntity<List<Author>> getAllAuthors() {
        var allAuthors = author.getAll();
        var headers = new HttpHeaders();
        headers.add("Total", String.valueOf(allAuthors.size()));
        return new ResponseEntity<>(allAuthors, headers, HttpStatus.OK);
    }

    @GetMapping ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        var a = author.getById(id);
            return a.map(value -> new ResponseEntity<>(value, null, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND));
    }

}
