package it.epicode.restapiblog.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Table(name = "author")
public class Author extends BaseEntity{

    @Column(name = "first_name", nullable= false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable= false, length = 50)
    private String lastName;
    @Column(nullable= false, length = 75)
    private String email;
    @Column(name = "birt_date", nullable= false)
    private LocalDate birthDate;
    @Column(nullable= false)
    private String avatar; //https://ui-avatars.com/api/?name=firstName+lastName
}
