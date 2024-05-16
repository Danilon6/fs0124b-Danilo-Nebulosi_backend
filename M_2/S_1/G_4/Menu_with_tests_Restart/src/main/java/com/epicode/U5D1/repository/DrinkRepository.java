package com.epicode.U5D1.repository;

import com.epicode.U5D1.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    List<Drink> findByCaloriesGreaterThan(int calories);
    List<Drink> findByPriceBetween(int minPrice, int maxPrice);


    @Query("SELECT d FROM Drink d WHERE d.id = :id")
    List<Drink> trovaPerId(Long id);
}
