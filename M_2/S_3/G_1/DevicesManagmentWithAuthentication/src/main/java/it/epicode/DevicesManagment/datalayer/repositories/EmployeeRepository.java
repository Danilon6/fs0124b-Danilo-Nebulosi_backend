package it.epicode.DevicesManagment.datalayer.repositories;

import it.epicode.DevicesManagment.datalayer.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EmployeeRepository extends
        JpaRepository<Employee, Long>,
        PagingAndSortingRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findOneByUsername(String username);
    Optional<Employee> findOneByUsernameAndPassword(String username, String password);

}
