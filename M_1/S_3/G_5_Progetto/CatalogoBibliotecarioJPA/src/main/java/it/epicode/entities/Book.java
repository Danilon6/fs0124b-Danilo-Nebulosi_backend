package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import it.epicode.enums.Category;
import jakarta.persistence.*;

@Entity
@Table(name = Tables.Names.BOOKS)
@DiscriminatorValue(Tables.Discriminators.BOOKS)
public class Book extends Item{
    @Column(nullable = false)
    private String author;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    public Book(){}

    public Book(String author, Category category,String isbn, String title, int releaseDate, int pages) {
        super(isbn, title, releaseDate, pages);
        this.author = author;
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" + "author='" + author + '\'' + ", category=" + category + super.toString() + '}';
    }
}
