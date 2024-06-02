package it.epicode.Events.businesslayer.services.interfaces;

import it.epicode.Events.businesslayer.services.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService<T, A extends BaseDTO> {
    Page<T> getAll(Pageable p);

    T getById(Long id);

    T save(A e);

    T update(Long id, T e);

    T delete(Long id);
}
