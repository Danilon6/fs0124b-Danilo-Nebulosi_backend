package com.epicode.U5D1.entities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
public class Order {
    private int orderNumber;
    private List<Item> itemList = new ArrayList<>();
    private OrderStatus orderStatus;
    private int numberOfSeats;
    private LocalDate AcquisitonDate;

    private int seatCost;

    private double totalAmount;

    public Order(int orderNumber, List<Item> itemList, OrderStatus orderStatus, int numberOfSeats, LocalDate acquisitonDate) {
        this.orderNumber = orderNumber;
        this.itemList = itemList;
        this.orderStatus = orderStatus;
        this.numberOfSeats = numberOfSeats;
        AcquisitonDate = acquisitonDate;

    }

    public double getTotalAmount() {
        this.totalAmount = 0;
        for (Item item : itemList) {
            totalAmount += item.getPrice();
        }
        return totalAmount += this.numberOfSeats * seatCost;
    }

}
