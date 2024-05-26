package it.epicode.DevicesManagment.services.interfaces;

import it.epicode.DevicesManagment.entities.Employee;

import it.epicode.DevicesManagment.services.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmployeeService {

    Page<Employee> getAll(Pageable p);

    Employee getById(Long id);

    Employee save(EmployeeDTO e);

    Employee update(Long id, Employee e);

    Employee saveProfileImage(Long id, MultipartFile file) throws IOException;

    Employee delete(Long id);
}
