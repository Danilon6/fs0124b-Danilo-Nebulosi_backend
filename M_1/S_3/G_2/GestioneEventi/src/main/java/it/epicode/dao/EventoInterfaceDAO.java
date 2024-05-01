package it.epicode.dao;

import it.epicode.entities.Evento;

public interface EventoInterfaceDAO{

    /**
     *
     * salva un evento
     * @param e è l'evento da salvare
     */
    void save(Evento e);

    /**
     *
     * cerca un elemento tramite il suo id
     *
     * @param id è la primary key dell'evento da cercare
     * @return l'evento desiderato
     */
    Evento getById(long id);

    /**
     * elimina un elemento
     * @param e è l'evento da eliminare
     */
    void delete(Evento e);
}
