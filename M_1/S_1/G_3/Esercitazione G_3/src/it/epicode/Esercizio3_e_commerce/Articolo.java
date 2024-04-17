package it.epicode.Esercizio3_e_commerce;

public class Articolo {

    private int codiceArticolo;
    private String descrizioneArticolo;
    private int prezzo;
    private int pezziDisponibili;


    public String getDescrizioneArticolo() {
        return descrizioneArticolo;
    }

    public int getCodiceArticolo() {
        return codiceArticolo;
    }

    public int getPezziDisponibili() {
        return pezziDisponibili;
    }

    public void setPezziDisponibili(int pezziDisponibili) {
        this.pezziDisponibili = pezziDisponibili;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public void setCodiceArticolo(int codiceArticolo) {
        this.codiceArticolo = codiceArticolo;
    }

    public void setDescrizioneArticolo(String descrizioneArticolo) {
        this.descrizioneArticolo = descrizioneArticolo;
    }




    public Articolo(int a, String b, int c, int d ){
        this.codiceArticolo = a;
        this.descrizioneArticolo = b;
        this.prezzo = c;
        this.pezziDisponibili = d;
    }


}
