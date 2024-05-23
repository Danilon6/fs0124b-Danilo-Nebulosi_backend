package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Post;
import it.epicode.restapiblog.services.dto.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface PostService {


    public Post create(PostDTO e); // Create

    //DOVREBBE RESITUIRE LA T PER CONSENITRE AL CONTROLLER DI AVER EUNA COPIA DI QUANTO è STATO FATTO
    public Post update(Long id, Post e); // Update

    //DOVREBBE RESITUIRE LA T PER FAR CAPIRE QUAL'ERA L'ELEMENTO CHE C'ERA PRIMA SUL DATABASE
    public Post delete(Long id); // Delete

    public Post getById(Long id); // Read

    public Page<Post> getAll(Pageable pageable);
}
