package it.epicode.dao;

import it.epicode.Main;
import it.epicode.entities.Evento;
import it.epicode.entities.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAPersonaDAO implements InterfaceDAO<Persona>{
    private static final Logger logger = LoggerFactory.getLogger(JPAPersonaDAO.class);
    private final EntityManager em;

    public JPAPersonaDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Persona entity) {
        var t = em.getTransaction();
        try {
            t.begin();
            em.persist(entity);
            t.commit();
        } catch(Exception ex) {
            logger.error("Exception in save()", ex);
        }
    }

    @Override
    public Persona getById(long id) {
        return em.find(Persona.class, id);
    }

    @Override
    public void delete(Persona entity) {
        var t = em.getTransaction();
        try {
            t.begin();
            em.remove(entity);
            t.commit();
        } catch (Exception ex){
            logger.error("Exception in delete()", ex);
        }
    }
}
