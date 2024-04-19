package it.epicode.elementoMultimediale.registrazioneAudio;

import it.epicode.elementoMultimediale.ElementoMultimediale;
import it.epicode.interfaces.PuoEssereRiprodotto;
import it.epicode.interfaces.PuoRegolareLaLuminosita;

public class RegistrazioneAudio extends ElementoMultimediale
implements PuoEssereRiprodotto {

    /*
     * L'istanza di una registrazioneAudio prevede necessarimente una durata,
     * non avrebbe senso creare una registrazione audio senza una durata come
     * non avrebbe senso modificarla in seguito.
     */

    //ATTRIBUTI
    private int durata;
    private int volume = 0;

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

    //METODO PER RIPRODURRE UN VIDEO ED UNA REGISTRAZIONE AUDIO
    @Override
    public void play() {
        //STRINGBUILDER FUNZIONA MA NON LO ABBIAMO FATTO
        //StringBuilder volumeString = new StringBuilder();
        //for (int i = 0; i < volume; i++) {
        //    volumeString.append("!");
        //}

        String volumeString = "";
        for (int i = 0; i < volume; i++) {
            volumeString = volumeString + "!";
        }
        for (int i = 0; i < this.durata; i++) {

            System.out.println(this.titolo + " - " + volumeString);
        }
    }

    //METODI PER REGOALRE IL VOLUME
    @Override
    public void alzaVolume() {
        if (this.volume == 10) {
            System.out.println("Il volume è già al massimo");
            return;
        }
        this.volume += 1;
    }

    @Override
    public void abbassaVolume() {
        if (this.volume == 0) {
            System.out.println("Il volume è già al minimo");
            return;
        }
        this.volume -= 1;
    }

    @Override
    public String toString() {
        return "RegistrazioneAudio [titolo=" + titolo + " durata="+ durata +"]";
    }

}
