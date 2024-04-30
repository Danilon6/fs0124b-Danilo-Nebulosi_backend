package it.epicode;

import it.epicode.data.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        EventoDAO eventManager = new EventoDAO();
        Evento evento1 = new Evento("Evento1", LocalDate.now(), "Questo è l'evento privato numero 1", TypeEvent.PRIVATO, 50 );
        eventManager.save(evento1);
        var event = eventManager.getById(1);
        //eventManager.delete(event);
        eventManager.closeEntityManagerFactory();
    }
}