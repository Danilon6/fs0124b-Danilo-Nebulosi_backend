package com.epicode.U5D1.entities;

import com.epicode.U5D1.enums.TableStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class Table {
    private int tableNumber;
    private TableStatus tableStatus;
    private int maxSeats;

}
