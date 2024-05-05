package it.epicode.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Item borrowed_item;
    private LocalDate loan_start_date;
    private LocalDate return_date;
    private LocalDate actual_return_date;

    public Loan(){}

    public Loan(User user, Item borrowed_item, LocalDate loan_start_date) {
        this.user = user;
        this.borrowed_item = borrowed_item;
        this.loan_start_date = loan_start_date;
        this.return_date = loan_start_date.plusDays(30);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getBorrowed_item() {
        return borrowed_item;
    }

    public void setBorrowed_item(Item borrowed_item) {
        this.borrowed_item = borrowed_item;
    }

    public LocalDate getLoan_start_date() {
        return loan_start_date;
    }

    public void setLoan_start_date(LocalDate loan_start_date) {
        this.loan_start_date = loan_start_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public LocalDate getActual_return_date() {
        return actual_return_date;
    }

    public void setActual_return_date(LocalDate actual_return_date) {
        this.actual_return_date = actual_return_date;
    }

    @Override
    public String toString() {
        return "Loan{" + "user=" + user + ", borrowed_item=" + borrowed_item + ", loan_start_date=" + loan_start_date + ", return_date=" + return_date + ", actual_return_date=" + actual_return_date + super.toString() +'}';
    }
}
