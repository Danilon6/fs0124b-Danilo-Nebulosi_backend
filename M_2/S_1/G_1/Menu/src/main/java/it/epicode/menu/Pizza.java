package it.epicode.menu;

import it.epicode.menu.enums.ToppingName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Pizza extends Product {
    private HashMap<ToppingName, Integer> ingredientsList = new HashMap<>();

    public void addTopping(ToppingName toppingName, Toppings toppingsObj) {
        HashMap<ToppingName, Integer> toppingList = toppingsObj.getToppingList();
        if (toppingList.containsKey(toppingName)) {
            ingredientsList.put(toppingName, toppingList.get(toppingName));
            //OGNI VOLTA CHE VIENE AGGIUNTO UN TOPPING AUMENTO LE CALORIE DELLA PIZZA

            var totalCalories = this.getCalories();
            totalCalories += ingredientsList.get(toppingName);
            this.setCalories(totalCalories);

            //OGNI VOLTA CHE VIENE AGGIUNTO UN TOPPING AUMENTO IL PREZZO DELLA PIZZA
            var toppingPriceList = toppingsObj.getToppingPriceList();
            var newPrice = this.getPrice();
            newPrice += toppingPriceList.get(toppingName);
            this.setPrice(newPrice);
        } else {
            log.warn("This ingredients does not exist");
        }
    }
    //dopo nel bean facciamo ingredientList.put(Ingrediente, calorie)
}
