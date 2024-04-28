package it.epicode.services;

import it.epicode.data.ElementoCatalogo;
import it.epicode.enums.TypeAttribute;

import java.util.List;

public interface CatalogoService {

    void addElement(ElementoCatalogo el);
    void removeElementByISBN(Long ISBN);
    void searchByAttribute(TypeAttribute tipoAttributo, Object valoreAttributo );
    void searchByISBN(Long ISBN);
    void searchByAuthor(String autore);
    void searchByReleaseDate(String releaseDate);
    void save();
    void load();
    List<ElementoCatalogo> getAllElements();

}
