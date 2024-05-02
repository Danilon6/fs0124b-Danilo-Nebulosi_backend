package it.epicode.dao;

import it.epicode.entities.Evento;

public interface InterfaceDAO<T> {

    /**
     *
     * salva un evento
     * @param entity è l'evento da salvare
     */
    void save(T entity);

    /**
     *
     * cerca un elemento tramite il suo id
     *
     * @param id è la primary key dell'evento da cercare
     * @return l'evento desiderato
     */
    T getById(long id);

    /**
     * elimina un elemento
     * @param entity è l'evento da eliminare
     */
    void delete(T entity);
}
