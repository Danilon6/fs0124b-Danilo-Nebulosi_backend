package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Author;
import it.epicode.restapiblog.repositories.AuthorRepository;
import it.epicode.restapiblog.controllers.exceptions.DuplicateEmailException;
import it.epicode.restapiblog.controllers.exceptions.NotFoundException;
import it.epicode.restapiblog.services.dto.AuthorDTO;
import it.epicode.restapiblog.utils.EntitiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository author;

    @Autowired
    EntitiesUtils utils;

    @Override
    public Author create(AuthorDTO e) {
        var authorDuplicated = author.findByEmail(e.getEmail());
        if (authorDuplicated.isEmpty()) {
            var a = Author.builder()
                    .withFirstName(e.getFirstName())
                    .withLastName(e.getLastName())
                    .withEmail(e.getEmail())
                    .withBirthDate(e.getBirthDate())
                    .withAvatar("https://ui-avatars.com/api/?name=" + e.getFirstName() + "+" + e.getLastName())
                    .build();
            return author.save(a);
        }else {
            throw new DuplicateEmailException(e.getEmail());
        }

    }

    @Override
    public Author update(Long id, Author e) {
        var a = this.getById(id);
        utils.copy(e, a);
        return a;
    }

    @Override
    public Author delete(Long id) {
        var founded = this.getById(id);
        author.delete(founded);
        return founded;
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
