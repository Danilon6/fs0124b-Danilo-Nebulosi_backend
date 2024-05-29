package it.epicode.DevicesManagment.datalayer.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq")
    private Long id;
    @Column(length = 50, nullable = false)
    private String firstName;
    @Column(length = 50, nullable = false)
    private String lastName;
    @Column(length = 20, nullable = false, unique = true)
    private String username;
    @Column(length = 40, nullable = false, unique = true)
    private String email;
    @Column(length = 125, nullable = false)
    private String password;
    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private List<Device> devices;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<RoleEntity> roles = new ArrayList<>();

    private String profileImagePath;



}
