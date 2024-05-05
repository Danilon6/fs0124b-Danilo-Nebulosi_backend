package it.epicode;


import it.epicode.dao.JpaLibraryDao;
import it.epicode.dao.JpaLoanDao;
import it.epicode.dao.JpaUserDao;
import it.epicode.entities.Book;
import it.epicode.entities.Item;
import it.epicode.entities.Loan;
import it.epicode.entities.User;
import it.epicode.enums.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;


public class Main {


    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        User user1 = new User("Danilo", "Nebulosi", LocalDate.of(2005,1,23), 1);
        Book book1 = new Book("Luca Verdi", Category.FANTASY, "001","Titolo casuale", 1990, 100);
        Book book2 = new Book("Mario Rossi", Category.MYSTERY, "002"," Libro bello", 2005, 100);
        Book book3 = new Book("Anna Neri", Category.SCIENCE, "003","Titolo poco diverso", 2024, 100);
        Loan loan1 = new Loan(user1, book1, LocalDate.now() );

        JpaLibraryDao libraryDao = new JpaLibraryDao();
        JpaLoanDao loanDao = new JpaLoanDao();
        JpaUserDao userDao = new JpaUserDao();


        libraryDao.addItem(book1);
        libraryDao.addItem(book2);
        libraryDao.addItem(book3);
        libraryDao.getItemByISBN("001");
        //libraryDao.getItemByISBN("4");
        List<Book> itemByAuthor = libraryDao.getBooksByAuthor("Mario");
        log.info(itemByAuthor.toString());
        libraryDao.getBooksByAuthor("Neri");
        libraryDao.getBooksByAuthor("blu");
        libraryDao.getItemsByTitle("casuale");
        libraryDao.getItemsByTitle("poco");
        libraryDao.getItemsByTitle("Libro");
        libraryDao.getItemsByTitle("ciao");
        libraryDao.getItemsByReleaseYear(2005);
        libraryDao.getItemsByReleaseYear(1975);

        userDao.addUser(user1);
        loanDao.addLoan(loan1);
        System.out.println();
        loanDao.getLoanByCardNumber(user1.getCard_number());
        loanDao.getExpiredOrNotReturnedLoans();




    }
}