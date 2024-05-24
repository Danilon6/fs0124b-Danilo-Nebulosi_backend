package it.epicode.DevicesManagment.services.interfaces;

import it.epicode.DevicesManagment.services.dto.DeviceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDDeviceService<T>{
    Page<T> getAll(Pageable p);

    T getById(Long id);

    T save(DeviceDTO e);

    T update(Long id, T e);

    T delete(Long id);

}
