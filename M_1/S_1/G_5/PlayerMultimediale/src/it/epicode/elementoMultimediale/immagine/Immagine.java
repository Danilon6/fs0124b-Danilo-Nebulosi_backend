package it.epicode.elementoMultimediale.immagine;

import it.epicode.elementoMultimediale.ElementoMultimediale;
import it.epicode.interfaces.PuoRegolareLaLuminosita;

public class Immagine extends ElementoMultimediale
implements PuoRegolareLaLuminosita {

    /*
     * L'istanza di una imamgine prevede solo un titolo,
     * Nonostante la presenza di metodi appositi che regolano la luminosita
    */


    //ATTRIBUTI
    private int luminosita;

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
        String luminositaString = "";
        for (int i = 0; i < luminosita; i++) {
            luminositaString = luminositaString + "*";
        }

        System.out.println(this.titolo + " - " + luminositaString);
    }

    //METODI PER REGOALRE LA LUMINOSITA
    @Override
    public void alzaLuminosita() {
        if (this.luminosita == 10) {
            System.out.println("La luminosità è già al massimo");
            return;
        }
        this.luminosita += 1;
    }

    @Override
    public void abbassaLuminosita() {
        if (this.luminosita == 0) {
            System.out.println("La luminosità è già al minimo");
            return;
        }
        this.luminosita -= 1;
    }

    @Override
    public String toString() {
        return "Immagine [titolo=" + titolo + "]";
    }
}
