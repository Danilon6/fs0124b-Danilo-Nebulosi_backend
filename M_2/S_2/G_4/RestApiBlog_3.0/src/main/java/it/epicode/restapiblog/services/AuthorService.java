package it.epicode.restapiblog.services;


import it.epicode.restapiblog.entities.Author;
import it.epicode.restapiblog.services.dto.AuthorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService  {

    public Author create(AuthorDTO e); // Create

    //DOVREBBE RESITUIRE LA T PER CONSENITRE AL CONTROLLER DI AVER EUNA COPIA DI QUANTO è STATO FATTO
    public Author update(Long id, Author e); // Update

    //DOVREBBE RESITUIRE LA T PER FAR CAPIRE QUAL'ERA L'ELEMENTO CHE C'ERA PRIMA SUL DATABASE
    public Author delete(Long id); // Delete

    public Author getById(Long id); // Read

    public Page<Author> getAll(Pageable pageable);
}
