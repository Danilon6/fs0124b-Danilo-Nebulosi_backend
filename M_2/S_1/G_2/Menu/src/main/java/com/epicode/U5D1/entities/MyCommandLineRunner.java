package com.epicode.U5D1.entities;

import com.epicode.U5D1.U5D1Application;
import com.epicode.U5D1.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    Menu menu;

    @Autowired
    @Qualifier("table1")
    Table table1;

    @Autowired
    @Qualifier("water")
    Item water;


    @Autowired
    @Qualifier("pizza_margherita")
    Item pizza_margherita;

    @Value("${costPerSeat.value}")
    int seatCost;


    @Override
    public void run(String... args) throws Exception {

         List<Item> itemList = new ArrayList<>();
         itemList.add(water);
         itemList.add(pizza_margherita);
       var order = Order.builder()
               .withOrderNumber("1")
               .withOrderStatus(OrderStatus.SERVED)
               .withAcquisitonDate(LocalDate.now())
               .withTable(table1)
               .withSeatCost(seatCost)
               .withNumberOfSeats(3)
               .withItemList(itemList)
               .build();


       log.info(order.toString());
    }
}
