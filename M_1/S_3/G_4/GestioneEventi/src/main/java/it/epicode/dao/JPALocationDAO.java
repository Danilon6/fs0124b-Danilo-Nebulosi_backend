package it.epicode.dao;

import it.epicode.Main;
import it.epicode.entities.Location;
import it.epicode.entities.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPALocationDAO implements InterfaceDAO<Location>{
    private static final Logger logger = LoggerFactory.getLogger(JPAPersonaDAO.class);
    private EntityManager em;

    public JPALocationDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Location entity) {
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
    public Location getById(long id) {
        return em.find(Location.class, id);
    }

    @Override
    public void delete(Location entity) {
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
