package it.epicode.DevicesManagment.entities;

import it.epicode.DevicesManagment.entities.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "devices")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "device_type", discriminatorType = DiscriminatorType.STRING)
public class Device extends BaseEntity{
    @Column(length = 100, nullable = false)
    private String model;
    @Column(length = 50, nullable = false)
    private String brand;
    @EqualsAndHashCode.Include
    @Column(length = 40, nullable = false, unique = true)
    private Long serialNumber;
    @Column(nullable = false)
    private double screenSize;
    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
    @ManyToOne
    @JoinColumn(name = "assignedTo")
    private Employee employee;
}
