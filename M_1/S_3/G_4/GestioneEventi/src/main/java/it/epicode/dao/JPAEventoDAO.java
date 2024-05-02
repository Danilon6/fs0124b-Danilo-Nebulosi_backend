package it.epicode.dao;

import it.epicode.Main;
import it.epicode.entities.Evento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAEventoDAO implements InterfaceDAO<Evento> {
    private static final Logger logger = LoggerFactory.getLogger(JPAPersonaDAO.class);
    private EntityManager em;

    public JPAEventoDAO(EntityManager em) {
        this.em = em;
    }
    @Override
    public void save(Evento entity) {
        var transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch(Exception ex){
        logger.info("Hai salvato correttamente un nuovo evento");
        }
    }

    @Override
    public Evento getById(long id) {
        var eventFounded = em.find(Evento.class, id);
        logger.info("L'elemento con id {} è {}", id, eventFounded);
        return eventFounded;
    }

    @Override
    public void delete(Evento entity) {
        var transaction = em.getTransaction();
        transaction.begin();
        em.remove(entity);
        transaction.commit();
        logger.info("L'elemento {} è stato rimosso", entity);
    }

}
