package it.epicode;

import it.epicode.dao.JPAEventoDAO;
import it.epicode.dao.JPALocationDAO;
import it.epicode.dao.JPAPartecipazioni;
import it.epicode.dao.JPAPersonaDAO;
import it.epicode.entities.Evento;
import it.epicode.entities.Location;
import it.epicode.entities.Partecipazione;
import it.epicode.entities.Persona;
import it.epicode.enums.Genre;
import it.epicode.enums.Stato;
import it.epicode.enums.TypeEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Main {


    public static void main(String[] args) {
      Logger logger = LoggerFactory.getLogger(Main.class);
      String PERSISTENCE_UNIT = "gestione_eventi";
      EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);

      EntityManager em = emf.createEntityManager();
        JPALocationDAO locationDAO = new JPALocationDAO(em);
        JPAEventoDAO eventDAO = new JPAEventoDAO(em);
        JPAPersonaDAO personaDAO = new JPAPersonaDAO(em);
        JPAPartecipazioni partecipazioneDao = new JPAPartecipazioni(em);

        //GENERO PERSONE
        Persona persona1 = new Persona("Danilo", "Nebulosi", "danilonebulosi@gmail.com", Date.from(Instant.now()), Genre.M);
        Persona persona2 = new Persona("Mario", "Rossi", "mariorossi@gmail.com", Date.from(Instant.now()), Genre.M);
        Persona persona3 = new Persona("Anna", "Neri", "annaneri@gmail.com", Date.from(Instant.now()), Genre.F);
        personaDAO.save(persona1);
        personaDAO.save(persona2);
        personaDAO.save(persona3);
        //GENERO UNA LOCATION
        Location location1 = new Location("Location1", "Napoli");
        locationDAO.save(location1);
        //GENERO EVENTI
        Evento evento1 = new Evento("Evento1", LocalDate.now(), "Questo è l'evento1", TypeEvent.PRIVATO, 50, location1);
        Evento evento2 = new Evento("Evento2", LocalDate.now(), "Questo è l'evento2", TypeEvent.PRIVATO, 50, location1);
        eventDAO.save(evento1);
        eventDAO.save(evento2);
        //GENERO PARTECIPAZIONI
        var p1 = personaDAO.getById(1);
        var p2 = personaDAO.getById(2);
        var e1 = eventDAO.getById(1);
        var e2 = eventDAO.getById(2);

        Partecipazione partecipazione1 = new Partecipazione(p1, e1, Stato.CONFERMATA);
        Partecipazione partecipazione2 = new Partecipazione(p1, e2, Stato.CONFERMATA);
        Partecipazione partecipazione3 = new Partecipazione(p2, e2, Stato.CONFERMATA);

        partecipazioneDao.save(partecipazione1);
        partecipazioneDao.save(partecipazione2);
        partecipazioneDao.save(partecipazione3);

        em.close();
        emf.close();


    }
}