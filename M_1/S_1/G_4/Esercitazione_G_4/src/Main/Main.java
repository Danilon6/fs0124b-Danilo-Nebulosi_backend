package Main;

import Azienda.*;
import Azienda.enums.TipoDipartimento;
import Azienda.Interfaces.DichiarareInizioTurno;

public class Main {

    public static void main(String[] args) {

        Dipendente dipendente1 = new DipendenteFullTime("01", 20, TipoDipartimento.PRODUZIONE );
        Dipendente dipendente2 = new DipendentePartTime("02", 20, TipoDipartimento.AMMINASTRAZIONE );
        Dipendente dipendente3 = new Dirigente("03", 50, TipoDipartimento.VENDITE );
        Volontario volontario1 = new Volontario("Volontario1", 45, "il mio CV");

        Dipendente[] dipendenti = {dipendente1, dipendente2, dipendente3};

        float stipendioTotale = 0;

        for (int i = 0; i < dipendenti.length; i++) {
            stipendioTotale +=  dipendenti[i].calculatesalary();;
            System.out.println(stipendioTotale);
        }

        DichiarareInizioTurno[] dipendentiEVolontari = {dipendente1, dipendente2, dipendente3, volontario1};


        for (var dipendente : dipendentiEVolontari){
            dipendente.checkIn();
        }


    }
}