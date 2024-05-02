package it.epicode.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "location"
)
public class Location {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    @Column(
            length = 25,
            nullable = false
    )
    private String nome;
    @Column(
            length = 25,
            nullable = false
    )
    private String citta;

    public Location() {
    }

    public Location(String nome, String citta) {
        this.nome = nome;
        this.citta = citta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", nome='" + nome + '\'' + ", citta='" + citta + '\'' + '}';
    }
}
