package it.epicode.booking.repositories;

import it.epicode.booking.entities.Booking;
import it.epicode.booking.entities.User;
import it.epicode.booking.entities.WorkStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByWorkStationAndDate(WorkStation workStation, LocalDate date);
    Optional<Booking> findByUserAndDate(User user, LocalDate date);

}
