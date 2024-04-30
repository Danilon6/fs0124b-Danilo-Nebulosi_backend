package it.epicode;

import it.epicode.data.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventoDAO implements EventoInterfaceDAO{
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String PERSISTENCE_UNIT = "Gestione_Eventi";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    EntityManager em = emf.createEntityManager();

    @Override
    public void save(Evento e) {
        var transaction = em.getTransaction();
        transaction.begin();
        em.persist(e);
        transaction.commit();
        logger.info("Hai salvato correttamente un nuovo evento");
    }

    @Override
    public Evento getById(long id) {
        var eventFounded = em.find(Evento.class, id);
        logger.info("L'elemento con id {} è {}", id, eventFounded);
        return eventFounded;
    }

    @Override
    public void delete(Evento e) {
        var transaction = em.getTransaction();
        transaction.begin();
        em.remove(e);
        transaction.commit();
        logger.info("L'elemento {} è stato rimosso", e);
        em.close();
    }

    public void closeEntityManagerFactory() {
        emf.close();
    }
    }
