package it.epicode.restapiblog.repositories;

import it.epicode.restapiblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
