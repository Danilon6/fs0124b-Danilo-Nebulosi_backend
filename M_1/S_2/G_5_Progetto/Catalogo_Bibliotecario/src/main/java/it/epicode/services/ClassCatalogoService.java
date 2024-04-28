package it.epicode.services;



import it.epicode.data.ElementoCatalogo;
import it.epicode.data.Libro;
import it.epicode.enums.TypeAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ClassCatalogoService implements CatalogoService{
    private static final Logger logger = LoggerFactory.getLogger(ClassCatalogoService.class);
    private final ArrayList<ElementoCatalogo> catalogoBibliotecario = new ArrayList<>();

    @Override
    public void addElement(ElementoCatalogo el) {
        catalogoBibliotecario.add(el);
        //save();
    }

    @Override
    public void removeElementByISBN(Long ISBN) {
        Optional<ElementoCatalogo> toRemoveElement = catalogoBibliotecario.stream()
                        .filter(el -> el.getISBN().equals(ISBN))
                                .findAny();

        if (toRemoveElement.isPresent()) {
            catalogoBibliotecario.remove(toRemoveElement.get());
        } else {
            logger.error("Nessun oggetto con ISBN:" + ISBN + "è stato trovato");
        }
        //save()

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
            case AUTHOR:
                String Author = (String) valoreAttributo;
                searchByAuthor(Author);
            case RELEASEDATE:
                String ReleaseDate = (String) valoreAttributo;
                searchByReleaseDate(ReleaseDate);
        }
    }

    @Override
    public void searchByISBN(Long ISBN) {
        Optional<ElementoCatalogo> searchedElementByISBN = catalogoBibliotecario.stream().filter(el -> el.getISBN().equals(ISBN))
                .findFirst();

        ElementoCatalogo foundElement= searchedElementByISBN.orElseThrow(() -> new NoSuchElementException("Nessun elemento con ISBN" + ISBN + " è stato trovato"));
        logger.info(String.valueOf(foundElement));
    }

    @Override
    public void searchByAuthor(String author) {
        Optional<ElementoCatalogo> searchedElementByAuthor = catalogoBibliotecario.stream()
                .filter(el -> el instanceof Libro && ((Libro) el).getAuthor().equals(author))
                .findFirst();

        ElementoCatalogo foundElement= searchedElementByAuthor.orElseThrow(() -> new NoSuchElementException("Nessun Libro con autore" + author + " è stato trovato"));
        logger.info(String.valueOf(foundElement));
    }

    @Override
    public void searchByReleaseDate(String releaseDate) {
        Optional<ElementoCatalogo> searchedElementByReleaseDate = catalogoBibliotecario.stream().filter(el -> el.getReleaseDate().equals(releaseDate))
                .findFirst();

        ElementoCatalogo foundElement= searchedElementByReleaseDate.orElseThrow(() -> new NoSuchElementException("Nessun elemento con data di pubblicazione " + releaseDate + " è stato trovato"));
        logger.info(String.valueOf(foundElement));
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public List<ElementoCatalogo> getAllElements() {
        return new ArrayList<>(catalogoBibliotecario);
    }

}
