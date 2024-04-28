package it.epicode.data;

import it.epicode.enums.Periodicita;

public class Rivista extends ElementoCatalogo {
    Periodicita periodicita;

    public Rivista(Long ISBN, String titolo, Periodicita periodicita, String annoDiPubblicazione, int numeroPagine) {
        super(ISBN, titolo, annoDiPubblicazione, numeroPagine);
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
