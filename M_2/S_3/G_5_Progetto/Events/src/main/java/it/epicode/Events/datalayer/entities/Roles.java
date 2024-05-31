package it.epicode.Events.datalayer.entities;

import it.epicode.Events.datalayer.entities.enums.RolesType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class Roles extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "roles_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolesType roleType;
}
