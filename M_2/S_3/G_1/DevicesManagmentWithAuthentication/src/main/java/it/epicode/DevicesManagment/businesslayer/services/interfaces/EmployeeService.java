package it.epicode.DevicesManagment.businesslayer.services.interfaces;

import it.epicode.DevicesManagment.businesslayer.services.dto.EmployeeDTO;
import it.epicode.DevicesManagment.businesslayer.services.dto.RegisteredEmployeeDto;
import it.epicode.DevicesManagment.datalayer.entities.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface EmployeeService {

    Page<Employee> getAll(Pageable p);

    Employee getById(Long id);

    RegisteredEmployeeDto save(EmployeeDTO e);

    Optional<RegisteredEmployeeDto> login(String username, String password);

    RegisteredEmployeeDto update(Long id, Employee e);

    Employee saveProfileImage(Long id, MultipartFile file) throws IOException;

    Employee delete(Long id);
}
