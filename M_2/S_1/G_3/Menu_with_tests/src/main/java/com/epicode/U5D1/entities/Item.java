package com.epicode.U5D1.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {

	protected int calories;
	protected double price;

	public Item(int calories, double price) {
		this.calories = calories;
		this.price = price;
	}

}
