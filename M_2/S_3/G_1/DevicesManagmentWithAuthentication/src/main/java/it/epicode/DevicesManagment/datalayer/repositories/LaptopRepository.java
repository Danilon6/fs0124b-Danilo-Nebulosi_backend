package it.epicode.DevicesManagment.datalayer.repositories;

import it.epicode.DevicesManagment.datalayer.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface LaptopRepository extends
        JpaRepository<Laptop, Long>,
        PagingAndSortingRepository<Laptop, Long> {
    Optional<Laptop> findBySerialNumber(Long serialNumber);
}
