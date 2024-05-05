package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import it.epicode.enums.Frequency;
import jakarta.persistence.*;

@Entity
@Table(name = Tables.Names.MAGAZINES)
@DiscriminatorValue(Tables.Discriminators.MAGAZINES)
public class Magazine extends Item{
    @Enumerated( EnumType.STRING)
    private Frequency frequency;

    public Magazine(){}

    public Magazine(Frequency frequency, String isbn, String title, int releaseDate, int pages) {
        super(isbn, title, releaseDate, pages);
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Magazine{" + "frequency=" + frequency + super.toString() + '}';
    }
}
