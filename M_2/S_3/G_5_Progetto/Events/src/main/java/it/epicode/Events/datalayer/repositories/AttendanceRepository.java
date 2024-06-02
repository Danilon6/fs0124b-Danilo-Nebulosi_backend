package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends
        JpaRepository<Attendance, Long>,
        PagingAndSortingRepository<Attendance, Long> {
    List<Attendance> findByEventId(Long id);
}
