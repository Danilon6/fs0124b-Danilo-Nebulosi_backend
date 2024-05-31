package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends
        JpaRepository<Booking, Long>,
        PagingAndSortingRepository<Booking, Long> {
}
