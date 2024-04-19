package Azienda;

import Azienda.Interfaces.DichiarareInizioTurno;

public class Volontario
implements DichiarareInizioTurno {
    String nome;
    int età;
    String CV;

    public Volontario(String nome, int età, String CV) {
        this.nome = nome;
        this.età = età;
        this.CV = CV;
    }

    @Override
    public void checkIn() {
        System.out.println("Sono " + this.nome + " e il mio turno di lavoro è inziato");
    }
}
