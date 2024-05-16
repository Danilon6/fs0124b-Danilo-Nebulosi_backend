package com.epicode.U5D1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("topping")
public class Topping extends Item {
	private String name;

	@ManyToOne
	@JoinColumn(name = "pizza_id")
	private Pizza pizza;


	public Topping(String name, int calories, double price) {
		super(calories, price);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Topping{" +
				"name='" + name + '\'' +
				", calories=" + calories +
				", price=" + price +
				'}';
	}
}
