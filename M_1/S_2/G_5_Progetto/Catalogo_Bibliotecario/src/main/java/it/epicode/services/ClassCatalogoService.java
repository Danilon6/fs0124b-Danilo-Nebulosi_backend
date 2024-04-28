package it.epicode.services;




import it.epicode.data.ElementoCatalogo;
import it.epicode.data.Libro;
import it.epicode.data.Rivista;
import it.epicode.enums.Genere;
import it.epicode.enums.Periodicita;
import it.epicode.enums.TypeAttribute;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;


public class ClassCatalogoService implements CatalogoService{
    private static final Logger logger = LoggerFactory.getLogger(ClassCatalogoService.class);
    private final ArrayList<ElementoCatalogo> catalogoBibliotecario = new ArrayList<>();
    private static final String STORAGE = "./catalogoBibliotecario.csv";

    @Override
    public void addElement(ElementoCatalogo el) {
        catalogoBibliotecario.add(el);
        save();
    }

    @Override
    public void removeElementByISBN(Long ISBN) {
        Optional<ElementoCatalogo> toRemoveElement = catalogoBibliotecario.stream()
                        .filter(el -> el.getISBN().equals(ISBN))
                                .findFirst();

        if (toRemoveElement.isPresent()) {
            catalogoBibliotecario.remove(toRemoveElement.get());
        } else {
            logger.error("Nessun oggetto con ISBN:{} è stato trovato", ISBN);
        }
        save();

        //ALTRO MODO
        // catalogoBibliotecario.removeIf(el -> el.getISBN().equals(ISBN));
        //save()
    }

    @Override
    public void searchByAttribute(TypeAttribute tipoAttributo, Object valoreAttributo) {
        switch (tipoAttributo) {
            case ISBN:
                Long ISBN = (Long) valoreAttributo;
                searchByISBN(ISBN);
                break;
            case AUTHOR:
                String Author = (String) valoreAttributo;
                searchByAuthor(Author);
                break;
            case RELEASEDATE:
                LocalDate ReleaseDate = (LocalDate) valoreAttributo;
                searchByReleaseDate(ReleaseDate);
                break;
        }
    }

    @Override
    public void searchByISBN(Long ISBN) {
        try {
            Optional<ElementoCatalogo> searchedElementByISBN = catalogoBibliotecario.stream().filter(el -> el.getISBN().equals(ISBN))
                    .findFirst();

            ElementoCatalogo foundElement = searchedElementByISBN.orElseThrow(NoSuchElementException::new);
            logger.info(String.valueOf(foundElement));
        } catch ( NoSuchElementException e) {
            logger.warn("Nessun elemento con ISBN {} è stato trovato", ISBN);
        }


    }

    @Override
    public void searchByAuthor(String author) {
        try {
            Optional<ElementoCatalogo> searchedElementByAuthor = catalogoBibliotecario.stream()
                    .filter(el -> el instanceof Libro && ((Libro) el).getAuthor().equals(author))
                    .findFirst();

            ElementoCatalogo foundElement = searchedElementByAuthor.orElseThrow(NoSuchElementException::new);
            logger.info(String.valueOf(foundElement));
        } catch ( NoSuchElementException e) {
            logger.warn("Nessun Libro con autore {} è stato trovato", author);
        }
    }

    @Override
    public void searchByReleaseDate(LocalDate releaseDate) {
        try {
            Optional<ElementoCatalogo> searchedElementByReleaseDate = catalogoBibliotecario.stream().filter(el -> el.getReleaseDate().equals(releaseDate))
                    .findFirst();

            ElementoCatalogo foundElement = searchedElementByReleaseDate.orElseThrow(NoSuchElementException::new);
            logger.info(String.valueOf(foundElement));
        } catch ( NoSuchElementException e) {
            logger.warn("Nessun elemento con data di pubblicazione {} è stato trovato", releaseDate);
        }
    }

    public ClassCatalogoService() {
        this.load();
    }

    @Override
    public void save() {
        File f = new File(STORAGE);

        try{
            //FileUtils.delete(f);
            FileUtils.writeStringToFile(f, "", StandardCharsets.ISO_8859_1, false);
        } catch (IOException e) {
            logger.error("Si è verificato un errore durante l'eliminazione del file di storage", e);
        }

        catalogoBibliotecario
                .forEach((el) -> {
                    try {
                    List<String> lines = new ArrayList<>();
                    lines.add(el.getClass().getSimpleName());
                    lines.add(String.valueOf(el.getISBN()));
                    lines.add(el.getTitle());
                    lines.add(el.getReleaseDate().toString());
                    lines.add(String.valueOf(el.getPages()));
                    if (el instanceof Libro){
                        lines.add(((Libro) el).getAuthor());
                        lines.add(String.valueOf(((Libro) el).getGenre()));
                    } else if (el instanceof Rivista ) {
                        lines.add(String.valueOf(((Rivista) el).getPeriodicita()));
                    }
                    FileUtils.writeStringToFile(f, String.join(",", lines) + "\n", StandardCharsets.ISO_8859_1, true);
                    } catch (IOException e) {
                        logger.error("Errore durante il salvataggio", e);
                    }
                });

    }

    @Override
    public void load() {
        File f = new File(STORAGE);

        try {
            List<String> lines = FileUtils.readLines(f, StandardCharsets.ISO_8859_1);
            for (String l : lines) {
                String[] elementoCatalogoArr = l.split(",");
                Long ISBN = Long.parseLong(elementoCatalogoArr[1]);
                String title = elementoCatalogoArr[2];
                LocalDate releasedate = LocalDate.parse(elementoCatalogoArr[3]);
                int pages = Integer.parseInt(elementoCatalogoArr[4]);
                if ("Libro".equals(elementoCatalogoArr[0])){
                    String author = elementoCatalogoArr[5];
                    Genere genre = Genere.valueOf(elementoCatalogoArr[6]);
                    catalogoBibliotecario.add(new Libro(ISBN, title, releasedate, pages, author, genre));
                } else if ("Rivista".equals(elementoCatalogoArr[0])){
                    Periodicita periodicita = Periodicita.valueOf(elementoCatalogoArr[5]);
                    catalogoBibliotecario.add(new Rivista(ISBN, title, releasedate, pages, periodicita));
                }
            }
        } catch (IOException e) {
            logger.error("Errore durante il caricamento", e);
        }
    }

    @Override
    public List<ElementoCatalogo> getAllElements() {
        return new ArrayList<>(catalogoBibliotecario);
    }

}
