package it.epicode.data;

import it.epicode.enums.Periodicita;

import java.time.LocalDate;

public class Rivista extends ElementoCatalogo {
    Periodicita periodicita;

    public Rivista(Long ISBN, String title, LocalDate releaseDate, int pages, Periodicita periodicita) {
        super(ISBN, title, releaseDate, pages);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" + "periodicita=" + periodicita + ", ISBN=" + ISBN + ", title='" + title + '\'' + ", releaseDate='" + releaseDate + '\'' + ", pages=" + pages + '}';
    }
}
