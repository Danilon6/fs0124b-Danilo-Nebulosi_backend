package it.epicode.entities;

import it.epicode.dao.JPAPartecipazioni;
import it.epicode.enums.TypeEvent;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (
        name="events"
)
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String description;
    @Enumerated(EnumType.STRING)
    TypeEvent type;
    @Column(nullable = false)
    private int maxParticipants;

    @ManyToOne
    @JoinColumn(
            name = "location_id"
    )
    private Location location;

    @OneToMany(
            mappedBy = "event"
    )
    private List<Partecipazione> listaPartecipazioni = new ArrayList<>();
    public Evento() {
    }

    public Evento(String title, LocalDate date, String description, TypeEvent type, int maxParticipants, Location location) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.type = type;
        this.maxParticipants = maxParticipants;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeEvent getType() {
        return type;
    }

    public void setType(TypeEvent type) {
        this.type = type;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", title='" + title + '\'' + ", date=" + date + ", description='" + description + '\'' + ", type=" + type + ", maxParticipants=" + maxParticipants + '}';
    }
}
