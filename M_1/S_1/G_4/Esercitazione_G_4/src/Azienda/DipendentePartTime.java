package Azienda;

import Azienda.enums.TipoDipartimento;

public class DipendentePartTime extends Dipendente{
    public DipendentePartTime(String matricola, float stipendio, TipoDipartimento dipartimento) {
        super(matricola, stipendio, dipartimento);
    }

    @Override
    public float calculatesalary() {
        float ore = 100;
        float stipendio = this.getStipendio();
        return stipendio * ore;
    }

    @Override
    public void checkIn() {
        System.out.println("Sono un dipendente parTime e il mio turno di lavoro è inziato");
    }
}
