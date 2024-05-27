package it.epicode.DevicesManagment.datalayer.repositories;

import it.epicode.DevicesManagment.datalayer.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceRepository extends
        JpaRepository<Device,Long>,
        PagingAndSortingRepository<Device, Long> {
}
