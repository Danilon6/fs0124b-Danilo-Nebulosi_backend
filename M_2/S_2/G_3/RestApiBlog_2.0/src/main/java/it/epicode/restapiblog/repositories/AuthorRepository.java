package it.epicode.restapiblog.repositories;

import it.epicode.restapiblog.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AuthorRepository extends
        JpaRepository<Author, Long>,
        PagingAndSortingRepository<Author, Long> {
    Optional<Author> findByEmail(String email);

}
