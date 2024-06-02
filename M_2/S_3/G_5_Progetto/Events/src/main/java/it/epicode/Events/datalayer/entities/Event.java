package it.epicode.Events.datalayer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.Events.datalayer.entities.enums.Place;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Event extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "event_seq")
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String title;
    @Column(length = 250, nullable = false)
    private String description;

    @Builder.Default
    private LocalDate date = LocalDate.now();

    private Place place;

    @Column(nullable = false)
    private int maxParticipants;

}
