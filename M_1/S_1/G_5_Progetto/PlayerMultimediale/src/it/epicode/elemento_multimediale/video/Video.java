package it.epicode.elemento_multimediale.video;

import it.epicode.elemento_multimediale.ElementoMultimediale;
import it.epicode.interfaces.PuoEssereRiprodotto;
import it.epicode.interfaces.PuoRegolareLaLuminosita;

public class Video extends ElementoMultimediale
implements PuoEssereRiprodotto,
        PuoRegolareLaLuminosita {

    /*
    * L'istanza di un video prevede necessarimente una durata,
    * non avrebbe senso creare un video senza una durata
    * come non avrebbe senso modificarla in seguito.
     */

    //ATTRIBUTI
    private int durata;
    private int volume = 0;
    private int luminosita = 0;

    //COSTRUTTORE
    public Video(String t, int d) {
        super(t);
        this.durata = d;
    }

    //GETTER AND SETTER
    public int getDurata() {
        return durata;
    }

    public int getVolume() {
        return volume;
    }

    public int getLuminosita() {
        return luminosita;
    }

    //METODO PER RIPRODURRE UN VIDEO ED UNA REGISTRAZIONE AUDIO
    @Override
    public void play() {

        //STRINGBUILDER FUNZIONA MA NON LO ABBIAMO FATTO
        //StringBuilder volumeString = new StringBuilder();
        //for (int i = 0; i < volume; i++) {
        //    volumeString.append("!");
        //}
        //StringBuilder luminositaString = new StringBuilder();
        //for (int i = 0; i < luminosita; i++) {
        //    luminositaString.append("*");
        //}

        // CICLO PER STAMPARE IL TITOLO TANTE VOLTE QUANTO LA DURATA E AGGIUNGERE
        // '!' TANTE VOLTE QUANTO IL VALORE DI VOLUME E
        // '*' TANTE VOLTE QUANTO IL VALORE DI LUMINOSITA
        for (int i = 0; i < this.durata; i++) {
            System.out.print(this.titolo + " - ");
            for (int j = 0; j < volume; j++) {
                System.out.print("!");
            }

            System.out.print(" - ");

            for (int k = 0; k < luminosita; k++) {
                System.out.print("*");;
            }
            System.out.println(" ");
        }
    }

    //METODI PER REGOLARE VOLUME E LUMINOSITA
    @Override
    public void alzaVolume() {
        if (this.volume == 10) {
            System.out.println("Il volume è già al massimo");
            return;
        }
        ++this.volume;
    }

    @Override
    public void abbassaVolume() {
        if (this.volume == 0) {
            System.out.println("Il volume è già al minimo");
            return;
        }
        -- this.volume;
    }

    @Override
    public void alzaLuminosita() {
        if (this.luminosita == 10) {
            System.out.println("La luminosità è già al massimo");
            return;
        }
        ++ this.luminosita ;
    }

    @Override
    public void abbassaLuminosita() {
        if (this.luminosita == 0) {
            System.out.println("La luminosità è già al minimo");
            return;
        }
        --this.luminosita;
    }

    @Override
    public String toString() {
        return "Video [titolo=" + titolo + " durata="+ durata +"]";
    }

}
