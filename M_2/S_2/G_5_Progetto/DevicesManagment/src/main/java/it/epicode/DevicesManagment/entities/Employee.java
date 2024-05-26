package it.epicode.DevicesManagment.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Employee extends BaseEntity{
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 20, nullable = false, unique = true)
    private String username;
    @Column(length = 40, nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private List<Device> devices;

    private String profileImagePath;
}
