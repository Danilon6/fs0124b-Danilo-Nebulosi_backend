package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = Tables.Names.LIBRARY_BASE)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = Tables.Columns.DISCRIMINATOR, discriminatorType = DiscriminatorType.INTEGER)
public abstract class Item extends BaseEntity implements Serializable {

    @Column(length = 13,nullable = false)
    private String isbn;
    @Column(length = 125, nullable = false)
    private String title;
    @Column(nullable = false)
    private int releaseYear;
    @Column(nullable = false)
    private int pages;

    public Item(){}

    public Item(String isbn, String title, int releaseYear, int pages) {
        this.isbn = isbn;
        this.title = title;
        this.releaseYear = releaseYear;
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Item{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + ", releaseDate=" + releaseYear + ", pages=" + pages + getInsertedAt() + getId() + '}';
    }
}
