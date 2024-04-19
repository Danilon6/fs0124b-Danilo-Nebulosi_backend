package it.epicode.elemento_multimediale.registrazione_audio;

import it.epicode.elemento_multimediale.ElementoMultimediale;
import it.epicode.interfaces.PuoEssereRiprodotto;

public class RegistrazioneAudio extends ElementoMultimediale
implements PuoEssereRiprodotto {

    /*
     * L'istanza di una registrazioneAudio prevede necessarimente una durata,
     * non avrebbe senso creare una registrazione audio senza una durata
     * come non avrebbe senso modificarla in seguito.
     */

    //ATTRIBUTI
    private int durata;
    private int volume = 5;

    //COSTRUTTORE
    public RegistrazioneAudio(String t, int d ) {
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

    //METODO PER RIPRODURRE UNA REGISTRAZIONE
    @Override
    public void play() {
        //STRINGBUILDER FUNZIONA MA NON LO ABBIAMO FATTO
        //StringBuilder volumeString = new StringBuilder();
        //for (int i = 0; i < volume; i++) {
        //    volumeString.append("!");
        //}

        // CICLO PER STAMPARE IL TITOLO TANTE VOLTE QUANTO LA DURATA E AGGIUNGERE
        // '!' TANTE VOLTE QUANTO IL VALORE DI VOLUME
        for (int i = 0; i < this.durata; i++) {

            System.out.print(this.titolo + " - ");

            for (int j = 0; j < volume; j++) {
                System.out.print("!");
            }

            System.out.println(" ");
        }
    }

    //METODI PER REGOLARE IL VOLUME
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
        --this.volume;
    }

    //OVERRIDE DEL METODO TOSTRING
    @Override
    public String toString() {
        return "RegistrazioneAudio [titolo=" + titolo + " durata="+ durata +"]";
    }

}
