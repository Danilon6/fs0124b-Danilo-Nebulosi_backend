package it.epicode.Esercizio3_e_commerce;

public class Cliente {
    private int codiceCliente;
    private String nome;
    private String cognome;
    private String email;
    private String dataDiIscrizione;


    public Cliente(int a, String b, String c, String d, String e){
        this.codiceCliente = a;
        this.nome = b;
        this.cognome = c;
        this.email = d;
        this.dataDiIscrizione = e;
    }

    public void datiUtente(){
        System.out.println("L'utente "+ this.nome + this.cognome + "è registarto con email: " + this.email);
    }
}
