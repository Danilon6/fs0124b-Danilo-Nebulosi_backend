package it.epicode.DevicesManagment.businesslayer.services.interfaces;

import it.epicode.DevicesManagment.datalayer.entities.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceService {

    Page<Device> getAll(Pageable p);

    Device getById(Long id);

    Device delete(Long id);

    void assignTo(Long employeeId, Long deviceId);


}
