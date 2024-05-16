package com.epicode.U5D1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Menu {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "menu")
	private List<Pizza> pizzaList;
	@OneToMany(mappedBy = "menu")
	private List<Drink> drinkList;
	@OneToMany(mappedBy = "menu")
	private List<Topping> toppingList;

	public Menu(List<Pizza> pizzaList, List<Drink> drinkList, List<Topping> toppingList) {
		this.pizzaList = pizzaList;
		this.drinkList = drinkList;
		this.toppingList = toppingList;
	}

	public void printMenu() {
		System.out.println("******* Menu *******");
		System.out.println("PIZZAS");
		this.pizzaList.forEach(System.out::println);
		System.out.println();

		System.out.println("TOPPINGS");
		this.toppingList.forEach(System.out::println);
		System.out.println();

		System.out.println("DRINKS");
		this.drinkList.forEach(System.out::println);
		System.out.println();

	}
}
