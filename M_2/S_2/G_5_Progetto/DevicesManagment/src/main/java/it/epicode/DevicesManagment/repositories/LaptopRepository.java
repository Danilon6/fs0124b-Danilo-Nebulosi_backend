package it.epicode.DevicesManagment.repositories;

import it.epicode.DevicesManagment.entities.Laptop;
import it.epicode.DevicesManagment.entities.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface LaptopRepository extends
        JpaRepository<Laptop, Long>,
        PagingAndSortingRepository<Laptop, Long> {
    Optional<Smartphone> findBySerialNumber(Long serialNumber);
}
