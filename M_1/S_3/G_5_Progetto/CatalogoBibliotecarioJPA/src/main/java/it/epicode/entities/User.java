package it.epicode.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity{
    
    private String name;
    private String last_name;
    private LocalDate birth_date;
    private int card_number;
    @OneToMany(mappedBy = "user")
    private List<Loan> allLoans = new ArrayList<>();

    public User(){}

    public User(String name, String last_name, LocalDate birth_date, int card_number) {
        this.name = name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.card_number = card_number;
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

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", last_name='" + last_name + '\'' + ", birth_date=" + birth_date + ", card_number=" + card_number + super.toString() + '}';
    }
}
