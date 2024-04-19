package it.epicode.elemento_multimediale.immagine;

import it.epicode.elemento_multimediale.ElementoMultimediale;
import it.epicode.interfaces.PuoRegolareLaLuminosita;

public class Immagine extends ElementoMultimediale
implements PuoRegolareLaLuminosita {

    // L'istanza di una imamgine prevede solo un titolo.



    //ATTRIBUTI
    private int luminosita = 0;

    //COSTRUTTORE
    public Immagine(String t) {
        super(t);
    }

    //GETTER AND SETTER

    public int getLuminosita() {
        return luminosita;
    }

    //METODO PER MOSTRARE UNA IMMAGINE
    public void show() {
        //STRINGBUILDER FUNZIONA MA NON LO ABBIAMO FATTO
        //StringBuilder luminositaString = new StringBuilder();
        //for (int i = 0; i < luminosita; i++) {
        //    luminositaString.append("*");
        //}


        // STAMPO IL TITOLO E AGGIUNGO '*' TANTE VOLTE QUANTO IL VALORE DI VOLUME
            System.out.print(this.titolo + " - ");
        for (int i = 0; i < luminosita; i++) {
            System.out.print("*");
        }

    }

    //METODI PER REGOLARE LA LUMINOSITA
    @Override
    public void alzaLuminosita() {
        if (this.luminosita == 10) {
            System.out.println("La luminosità è già al massimo");
            return;
        }
        ++this.luminosita;
    }

    @Override
    public void abbassaLuminosita() {
        if (this.luminosita == 0) {
            System.out.println("La luminosità è già al minimo");
            return;
        }
        -- this.luminosita;
    }

    @Override
    public String toString() {
        return "Immagine [titolo=" + titolo + "]";
    }
}
