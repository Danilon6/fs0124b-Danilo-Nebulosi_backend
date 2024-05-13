package it.epicode.menu;

import it.epicode.menu.enums.DrinkName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class Drinks extends Product{
    HashMap<DrinkName, Integer> drinkList = new HashMap<>();
    HashMap<DrinkName, Double> drinkPriceList = new HashMap<>();
}
