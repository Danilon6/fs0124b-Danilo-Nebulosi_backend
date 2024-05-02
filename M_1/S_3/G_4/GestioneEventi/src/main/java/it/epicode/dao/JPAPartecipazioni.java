package it.epicode.dao;

import it.epicode.entities.Partecipazione;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAPartecipazioni implements InterfaceDAO<Partecipazione> {
    private static final Logger logger = LoggerFactory.getLogger(JPAPersonaDAO.class);
    private EntityManager em;

    public JPAPartecipazioni(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Partecipazione entity) {
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
    public Partecipazione getById(long id) {
        return em.find(Partecipazione.class, id);
    }

    @Override
    public void delete(Partecipazione entity) {
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
