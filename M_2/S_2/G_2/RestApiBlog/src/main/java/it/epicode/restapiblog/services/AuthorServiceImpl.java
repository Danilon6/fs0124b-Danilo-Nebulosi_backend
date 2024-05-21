package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Author;
import it.epicode.restapiblog.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository author;

    @Override
    public Author create(Author e) {
        return author.save(e);
    }

    @Override
    public void update(Author e) {
        author.save(e);

    }

    @Override
    public void delete(Long id) {

        author.deleteById(id);
    }

    @Override
    public Optional<Author> getById(Long id) {

        return author.findById(id);
    }

    @Override
    public List<Author> getAll() {
        return author.findAll();
    }
}
