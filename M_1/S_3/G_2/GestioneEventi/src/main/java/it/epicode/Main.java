package it.epicode;

import it.epicode.dao.EventoDAO;
import it.epicode.entities.Evento;
import it.epicode.entities.TypeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        EventoDAO eventManager = new EventoDAO();
        Evento evento1 = new Evento("Evento3", LocalDate.now(), "Questo è l'evento privato numero 1", TypeEvent.PRIVATO, 50 );
        eventManager.save(evento1);
        //var event = eventManager.getById(1);
        //eventManager.delete(event);
        eventManager.closeEntityManagerFactory();
    }
}