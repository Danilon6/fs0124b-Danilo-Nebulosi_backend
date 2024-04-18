package Azienda;

public abstract class Dipendente
implements TurnoDiLavoro{
    private String matricola;
    private float stipendio;
    private TipoDipartimento dipartimento;

    public TipoDipartimento getDipartimento() {
        return dipartimento;
    }

    public float getStipendio() {
        return stipendio;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setDipartimento(TipoDipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

    public Dipendente(String matricola, float stipendio, TipoDipartimento dipartimento){
        this.matricola = matricola;
        this.stipendio = stipendio;
        this.dipartimento = dipartimento;
    }

    public abstract float calculatesalary();

}
