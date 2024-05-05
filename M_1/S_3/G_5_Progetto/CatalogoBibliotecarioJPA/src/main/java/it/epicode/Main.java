package it.epicode;


import it.epicode.dao.JpaLibraryDao;
import it.epicode.dao.JpaLoanDao;
import it.epicode.dao.JpaUserDao;
import it.epicode.dao.LibraryDao;
import it.epicode.entities.*;
import it.epicode.enums.Category;
import it.epicode.enums.Frequency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class Main {


    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final Random rnd = new Random(1234);
    private static final LocalDateTime now = LocalDateTime.now();
    private static final LocalDate randomDate = LocalDate.of(rnd.nextInt(1980, 2005), rnd.nextInt(1,13), rnd.nextInt(1, 29));

    private static List<Book> randomBook(int count){
        return LongStream.range(1, count + 1)
                .mapToObj(n -> new Book(
                        String.format("Autore Libro %d", n),
                        Category.values()[rnd.nextInt(8)],
                        String.format("%d-%d-%d-%d-%d", rnd.nextInt(100, 999), rnd.nextInt(10,99), rnd.nextInt(10000,99999),rnd.nextInt(10,99),rnd.nextInt(10)),
                        String.format("Libro %d", n),
                        rnd.nextInt(1900,2025),
                        rnd.nextInt(1000)))
                        .toList();
    }

    private static List<Magazine> randomMagazine(int count){
        return LongStream.range(1, count + 1)
                .mapToObj(n -> new Magazine(
                        Frequency.values()[rnd.nextInt(2)],
                        String.format("%d-%d-%d-%d-%d", rnd.nextInt(100, 999), rnd.nextInt(10,99), rnd.nextInt(10000,99999),rnd.nextInt(10,99),rnd.nextInt(10)),
                        String.format("Rivista %d", n),
                        rnd.nextInt(1900,2025),
                        rnd.nextInt(1000)))
                        .toList();
    }

    private static List<User> randomUser(int count){
        return IntStream.range(1, count + 1)
                .mapToObj(n -> new User(
                        String.format("First Name %d", n),
                        String.format("Last Name %d", n),
                        randomDate,
                        n))
                        .toList();
    }

    public static void main(String[] args) {
        int count = 50;

        try (var libraryDao = new JpaLibraryDao();
             var loanDao = new JpaLoanDao();
             var userDao = new JpaUserDao())
        {

            //CREO E SALVO 50 BOOK CASUALI
            log.info("Creo {} libri a caso", count);
            List<Book> bookList = randomBook(count);

            bookList.forEach(libraryDao::addItem);

            //CREO E SALVO 50 MAGAZINE CASUALI
            log.info("Creo {} riviste a caso", count);
            randomMagazine(count).forEach(libraryDao::addItem);

            //CREO E SALVO 50 USER CASUALI
            log.info("Creo {} user a caso", count);
            List<User> userList = randomUser(count);

            userList.forEach(userDao::addUser);

            //RECUPERO TUTTI I LOAN PER VERIFICARE CHE NON CI SIANO DUPLICATI DURANTE LA CREAZIONE
            List<Loan> loanList = loanDao.getAllLoans();

            //CREO E SALVO 20 LOAN CASUALI
            for (int i = 0; i < 20; i++) {
                var book = bookList.get(i);
                var user = userList.get(i);
                Loan loan = new Loan(user, book, randomDate);
                if (!loanList.contains(loan)) {
                loanDao.addLoan(loan);
                } else {
                    i--;
                }
            }

            //TEST METODI libraryDao

            // TEST METODO getItemsByISBN
            String isbn = "971-44-99462-10-7"; //QUESTO ITEM NON SARA ELIMINATO PERCHE APPARTIENE AD UN PRESTITO
            libraryDao.getItemByISBN(isbn).ifPresentOrElse(
                    item -> {
                        log.info("Ho trovato l'elemento con ISBN {}: {}", isbn, item);
                        log.info("Elimino l'elemento appena trovato");
                        // TEST METODO removeItemByISBN
                        libraryDao.removeItemByISBN(isbn);
                        log.info("Tento di recuperare lo stesso elemento");
                        libraryDao.getItemByISBN(isbn).ifPresentOrElse(
                                i -> log.info("L'elemento dovrebbe essere stato eliminato"),
                                ()-> log.info("L'elemento è stato eliminato"));
                    },
                    () -> log.info("L'elemento non è stato trovato"));

            //TEST METODO getItemsByTitle
            String testTitle1 = "1";
            String testTitle2 = "br";
            var searchTestTitle1 = libraryDao.getItemsByTitle(testTitle1);
            log.info("Ho trovato {} item che contengono nel titolo {}", searchTestTitle1.size(), testTitle1);
            log.info(searchTestTitle1.toString());

            //TEST METODO getItemsByTitle
            int testReleaseYear = 2015;
            var searchTestReleaseYear = libraryDao.getItemsByReleaseYear(testReleaseYear);
            log.info("Ho trovato {} item la cui releaseDate è {}", searchTestReleaseYear.size(), testReleaseYear);
            log.info(searchTestReleaseYear.toString());

            //TEST METODO getItemsByAuthor
            String testAuthor1 = "1";
            String testAuthor2 = "tor";
            var searchTestAuthor = libraryDao.getBooksByAuthor(testAuthor1);
            log.info("Ho trovato {} item il cui autore è o contiene i caratteri {}", searchTestAuthor.size(), testAuthor1);
            log.info(searchTestAuthor.toString());

            //TEST METODI loanDao

            //TEST METODO getLoanByCardNumber
            int card_numberTest1 = 1;
            int card_numberTest2 = 60;
            var searchCard_numberTest =  loanDao.getLoanByCardNumber(card_numberTest1);
            log.info("Ho trovato {} prestiti associati alla tessera n{}", searchCard_numberTest.size(), card_numberTest1);
            log.info(searchCard_numberTest.toString());

            //TEST METODO getLoanByCardNumber
            var searchExpiredOrNotReturnedLoans = loanDao.getExpiredOrNotReturnedLoans();
            log.info("Ho trovato {} prestiti scaduti o non restituiti", searchExpiredOrNotReturnedLoans.size());
            log.info(searchExpiredOrNotReturnedLoans.toString());

        } catch (Exception e) {
            log.error("Exception in main()", e);
        }
    }
}