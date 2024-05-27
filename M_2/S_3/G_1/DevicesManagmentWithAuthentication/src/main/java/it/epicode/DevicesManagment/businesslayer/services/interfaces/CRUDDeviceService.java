package it.epicode.DevicesManagment.businesslayer.services.interfaces;

import it.epicode.DevicesManagment.businesslayer.services.dto.DeviceDTO;
import it.epicode.DevicesManagment.datalayer.entities.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDDeviceService<T>{
    Page<T> getAll(Pageable p);

    T getById(Long id);

    T save(DeviceDTO e);

    T update(Long id, Device e);

    T delete(Long id);

}
