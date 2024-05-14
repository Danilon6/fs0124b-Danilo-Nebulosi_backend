package com.epicode.U5D1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class Table {
    private int tableNumber;
    private Order order;
    private TableStatus tableStatus;
    private int maxSeats;

    public Table(int tableNumber, Order order, TableStatus tableStatus, int maxSeats) {
        this.tableNumber = tableNumber;
        this.order = order;
        this.tableStatus = tableStatus;
        this.maxSeats = maxSeats;
    }
}
