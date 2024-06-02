package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.Event;
import it.epicode.Events.datalayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EventRepository extends
        JpaRepository<Event, Long>,
        PagingAndSortingRepository<Event, Long> {
    Optional<Event> findOneByTitle(String title);
}
