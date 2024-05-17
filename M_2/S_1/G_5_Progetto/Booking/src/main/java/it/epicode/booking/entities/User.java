package it.epicode.booking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class User extends BaseEntity{
    private String username;
    private String name;
    private String last_name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

}
