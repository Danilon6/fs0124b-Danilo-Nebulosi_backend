package it.epicode;

import it.epicode.elemento_multimediale.ElementoMultimediale;
import it.epicode.elemento_multimediale.immagine.Immagine;
import it.epicode.elemento_multimediale.registrazione_audio.RegistrazioneAudio;
import it.epicode.elemento_multimediale.video.Video;
import it.epicode.menu.MenuOption;

import java.util.Scanner;

public class Main {
    /*
    * SCOPO: Organizzare classi astratte, interfacce e classi concrete
    * al fine di implementare un lettore multimediae che memorizza 5 elementi
    * creati con valori letti da tastiera in un array.
    * Il lettore deve chiedere all'utente quale oggetto riprodurre/visualizzare
    * l'Utente deve digitare un intero da 1 a 5 oppure 0 per uscire
    *
    *
    * In questa applicazione java saranno presenti Elementi multimediali riproducibili o meno
    * tutti caratterizzati dalla presenza di un titolo,
    * in particolare tra gli ogetti multimediali troviamo:
    * - Immagine,
    * - Video,
    * - Registrazione Audio;
    *
    * La registrazione audio e il video possono essere rirpodotti
    * mentre l'immagine può essere visualizzata .
    * ///////////////////////////////////////////////////////////////////////////
    *
    * La registrazione audio e il video implementeranno un'interfaccia PuoEssereRiprodotto.
    *
    * L'interfaccia in questione avrà 2 attributi:
    * - durata di tipo int,
    * -volume di tipo int;
    * e 3 metodi:
    * - play(),
    * - AlzaVolume(),
    * - abbassaVolume();
    *
    * Data l'esigenza per la registrazione audio e l'immagine di regolare la luminosità,
    * ci sarà una seconda interfaccia RegolaLuminosità che avrà 1 attributo:
    * - luminosità di tipo int;
    * e 2 metodi:
    * - AlzaLuminosita(),
    * - abbassaLuminosita();
    *
    *
    * l'immagine inoltre per essere riprodotta avrà un metodo show()
    */


    /* IMPLEMENTAZIONE MENU
    * 1
    * L'UTENTE DEVE AVERE LA POSSIBILITA TRAMITE UN MAIN MENU DI SCEGLIERE TRA DUE OPZIONI:
    * OPTION 1 = ENTRARE IN UN MENU PER SCEGLIERE QUALE TRA 5 OGGETTI DI UN ARRAY VISUALIZZARE
    * OPTION 2 = CREARE UN OGGETTO
    *
    * 2
    * SE L'UTENTE SCEGLIE L'OPZIONE 1 ACCEDE AD UN SUB_MENU_1 CON 5 OPZIONI
    * L'UTENTE PUON SCEGLIERE QUALE OGGETTO VISUALIZZARE TRAMITE INDICE
    * OGNI NUMERO (DA 1 A 5) CORRISPONDE ALL'INDICE DELL'ARRAY -1
    * 2.1
    * SE L'OGGETTO SCELTO NON è PRESENTE VIENE RESTITUITO NULL
    * 2.2
    * SE L'OGGETTO E PRESENTE VIENE MOSTRATO SOTTO FORMA DI STRINGA
    *
    * IN ENTRAMBI I CASI L'UTENTE VIENE RIPORTATO AL MAIN MENU
    *
    * 3 SE L'UTENTE SCEGLIE L'OPZIONE 2 ACCEDE AD UN SUB_MENU_2 CON 3 OPZIONI:
    * SUB_MENU_2_OPTION_1 = CREA UN'IMMAGINE,
    * SUB_MENU_2_OPTION_2 = CREA UN VIDEO,
    * SUB_MENU_2_OPTION_3 = CREA UNA REGISTRAZIONE AUDIO;
    *
    * 3.1
    * FATTA LA SCELTA L'UTENTE RICEVE UN MESSAGGIO DI AVVENUTA CREAZIONE DELL'OGGETTO
    * E VIENE RIPORTATO AL MAIN MENU
    *
     */

    //GESTIONE SUB-MENU-VISUALIZZAZIONE OGGETTI
    private static void handleMainMenuOption1(Scanner scanner) {
        System.out.println("Hai selezionato l'opzione 1 del menu principale.");
        System.out.println("*** SUB-MENU-VISUALIZZAZIONE***:");
        System.out.println("1. Visualizza il primo oggetto");
        System.out.println("2. Visualizza il secondo oggetto");
        System.out.println("3. Visualizza il terzo oggetto");
        System.out.println("4. Visualizza il quarto oggetto");
        System.out.println("5. Visualizza il quinto oggetto");
        System.out.print("Scelta: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                returnToMainAndReadObject(choice);
                break;
            case 2:
                returnToMainAndReadObject(choice);
                break;
            case 3:
                returnToMainAndReadObject(choice);
                break;
            case 4:
                returnToMainAndReadObject(choice);
                break;
            case 5:
                returnToMainAndReadObject(choice);
                break;

        }
    }

