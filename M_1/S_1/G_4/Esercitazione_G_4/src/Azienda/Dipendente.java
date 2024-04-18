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

    @Override
    public void checkIn() {
       if (this instanceof Dirigente){
           System.out.println("Sono un dirigente e il mio turno di lavoro è inziato");
       } else if (this instanceof DipendenteFullTime) {
           System.out.println("Sono un dipendente fullTime e il mio turno di lavoro è inziato");
        } else if (this instanceof DipendentePartTime) {
           System.out.println("Sono un dipendente parTime e il mio turno di lavoro è inziato");
        }
    }

    public abstract float calculatesalary();

}
