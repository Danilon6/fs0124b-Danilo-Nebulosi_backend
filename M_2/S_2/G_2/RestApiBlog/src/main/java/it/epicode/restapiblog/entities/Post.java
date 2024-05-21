package it.epicode.restapiblog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Table(name = "post")
public class Post extends BaseEntity{

    @Column(nullable= false, length = 25)
    private String category;
    @Column(nullable= false, length = 100)
    private String title;

    @Column(nullable= false, length = 150)
    private String cover;
    @Column(nullable= false, length = 500)
    private String content;

    @Column( name = "reading_time", nullable= false)
    private int readingTime;


}
