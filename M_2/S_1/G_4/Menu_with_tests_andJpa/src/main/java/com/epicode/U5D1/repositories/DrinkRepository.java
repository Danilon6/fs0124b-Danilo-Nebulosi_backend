package com.epicode.U5D1.repositories;

import com.epicode.U5D1.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
