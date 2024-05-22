package it.epicode.restapiblog.controllers;

import it.epicode.restapiblog.entities.Author;

import it.epicode.restapiblog.services.AuthorService;
import it.epicode.restapiblog.services.exceptions.DuplicateEmailException;
import it.epicode.restapiblog.services.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/authors")
@Slf4j
public class AuthorController {

    @Autowired
    AuthorService author;


    @PostMapping ResponseEntity<Author> addAuthor(@RequestBody Author authorToSave) {
        var a = author.create(authorToSave);
        return new ResponseEntity<Author>(a, null, HttpStatus.CREATED);
    }


    @GetMapping ResponseEntity<Page<Author>> getAllAuthors(Pageable p) {
        var allAuthors = author.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Total", String.valueOf(allAuthors.getTotalElements()));
        return new ResponseEntity<>(allAuthors, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        var a = author.getById(id);
        return  new ResponseEntity<>(a, null, HttpStatus.FOUND);
    }

    @PutMapping("/{id}") ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @RequestBody Author authorModified
    ) {
            var a = author.update(id, authorModified);
            return new ResponseEntity<>(a, null, HttpStatus.OK);
    }


    @DeleteMapping("/{id}") ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
            author.delete(id);
            return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
    }

}
