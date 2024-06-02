package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends
        JpaRepository<Attendance, Long>,
        PagingAndSortingRepository<Attendance, Long> {
    Optional<Attendance> findByUserIdAndEventId(Long user_id, Long event_id);
    List<Attendance> findByEventId(Long id);
    List<Attendance> findByUserId(Long id);
}
