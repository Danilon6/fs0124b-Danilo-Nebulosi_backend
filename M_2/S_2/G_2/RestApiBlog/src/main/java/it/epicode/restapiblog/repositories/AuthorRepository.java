package it.epicode.restapiblog.repositories;

import it.epicode.restapiblog.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
