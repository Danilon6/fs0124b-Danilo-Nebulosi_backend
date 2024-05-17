package it.epicode.booking.entities;

import it.epicode.booking.enums.Cities;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Building extends BaseEntity{
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private Cities city;
    @OneToMany(mappedBy = "building")
    private List<WorkStation> workStations = new ArrayList<>();
}
