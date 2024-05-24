package it.epicode.DevicesManagment.repositories;

import it.epicode.DevicesManagment.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EmployeeRepository extends
        JpaRepository<Employee, Long>,
        PagingAndSortingRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByUsername(String username);
}
