package it.epicode.designpatterns.composite;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Book extends BookItem{
    private String title;
    private List<BookItem> chapters;
    private List<String> authors;
    private double price;


    @Override
    public int getNumeroPagine() {
        return chapters.stream().mapToInt(BookItem::getNumeroPagine).sum();
    }

    @Override
    public void stampa() {
        System.out.println(this.title);
        chapters.forEach(BookItem::stampa);
    }
}
