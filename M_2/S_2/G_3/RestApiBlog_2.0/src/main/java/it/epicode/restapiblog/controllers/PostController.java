//package it.epicode.restapiblog.controllers;
//
//import it.epicode.restapiblog.entities.Post;
//import it.epicode.restapiblog.services.PostService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/blogPosts")
//public class PostController {
//
//    @Autowired
//    PostService post;
//
//    @PostMapping
//    public ResponseEntity<Post> addPost(@RequestBody Post postToSave){
//        var p = post.create(postToSave);
//        if (p == null)
//            throw  new RuntimeException("Error while saving new Post");
//        return new ResponseEntity<Post>(p, null, HttpStatus.CREATED);
//    }
//
//
//    @GetMapping
//    public ResponseEntity<Page<Post>> getPosts (Pageable p) {
//        var posts = post.getAll(p);
//        var headers = new HttpHeaders();
//        headers.add("Total", String.valueOf(posts.getTotalElements()));
//        return new ResponseEntity<>(posts, headers, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPost (@PathVariable Long id) {
//        var p = post.getById(id);
//        return p.map(value -> new ResponseEntity<>(value, null, HttpStatus.FOUND)).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Post> updatePost(
//            @PathVariable Long id,
//            @RequestBody Post postModified) {
//        var postToModify = post.getById(id);
//
//        if (postToModify.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        var ObjPost = postToModify.get();
//        ObjPost.setCategory(postModified.getCategory());
//        ObjPost.setCover(postModified.getCover());
//        ObjPost.setTitle(postModified.getTitle());
//        ObjPost.setContent(postModified.getContent());
//        ObjPost.setReadingTime(postModified.getReadingTime());
//
//        post.update(ObjPost);
//        return new ResponseEntity<Post>(ObjPost, null, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
//        var postToEliminate = post.getById(id);
//
//        if (postToEliminate.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        post.delete(id);
//        return new ResponseEntity<Post>(HttpStatus.NO_CONTENT);
//
//    }
//
//}
