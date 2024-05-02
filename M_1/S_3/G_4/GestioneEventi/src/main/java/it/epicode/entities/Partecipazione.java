package it.epicode.entities;

import it.epicode.enums.Stato;
import jakarta.persistence.*;

@Entity
@Table(
        name="partecipazione"
)
public class Partecipazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento event;
    @Enumerated(
            EnumType.STRING
    )
    @Column(
            nullable = false
    )
    private Stato stato;

    public Partecipazione() {
    }

    public Partecipazione(Persona persona, Evento event, Stato stato) {
        this.persona = persona;
        this.event = event;
        this.stato = stato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Evento getEvent() {
        return event;
    }

    public void setEvent(Evento event) {
        this.event = event;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Partecipazione{" + "id=" + id + ", persona=" + persona + ", event=" + event + ", stato=" + stato + '}';
    }
}
