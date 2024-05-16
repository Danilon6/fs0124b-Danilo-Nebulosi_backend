package com.epicode.U5D1.entities;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.security.SecureRandom;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topping extends Item implements Serializable {
	private String name;

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
