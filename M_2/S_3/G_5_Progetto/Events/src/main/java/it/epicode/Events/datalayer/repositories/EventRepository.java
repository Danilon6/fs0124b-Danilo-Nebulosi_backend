package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends
        JpaRepository<Event, Long>,
        PagingAndSortingRepository<Event, Long> {
}
