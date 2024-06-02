package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long>,
        PagingAndSortingRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findOneByUsernameAndPassword(String username, String password);
    Optional<User> findOneByUsername(String username);
}
