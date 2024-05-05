package it.epicode.dao;

import it.epicode.entities.Book;
import it.epicode.entities.Item;

import java.util.List;
import java.util.Optional;

public interface LibraryDao {


    void addItem(Item item);
    void removeItemByISBN(String isbn);
    Item getItemByISBN(String isbn);
    List<Item> getItemsByReleaseYear(int releaseYear);
    List<Book> getBooksByAuthor(String author);
    List<Item> getItemsByTitle(String title);

}
