package it.epicode.restapiblog.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface CRUDDao<T> {

    //DOVREBBE RESITUIRE LA T PER CONSENITRE AL CONTROLLER DI AVER EUNA COPIA DI QUANTO è STATO FATTO BASTI PENSARE ALL'ID CHE VIENE IMPOSTATO AUTOMATICAMENTE
    public T create(T e); // Create

    //DOVREBBE RESITUIRE LA T PER CONSENITRE AL CONTROLLER DI AVER EUNA COPIA DI QUANTO è STATO FATTO
    public T update(Long id, T e); // Update

    //DOVREBBE RESITUIRE LA T PER FAR CAPIRE QUAL'ERA L'ELEMENTO CHE C'ERA PRIMA SUL DATABASE
    public void delete(Long id); // Delete

    public T getById(Long id); // Read

    public Page<T> getAll(Pageable pageable);
}
