package it.epicode;

import it.epicode.data.ElementoCatalogo;
import it.epicode.data.Libro;
import it.epicode.data.Rivista;
import it.epicode.enums.Genere;
import it.epicode.enums.Periodicita;
import it.epicode.enums.TypeAttribute;
import it.epicode.services.CatalogoService;
import it.epicode.services.ClassCatalogoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        CatalogoService service = new ClassCatalogoService();
        // TEST AGGIUNTA DI ELEMENTI
        //service.addElement(new Libro(1L, "Libro1", LocalDate.now(), 100, "Autore1", Genere.FANTASCIENZA));
        //service.addElement(new Libro(2L, "Libro2", LocalDate.of(2021, 4, 20), 300, "Autore2", Genere.GIALLO));
        //service.addElement(new Libro(3L, "Libro3", LocalDate.now(), 250, "Autore3", Genere.ROMANZO));
        //service.addElement(new Rivista(5L, "Rivista1", LocalDate.now(), 60, Periodicita.SETTIMANALE));
        //service.addElement(new Rivista(6L, "Rivista2", LocalDate.now(), 150, Periodicita.MENSILE));
        //service.addElement(new Rivista(7L, "Rivista3", LocalDate.now(), 90, Periodicita.SEMESTRALE));
        //service.addElement(new Rivista(8L, "Rivista4", LocalDate.now(), 120, Periodicita.SETTIMANALE));

        //TEST RIMOZIONE DI UN ELEMENTO
        //service.removeElementByISBN(8L);


        //TEST METODI PER LA RICERCA
        //service.searchByAttribute(TypeAttribute.ISBN, 3l);
        //service.searchByAttribute(TypeAttribute.AUTHOR, "Autore");
        //service.searchByAttribute(TypeAttribute.AUTHOR, "Autore1");
        //service.searchByAttribute(TypeAttribute.RELEASEDATE, LocalDate.of(2021, 4, 21));
        //service.searchByAttribute(TypeAttribute.RELEASEDATE, LocalDate.of(2021, 4, 20));
        //service.searchByAttribute(TypeAttribute.RELEASEDATE, LocalDate.now());

        service.getAllElements().forEach(System.out::println);
    }
}