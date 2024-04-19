package it.epicode.elemento_multimediale.video;

import it.epicode.elemento_multimediale.ElementoMultimediale;
import it.epicode.interfaces.PuoEssereRiprodotto;
import it.epicode.interfaces.PuoRegolareLaLuminosita;

public class Video extends ElementoMultimediale
implements PuoEssereRiprodotto,
        PuoRegolareLaLuminosita {

    /*
    * L'istanza di un video prevede necessarimente una durata,
    * non avrebbe senso creare un video senza una durata come
    * non avrebbe senso modificarla in seguito.
     */

    //ATTRIBUTI
    private int durata;
    private int volume = 5;
    private int luminosita = 5;

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

        String volumeString = "";
        for (int i = 0; i < volume; i++) {
            volumeString = volumeString + "!";
        }

        String luminositaString = "";
        for (int i = 0; i < luminosita; i++) {
            luminositaString = luminositaString + "*";
        }

        for (int i = 0; i < this.durata; i++) {

            System.out.println(this.titolo + " - " + volumeString + " - " + luminositaString);
        }
    }

    //METODI PER REGOALRE VOLUME E LUMINOSITA
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
