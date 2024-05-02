package it.epicode.entities;

import it.epicode.enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "persona"
)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(
            length = 25,
            nullable = false
    )
    private String name;
    @Column(
            length = 25,
            nullable = false
    )
    private String last_name;
    @Column(
            length = 50
    )
    private String email;
    @Column(
            nullable = false
    )
    private Date birthday_date;
    @Enumerated(
            EnumType.STRING
    )
    @Column(
            nullable = false
    )
    private Genre genre;

    @OneToMany(
            mappedBy = "persona"
    )
    private List<Partecipazione> listapartecipazione = new ArrayList<>();

    public Persona() {
    }

    public Persona(String name, String last_name, String email, Date birthday_date, Genre genre) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.birthday_date = birthday_date;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday_date() {
        return birthday_date;
    }

    public void setBirthday_date(Date birthday_date) {
        this.birthday_date = birthday_date;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Partecipazione> getListapartecipazione() {
        return listapartecipazione;
    }

    public void setListapartecipazione(List<Partecipazione> listapartecipazione) {
        this.listapartecipazione = listapartecipazione;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", name='" + name + '\'' + ", last_name='" + last_name + '\'' + ", email='" + email + '\'' + ", birthday_date=" + birthday_date + ", genre=" + genre + ", listapartecipazione=" + listapartecipazione + '}';
    }
}
