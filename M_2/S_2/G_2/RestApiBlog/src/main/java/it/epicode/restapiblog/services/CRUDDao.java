package it.epicode.restapiblog.services;

import it.epicode.restapiblog.entities.Post;

import java.util.List;
import java.util.Optional;

public interface CRUDDao<T> {

    public T create(T e); // Create

    public void update(T e); // Update

    public void delete(Long id); // Delete

    public Optional<T> getById(Long id); // Read

    public List<T> getAll();
}
