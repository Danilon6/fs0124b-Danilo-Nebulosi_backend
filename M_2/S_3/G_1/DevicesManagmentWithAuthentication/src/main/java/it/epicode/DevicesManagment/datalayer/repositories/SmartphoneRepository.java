package it.epicode.DevicesManagment.datalayer.repositories;

import it.epicode.DevicesManagment.datalayer.entities.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SmartphoneRepository extends
        JpaRepository<Smartphone, Long>,
        PagingAndSortingRepository<Smartphone, Long> {
    Optional<Smartphone> findBySerialNumber(Long serialNumber);
}
