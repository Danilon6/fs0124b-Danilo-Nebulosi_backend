package Azienda;

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

}
