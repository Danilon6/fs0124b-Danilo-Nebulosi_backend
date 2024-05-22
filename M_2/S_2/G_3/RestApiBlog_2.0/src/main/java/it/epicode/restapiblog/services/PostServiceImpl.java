//package it.epicode.restapiblog.services;
//
//import it.epicode.restapiblog.entities.Post;
//import it.epicode.restapiblog.repositories.PostRepository;
//import it.epicode.restapiblog.services.exceptions.NotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class PostServiceImpl implements PostService {
//
//    @Autowired
//    PostRepository post;
//
//    @Override
//    public Post create(Post e) {
//        return post.save(e);
//    }
//
//    @Override
//    public Post update(Post e) {
//        post.save(e);
//
//    }
//
//    @Override
//    public void delete(Long id) {
//
//        post.deleteById(id);
//    }
//
//    @Override
//    public Post getById(Long id) {
//        return post.findById(id).orElseThrow(()-> new NotFoundException(id));
//    }
//
//    @Override
//    public Page<Post> getAll(Pageable pageable) {
//        return post.findAll(pageable);
//    }
//}
