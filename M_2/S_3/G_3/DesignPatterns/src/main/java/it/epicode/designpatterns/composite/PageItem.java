package it.epicode.designpatterns.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@Slf4j
public class PageItem extends BookItem{
    private int numeroDiPagina;
    private String content;

    @Override
    public int getNumeroPagine() {
        return 1;
    }

    @Override
    public void stampa() {
        System.out.println(this.content);
    }
}
