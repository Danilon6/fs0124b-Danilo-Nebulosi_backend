package it.epicode.data;

import it.epicode.enums.Genere;

import java.time.LocalDate;

public class Libro extends ElementoCatalogo {
    private String author;
    private Genere genre;

    public Libro(Long ISBN, String title, LocalDate releaseDate, int pages, String author, Genere genre) {
        super(ISBN, title, releaseDate, pages);
        this.author = author;
        this.genre = genre;
    }


    public Genere getGenre() {
        return genre;
    }

    public void setGenre(Genere genere) {
        this.genre = genere;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Libro{" + "ISBN=" + ISBN + ", genre=" + genre + ", author='" + author + '\'' + ", title='" + title + '\'' + ", releaseDate='" + releaseDate + '\'' + ", pages=" + pages + '}';
    }
}
