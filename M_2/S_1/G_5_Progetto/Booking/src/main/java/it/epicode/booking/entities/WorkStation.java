package it.epicode.booking.entities;

import it.epicode.booking.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class WorkStation extends BaseEntity{

    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;
    private int max_seats;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "workStation")
    private List<Booking> bookings = new ArrayList<>();
}
