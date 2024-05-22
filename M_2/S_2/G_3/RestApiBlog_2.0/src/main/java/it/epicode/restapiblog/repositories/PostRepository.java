package it.epicode.restapiblog.repositories;

import it.epicode.restapiblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends
        JpaRepository<Post, Long>,
        PagingAndSortingRepository<Post, Long> {

}