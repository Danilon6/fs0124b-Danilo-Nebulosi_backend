package it.epicode.menu;

import it.epicode.menu.enums.DrinkName;
import it.epicode.menu.enums.ToppingName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyAppConfig {

    @Bean
    @Scope("singleton")
    Toppings ToppingSingleton(){
        var t = new Toppings();
        //CONFIGURO LA LISTA DI INGREDIENTI CON LE RELATIVE CALORIE
        var toppingList = t.getToppingList();
        toppingList.put(ToppingName.TOMATO, 10);
        toppingList.put(ToppingName.MOZZARELLA, 20);
        toppingList.put(ToppingName.HAM, 30);
        toppingList.put(ToppingName.ONIONS, 40);
        toppingList.put(ToppingName.CHEESE, 50);
        toppingList.put(ToppingName.PINEAPPLE, 60);
        toppingList.put(ToppingName.SALAMI, 70);
        //CONFIGURO LA LISTA DI INGREDIENTI CON I RELATIVI PREZZI
        var toppingPriceList = t.getToppingPriceList();
        toppingPriceList.put(ToppingName.TOMATO, 2.50);
        toppingPriceList.put(ToppingName.MOZZARELLA, 2.50);
        toppingPriceList.put(ToppingName.HAM, 2.50);
        toppingPriceList.put(ToppingName.ONIONS, 3.99);
        toppingPriceList.put(ToppingName.CHEESE, 1.99);
        toppingPriceList.put(ToppingName.PINEAPPLE, 20.99);
        toppingPriceList.put(ToppingName.SALAMI, 3.50);
        return t;
    }

    @Bean
    @Scope("singleton")
    Drinks drinksSingleton(){
        var d = new Drinks();

        //CONFIGURO LA LISTA DI DRINK CON LE RELATIVE CALORIE
        var drinkList = d.getDrinkList();

        drinkList.put(DrinkName.LEMONADE, 128);
        drinkList.put(DrinkName.WATER, 0);
        drinkList.put(DrinkName.WINE, 607);

        //CONFIGURO LA LISTA DI DRINK CON I RELATIVI PREZZI
        var drinkPriceList = d.getDrinkPriceList();

        drinkPriceList.put(DrinkName.LEMONADE,1.29);
        drinkPriceList.put(DrinkName.WATER,1.29);
        drinkPriceList.put(DrinkName.WINE,7.49);

        return d;
    }


    @Bean
    @Scope("prototype")
    Pizza PizzaPrototype(Toppings topping){
        var p = new Pizza();
        p.addTopping(ToppingName.TOMATO, topping);
        p.addTopping(ToppingName.MOZZARELLA, topping);
        return p;
    }

    @Bean
    @Scope("singleton")
    Menu menu(Pizza pizza){
        var m = new Menu();
        var drinksList = m.getAllDrinks();
        drinksList.add(drinksSingleton());
        m.setAllDrinks(drinksList);
        return m;
    }
}
