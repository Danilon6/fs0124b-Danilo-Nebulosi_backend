package Azienda;

public class DipendenteFullTime extends Dipendente {


    public DipendenteFullTime(String matricola, float stipendio, TipoDipartimento dipartimento) {
        super(matricola, stipendio, dipartimento);
    }


    @Override
    public float calculatesalary() {
        float ore = 160;
        float stipendio = this.getStipendio();
        return stipendio * ore;
    }

}
