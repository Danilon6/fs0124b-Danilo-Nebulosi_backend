package it.epicode;

import jakarta.persistence.*;

@Entity
@Table(name = "Persone")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String nome;
    String cognome;

    public Persona() {
    }

    public Persona(long id, String nome, String cognome) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "ClasseDiProva{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                '}';
    }
}
