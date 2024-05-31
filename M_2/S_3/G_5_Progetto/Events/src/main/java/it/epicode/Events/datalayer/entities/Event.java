package it.epicode.Events.datalayer.entities;

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
    @Column(length = 250, nullable = false, unique = true)
    private String description;

    @Builder.Default
    private LocalDate date = LocalDate.now();

    private Place place;//FORSE SAREBBE MEGLIO FAR EUN ENUM CON 10 LOCALITA

    @Column(nullable = false)
    private int maxPrticipants;

    @Column(nullable = false)

    @ManyToOne
    private User manager;

}
