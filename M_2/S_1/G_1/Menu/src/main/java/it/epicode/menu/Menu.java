package it.epicode.menu;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
public class Menu {
    private Set<Pizza> allPizza = new HashSet<>();
    private Set<Drinks> allDrinks = new HashSet<>();
}
