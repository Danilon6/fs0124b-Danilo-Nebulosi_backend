package com.epicode.U5D1.repository;

import com.epicode.U5D1.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
