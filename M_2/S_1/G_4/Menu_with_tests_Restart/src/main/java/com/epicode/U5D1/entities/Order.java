package com.epicode.U5D1.entities;

import com.epicode.U5D1.enums.OrderStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Order {
    private List<Item> itemList = new ArrayList<>();
    private int seatCost;
    private String orderNumber;
    private int numberOfSeats;
    private OrderStatus orderStatus;
    private LocalDate AcquisitonDate;
    private Table table;

    @ToString.Include(name = "TotalePrice")
    public double getTotalAmount() {
        double totalAmount = 0;
        for (Item item : itemList) {
            totalAmount += item.getPrice();
        }
        return totalAmount += this.numberOfSeats * seatCost;
    }

}
