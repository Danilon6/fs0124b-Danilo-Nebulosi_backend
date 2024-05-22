package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Author;
import it.epicode.restapiblog.repositories.AuthorRepository;
import it.epicode.restapiblog.services.exceptions.DuplicateEmailException;
import it.epicode.restapiblog.services.exceptions.NotFoundException;
import it.epicode.restapiblog.utils.EntitiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository author;

    @Autowired
    EntitiesUtils utils;

    @Override
    public Author create(Author e) {
        var authorDuplicated = author.findByEmail(e.getEmail());

        if (authorDuplicated.isEmpty()) {
            e.setAvatar("https://ui-avatars.com/api/?name=" + e.getFirstName() + "+" + e.getLastName());
            return author.save(e);
        }else {
            throw new DuplicateEmailException(e.getEmail());
        }

    }

    @Override
    public Author update(Long id, Author e) {
        var a = author.findById(id).orElseThrow();
        utils.copy(e, a);
        return a;
    }

    @Override
    public void delete(Long id) {
        var founded = this.getById(id);
        author.delete(founded);
    }

    @Override
    public Author getById(Long id) {
        return author.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return author.findAll(pageable);
    }
}
