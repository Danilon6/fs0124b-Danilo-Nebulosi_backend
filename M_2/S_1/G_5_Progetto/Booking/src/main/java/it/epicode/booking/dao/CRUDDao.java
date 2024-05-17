package it.epicode.booking.dao;

public interface CRUDDao<T> {

    public void create(T e); // Create

    public void update(T e); // Update

    public void delete(Long id); // Delete

    public T getById(Long id); // Read

}
