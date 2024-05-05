package it.epicode.dao;

import it.epicode.entities.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JpaLoanDao implements LoanDao {
    private static final Logger logger = LoggerFactory.getLogger(JpaLoanDao.class);
    private static final String PERSISTENCE_UNIT = "catalogoBibliotecario";
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private final EntityManager em = emf.createEntityManager();

    @Override
    public List<Loan> getAllLoans() {
        return em.createQuery("SELECT l FROM Loan l", Loan.class)
                .getResultList();
    }


    @Override
    public void addLoan(Loan loan) {
        try {
            var t = em.getTransaction();
            t.begin();
            em.persist(loan);
            t.commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Exception in addLoan()", ex);

        }
    }


    @Override
    public List<Loan> getLoanByCardNumber(int card_number) {
        return em.createQuery("SELECT l FROM Loan l WHERE l.user.card_number = :card_number", Loan.class)
                .setParameter("card_number", card_number)
                .getResultList();
    }

    @Override
    public List<Loan> getExpiredOrNotReturnedLoans() {
        return em.createQuery("SELECT l FROM Loan l WHERE l.return_date < CURRENT_DATE AND l.actual_return_date IS NULL", Loan.class)
                .getResultList();
    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
    }
}
