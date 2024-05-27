package it.epicode.DevicesManagment.datalayer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseEntity {

    private LocalDate createdAt;

    public BaseEntity() {
        this.createdAt = LocalDate.now();
    }
}
