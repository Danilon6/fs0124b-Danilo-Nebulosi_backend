package it.epicode.Esercizio2;

public class Chiamata {
    private int durata;
    private long numeroDiTelefono;

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public long getNumeroDiTelefono() {
        return numeroDiTelefono;
    }

    public void setNumeroDiTelefono(long numeroDiTelefono) {
        this.numeroDiTelefono = numeroDiTelefono;
    }



    public Chiamata(long a, int b){
        this.numeroDiTelefono = a;
        this.durata = b;
    }
}