    //GESTIONE SUB-MENU-CREAZIONE OGGETTI
    private static void handleMainMenuOption2(Scanner scanner) {
        System.out.println("Hai selezionato l'opzione 2 del menu principale.");
        // Implementa il menu secondario
        System.out.println("*** SUB-MENU-CREAZIONE***:");
        System.out.println("1. Crea un'immagine");
        System.out.println("2. Crea un video");
        System.out.println("3. Crea una registrazione audio");
        System.out.print("Scelta: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Immagine img1 = new Immagine("img1");
                controlloQuantita(img1);
                returnToMain();
                break;
            case 2:
                Video video1 = new Video("video1", 5);
                controlloQuantita(video1);
                returnToMain();
                break;
            case 3:
                RegistrazioneAudio record1 = new RegistrazioneAudio("record1", 5);
                controlloQuantita(record1);
                returnToMain();
                break;
        }
    }

    //METODO PER RITORNARE AL MAIN MENU E RESITUIRE L'OGGETTO CHE SI VUOLE LEGGERE DAL SUB MENU DI VISUALIZZAZIONE OGGETTI
    private static void returnToMainAndReadObject(int choice) {
        System.out.println(elementiMultimediali[choice -1]);
        main(new String[]{});
    }

    //METODO PER RITORNARE AL MAIN MENU DAL SUB MENU CREAZIONE OGGETTI DANDO CONFERMA DELLA CREAZIONE
    private static void returnToMain() {
        System.out.println("Il tuo oggetto è stato creato con successo");
        main(new String[]{});
    }

    //AGGIUNGO SOLO GLI ULTIMI 5 OGGETTI CREATI ALL'ARRAY DI ELEMENTI MULTIMEDIALI
    private static void controlloQuantita(ElementoMultimediale el){
        if (currentObj == 4) {
            // copia gli ultimi 4 scartando il primo
            for (int i = 0; i < 4; ++i) {
                elementiMultimediali[i] = elementiMultimediali[i + 1];
            }
        }
        // aggiunge l'oggetto
        elementiMultimediali[currentObj] = el;
        // se sono stati creati meno di 5 oggetti incremento
        if (currentObj < 4)
            ++currentObj;
    }


    private static ElementoMultimediale[] elementiMultimediali = new ElementoMultimediale[5];
    private static int currentObj;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        MenuOption selectedOption = MenuOption.MAIN_MENU_OPTION_2;
        do {
            System.out.println("*** MAIN MENU ***");
            System.out.println("1. VISUALIZZA OGGETTI");
            System.out.println("2. CREA OGGETTI");
            System.out.println("CHOOSE");
            int choice = scanner.nextInt();

            if (choice == 1) {
                selectedOption = MenuOption.MAIN_MENU_OPTION_1;
            } else if (choice == 2) {
                selectedOption = MenuOption.MAIN_MENU_OPTION_2;
            } else {
                System.out.println("Your choice is not valid. Try again.");
            }
            switch (selectedOption) {
                case MAIN_MENU_OPTION_1:
                    handleMainMenuOption1(scanner);
                    break;
                case MAIN_MENU_OPTION_2:
                    handleMainMenuOption2(scanner);
                    break;
            }
        }while (selectedOption != MenuOption.MAIN_MENU_OPTION_1 && selectedOption != MenuOption.MAIN_MENU_OPTION_2) ;

            scanner.close();


        //TEST CLASSE REGISTRAZIONE AUDIO
        /* RegistrazioneAudio record1 = new RegistrazioneAudio("record1",2);
            record1.alzaVolume();
            record1.alzaVolume();
            record1.play();
        */


        //TEST CLASSE VIDEO
        /* Video video1 = new Video("video1", 3);
            video1.alzaLuminosita();
            video1.alzaLuminosita();
            video1.setLuminosita(10);
            video1.setVolume(10);
            video1.abbassaVolume();
            video1.abbassaVolume();
            video1.alzaVolume();
            video1.play();
         */

        //TEST CLASSE IMMAGINE
        /*
            Immagine img1 = new Immagine("img1");
            img1.alzaLuminosita();
            img1.abbassaLuminosita();
            img1.abbassaLuminosita();
            img1.alzaLuminosita();
            img1.show();
        */

    }
}