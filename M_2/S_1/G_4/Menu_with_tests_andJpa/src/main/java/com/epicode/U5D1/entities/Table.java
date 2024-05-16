package com.epicode.U5D1.entities;

import com.epicode.U5D1.enums.TableStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(setterPrefix = "with")
public class Table {

    @Id
    @GeneratedValue
    private Long id;

    private int tableNumber;
    private TableStatus tableStatus;
    private int maxSeats;

}
