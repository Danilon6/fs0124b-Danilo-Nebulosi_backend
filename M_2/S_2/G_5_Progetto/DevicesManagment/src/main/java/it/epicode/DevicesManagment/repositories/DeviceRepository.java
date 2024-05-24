package it.epicode.DevicesManagment.repositories;

import it.epicode.DevicesManagment.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceRepository extends
        JpaRepository<Device,Long>,
        PagingAndSortingRepository<Device, Long> {
}
