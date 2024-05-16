package com.epicode.U5D1.repository;

import com.epicode.U5D1.entities.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
