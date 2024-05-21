package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Post;
import it.epicode.restapiblog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository post;

    @Override
    public Post create(Post e) {
        return post.save(e);
    }

    @Override
    public void update(Post e) {
        post.save(e);

    }

    @Override
    public void delete(Long id) {

        post.deleteById(id);
    }

    @Override
    public Optional<Post> getById(Long id) {
        return post.findById(id);
    }

    @Override
    public List<Post> getAll() {
        return post.findAll();
    }
}
