package com.epicode.U5D1.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "data_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Item {

	@Id
	@GeneratedValue
	private Long id;
	protected int calories;
	protected double price;

	public Item(int calories, double price) {
		this.calories = calories;
		this.price = price;
	}

}
