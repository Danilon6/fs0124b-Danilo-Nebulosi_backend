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
@RequestMapping("/api/authors")
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

    @GetMapping("/{id}")
    ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        var a = author.getById(id);
            return a.map(value -> new ResponseEntity<>(value, null, HttpStatus.FOUND)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}") ResponseEntity<Author> updateAuthor(
            @PathVariable Long id,
            @RequestBody Author authorModified
    ) {
        var authorToModify = author.getById(id);

        if (authorToModify.isEmpty())
            return ResponseEntity.notFound().build();

        var ObjAuthor = authorToModify.get();

        ObjAuthor.setFirstName(authorModified.getFirstName());
        ObjAuthor.setLastName(authorModified.getLastName());
        ObjAuthor.setEmail(authorModified.getEmail());
        ObjAuthor.setBirthDate(authorModified.getBirthDate());
        ObjAuthor.setAvatar(authorModified.getAvatar());

        author.update(ObjAuthor);
        return new ResponseEntity<>(ObjAuthor, null, HttpStatus.OK);
    }


    @DeleteMapping("/{id}") ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
        var authorToEliminate = author.getById(id);

        if (authorToEliminate.isEmpty())
            return ResponseEntity.notFound().build();

        author.delete(id);
        return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
    }

}
