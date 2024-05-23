package it.epicode.restapiblog.controllers;

import it.epicode.restapiblog.controllers.exceptions.BadRequestException;
import it.epicode.restapiblog.controllers.models.PostRequest;
import it.epicode.restapiblog.entities.Post;
import it.epicode.restapiblog.services.PostService;
import it.epicode.restapiblog.services.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogPosts")
public class PostController {

    @Autowired
    PostService post;

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody @Validated PostRequest postToSave, BindingResult validator){
        if (validator.hasErrors()) {
            throw new BadRequestException(validator.getAllErrors());
        }

        var p = post.create(
                PostDTO.builder().
                        withTitle(postToSave.title()).
                        withCategory(postToSave.category())
                        .withContent(postToSave.content())
                        .withReadingTime(postToSave.readingTime())
                        .withAuthorId(postToSave.AuthorId())
                        .withCover(postToSave.cover())
                        .build()
        );
        return new ResponseEntity<Post>(p, null, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Page<Post>> getPosts (Pageable p) {
        var posts = post.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Total", String.valueOf(posts.getTotalElements()));
        return new ResponseEntity<>(posts, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost (@PathVariable Long id) {
        var p = post.getById(id);
        return  new ResponseEntity<>(p, null, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @RequestBody Post postModified) {
        var p = post.update(id, postModified);
        return new ResponseEntity<Post>(p, null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        var p = post.delete(id);
        return new ResponseEntity<Post>(p, null, HttpStatus.OK);

    }

}
