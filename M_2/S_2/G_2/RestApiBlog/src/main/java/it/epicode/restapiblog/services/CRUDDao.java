package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Post;

import java.util.List;
import java.util.Optional;

public interface CRUDDao<T> {

    //DOVREBBE RESITUIRE LA T PER CONSENITRE AL CONTROLLER DI AVER EUNA COPIA DI QUANTO è STATO FATTO BASTI PENSARE ALL'ID CHE VIENE IMPOSTATO AUTOMATICAMENTE
    public T create(T e); // Create

    //DOVREBBE RESITUIRE LA T PER CONSENITRE AL CONTROLLER DI AVER EUNA COPIA DI QUANTO è STATO FATTO
    public void update(T e); // Update

    //DOVREBBE RESITUIRE LA T PER FAR CAPIRE QUAL'ERA L'ELEMENTO CHE C'ERA PRIMA SUL DATABASE
    public void delete(Long id); // Delete

    public Optional<T> getById(Long id); // Read

    public List<T> getAll();
}
