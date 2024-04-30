package it.epicode.data;

import it.epicode.TypeEvent;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (
        name="events"
)
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(
            length = 25,
            nullable = false
    )
    String title;

    @Column(
            nullable = false
    )
    LocalDate date;
    @Column(
            nullable = false
    )
    String description;
    @Enumerated(
            EnumType.STRING
    )
     TypeEvent type;
    @Column(
            nullable = false
    )
    int maxParticipants;

    public Evento() {
    }

    public Evento(String title, LocalDate date, String description, TypeEvent type, int maxParticipants) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.type = type;
        this.maxParticipants = maxParticipants;
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
