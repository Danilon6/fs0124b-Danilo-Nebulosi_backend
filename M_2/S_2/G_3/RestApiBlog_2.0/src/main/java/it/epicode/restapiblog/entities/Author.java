package it.epicode.restapiblog.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Table(name = "author", indexes = { @Index(columnList = "email", unique = true)})
public class Author extends BaseEntity{

    @Column(name = "first_name", nullable= false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable= false, length = 50)
    private String lastName;
    @Column(nullable= false, length = 75)
    private String email;
    @Column(name = "birth_date", nullable= false)
    private String birthDate;
    private String avatar; //https://ui-avatars.com/api/?name=firstName+lastName

    @OneToMany(mappedBy = "author")
    private List<Post> posts;
}
