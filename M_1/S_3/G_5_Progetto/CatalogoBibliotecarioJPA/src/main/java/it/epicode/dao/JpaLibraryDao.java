package it.epicode.dao;

import it.epicode.entities.Book;
import it.epicode.entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


public class JpaLibraryDao implements LibraryDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaLoanDao.class);
    private static final String PERSISTENCE_UNIT = "catalogoBibliotecario";
    private final EntityManager em;

    public JpaLibraryDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        em = emf.createEntityManager();
    }

    @Override
    public void addItem(Item item) {
        try {
            var t = em.getTransaction();
            t.begin();
            em.persist(item);
            t.commit();
           logger.info("Item: {} saved", item);
        } catch (Exception ex){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Exception in addItem()", ex);
        }
    }

    @Override
    public Item getItemByISBN(String isbn) {
        try {
            return em.createQuery("SELECT i FROM Item i Where i.isbn = :isbn", Item.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        } catch( Exception ex) {
            logger.error("Exception in getItemByISBN()", ex);
            throw ex;
        }
    }

    @Override
    public void removeItemByISBN(String isbn) {
        try {
        var t = em.getTransaction();
        t.begin();
        var toRemove = this.getItemByISBN(isbn);
        if (toRemove != null) {
            em.remove(toRemove);
        }
        t.commit();
        } catch (Exception ex){
            logger.error("Exception in removeItemByISBN()", ex);
        }
    }



    @Override
    public List<Item> getItemsByReleaseYear(int releaseYear) {
            return em.createQuery("SELECT i FROM Item i WHERE i.releaseYear = :releaseYear", Item.class)
                    .setParameter("releaseYear", releaseYear)
                    .getResultList();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return em.createQuery("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(:author)", Book.class)
                .setParameter("author", "%" + author.toLowerCase() + "%")
                .getResultList();
    }

    @Override
    public List<Item> getItemsByTitle(String title) {
        return em.createQuery("SELECT i FROM Item i WHERE LOWER(i.title) LIKE LOWER(:title)", Item.class)
                .setParameter("title", "%" + title.toLowerCase() + "%")
                .getResultList();
    }

}
