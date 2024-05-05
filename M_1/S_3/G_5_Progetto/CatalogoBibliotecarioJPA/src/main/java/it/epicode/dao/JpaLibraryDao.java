package it.epicode.dao;

import it.epicode.entities.Book;
import it.epicode.entities.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;


public class JpaLibraryDao implements LibraryDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaLoanDao.class);
    private static final String PERSISTENCE_UNIT = "catalogoBibliotecario";
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void addItem(Item item) {
        try {
            var t = em.getTransaction();
            t.begin();
            em.persist(item);
            t.commit();
        } catch (Exception ex){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Exception in addItem()", ex);
        }
    }

    @Override
    public Optional<Item> getItemByISBN(String isbn) {
        try {
        var founded = em.createQuery("SELECT i FROM Item i Where i.isbn = :isbn", Item.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();
        return Optional.ofNullable(founded);
    } catch (NoResultException ex) {
        return Optional.empty();
    }
    }

    @Override
    public void removeItemByISBN(String isbn) {
        try {
        var t = em.getTransaction();
        t.begin();
        var toRemove = this.getItemByISBN(isbn);
            toRemove.ifPresentOrElse(
                    item -> {
                        try {
                             var result = em.createQuery("SELECT l FROM Loan l WHERE l.borrowed_item = :item")
                                .setParameter("item", toRemove.get())
                                .getSingleResult();
                             if (result != null) {
                                 logger.warn("Impossibile rimuovere l'elemento perchè fa parte di un prestito");
                             } else {
                                 em.remove(toRemove.get());
                                 t.commit();
                                 logger.info("L'elemento con isbn {} è stato correttamente rimosso", isbn);
                             }
                } catch (NoResultException ex) {
                    logger.error("NoresultsException in removeItemByISBN():", ex);
                }

            },
            () -> logger.warn("Nessun elemento trovato con ISBN: {}", isbn)
            );
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

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
    }
}
