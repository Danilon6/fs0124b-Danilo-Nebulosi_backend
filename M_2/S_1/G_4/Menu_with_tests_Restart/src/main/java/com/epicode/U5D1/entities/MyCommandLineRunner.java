package com.epicode.U5D1.entities;

import com.epicode.U5D1.repository.DrinkRepository;
import com.epicode.U5D1.enums.OrderStatus;
import com.epicode.U5D1.repository.MenuRepository;
import com.epicode.U5D1.repository.PizzaRepository;
import com.epicode.U5D1.repository.ToppingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    //CREO TUTTI I TOPPINGS
    @Autowired
    @Qualifier("toppings_tomato")
    Topping tomato;

    @Autowired
    @Qualifier("toppings_cheese")
    Topping cheese;

    @Autowired
    @Qualifier("toppings_ham")
    Topping ham;

    @Autowired
    @Qualifier("toppings_pineapple")
    Topping pineapple;

    @Autowired
    @Qualifier("toppings_salami")
    Topping toppingSalami;

    //CREO TUTTE LE PIZZE
    @Autowired
    @Qualifier("pizza_margherita")
    Pizza margherita;

    @Autowired
    @Qualifier("hawaiian_pizza")
    Pizza hawaiian;

    @Autowired
    @Qualifier("salami_pizza")
    Pizza pizzaSalami;

    @Autowired
    @Qualifier("salami_pizza_xl")
    Pizza salamiXL;

    //CREO TUTTI I DRINK
    @Autowired
    @Qualifier("lemonade")
    Drink lemonade;

    @Autowired
    @Qualifier("water")
    Drink water;

    @Autowired
    @Qualifier("wine")
    Drink wine;


    //ISTANZIO I REPOSITORY
    @Autowired
    DrinkRepository drink;

    @Autowired
    PizzaRepository pizza;

    @Autowired
    ToppingRepository topping;

    @Autowired
    MenuRepository menuR;

    @Autowired
    @Qualifier("table1")
    Table table1;


    @Value("${costPerSeat.value}")
    int seatCost;


    @Override
    public void run(String... args) throws Exception {

        topping.save(tomato);
        topping.save(cheese);
        topping.save(ham);
        topping.save(pineapple);
        topping.save(toppingSalami);

        pizza.save(margherita);
        pizza.save(hawaiian);
        pizza.save(pizzaSalami);
        pizza.save(salamiXL);

        drink.save(lemonade);
        drink.save(water);
        drink.save(wine);

        log.info("Drink con calorie > 1000");
        drink.findByCaloriesGreaterThan(100).forEach(d-> log.info("{}", d));

        log.info("Drink con prezzo compreso tra x e x");
        drink.findByPriceBetween(5,10).forEach(d-> log.info("{}", d));

        log.info("Drink con id 10 = {}", drink.trovaPerId(10L));
            List<Pizza> pizzaList = new ArrayList<>();
            List<Drink> drinkList = new ArrayList<>();
            List<Topping> toppingsList = new ArrayList<>();

            pizzaList.add(margherita);
            pizzaList.add(hawaiian);
            pizzaList.add(pizzaSalami);
            pizzaList.add(salamiXL);

            drinkList.add(lemonade);
            drinkList.add(water);
            drinkList.add(wine);

            toppingsList.add(tomato);
            toppingsList.add(cheese);
            toppingsList.add(toppingSalami);
            toppingsList.add(ham);
            toppingsList.add(pineapple);

            Menu menu = new Menu(pizzaList, drinkList, toppingsList);

        menuR.save(menu);
        // log.info(order.toString());
//        List<Item> itemList = new ArrayList<>();
//         itemList.add(water);
//         itemList.add(margherita);
//       var order = Order.builder()
//               .withOrderNumber("1")
//               .withOrderStatus(OrderStatus.SERVED)
//               .withAcquisitonDate(LocalDate.now())
//               .withTable(table1)
//               .withSeatCost(seatCost)
//               .withNumberOfSeats(3)
//               .withItemList(itemList)
//               .build();
    }
}
