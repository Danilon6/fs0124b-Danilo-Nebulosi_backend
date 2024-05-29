package it.epicode.designpatterns.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@AllArgsConstructor
@Slf4j
public class SectionItem extends BookItem{
    private String title;
    private List<BookItem> componenti;

    @Override
    public int getNumeroPagine() {
        return componenti.stream().mapToInt(BookItem::getNumeroPagine).sum();
    }
    @Override
    public void stampa() {
        System.out.println(this.title);
        componenti.forEach(BookItem::stampa);
    }
}
