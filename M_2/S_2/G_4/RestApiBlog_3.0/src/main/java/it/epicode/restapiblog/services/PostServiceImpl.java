package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Post;
import it.epicode.restapiblog.utils.EntitiesUtils;
import it.epicode.restapiblog.repositories.PostRepository;
import it.epicode.restapiblog.controllers.exceptions.NotFoundException;
import it.epicode.restapiblog.services.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository post;

    @Autowired
    AuthorService author;

    @Autowired
    EntitiesUtils utils;

    @Override
    public Post create(PostDTO e) {
        var a = author.getById(e.getAuthorId());
        return post.save(Post.builder()
                        .withTitle(e.getTitle())
                        .withContent(e.getContent())
                        .withCategory(e.getCategory())
                        .withReadingTime(e.getReadingTime())
                        .withCover(e.getCover())
                        .withAuthor(a)
                        .build());
    }

    @Override
    public Post update(Long id, Post e) {
        var p = this.getById(id);
        utils.copy(e, p);
        return p;
    }


    @Override
    public Post delete(Long id) {
        var founded = this.getById(id);
        post.delete(founded);
        return founded;
    }

    @Override
    public Post getById(Long id) {
        return post.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Page<Post> getAll(Pageable pageable) {
        return post.findAll(pageable);
    }
}
