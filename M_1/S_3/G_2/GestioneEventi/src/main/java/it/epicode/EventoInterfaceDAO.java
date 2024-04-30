package it.epicode;

import it.epicode.data.Evento;

public interface EventoInterfaceDAO {
    void save(Evento e);
    Evento getById(long id);
    void delete(Evento e);
}
