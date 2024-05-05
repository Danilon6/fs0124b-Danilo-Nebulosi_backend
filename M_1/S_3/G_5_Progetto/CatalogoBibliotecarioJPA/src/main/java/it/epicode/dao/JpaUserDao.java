package it.epicode.dao;


import it.epicode.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaUserDao implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(JpaLoanDao.class);
    private static final String PERSISTENCE_UNIT = "catalogoBibliotecario";
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void addUser(User user) {

        try {
            var t = em.getTransaction();
            t.begin();
            em.persist(user);
            t.commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Exception in addUser()", ex);
        }

    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
    }
}
