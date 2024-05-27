package it.epicode.DevicesManagment.datalayer.repositories;


import it.epicode.DevicesManagment.datalayer.entities.Tablet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface TabletRepository extends
        JpaRepository<Tablet, Long>,
        PagingAndSortingRepository<Tablet, Long> {
    Optional<Tablet> findBySerialNumber(Long serialNumber);
}
