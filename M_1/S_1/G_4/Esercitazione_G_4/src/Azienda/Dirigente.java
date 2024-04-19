package Azienda;

import Azienda.enums.TipoDipartimento;

public class Dirigente extends Dipendente{
    public Dirigente(String matricola, float stipendio, TipoDipartimento dipartimento) {
        super(matricola, stipendio, dipartimento);
    }

    @Override
    public float calculatesalary() {
        float ore = 160;
        float stipendio = this.getStipendio();
        return stipendio * ore;
    }

    @Override
    public void checkIn() {
        System.out.println("Sono un dirigente e il mio turno di lavoro è inziato");
    }
}
