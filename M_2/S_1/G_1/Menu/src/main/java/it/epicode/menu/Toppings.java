package it.epicode.menu;

import it.epicode.menu.enums.ToppingName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Toppings{
    HashMap<ToppingName, Integer> toppingList = new HashMap<>();
    HashMap<ToppingName, Double> toppingPriceList = new HashMap<>();

}
