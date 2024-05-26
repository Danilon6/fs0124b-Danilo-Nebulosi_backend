package it.epicode.DevicesManagment.services.impl;

import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateEmailException;
import it.epicode.DevicesManagment.controllers.exceptions.duplicated.DuplicateUsernameException;
import it.epicode.DevicesManagment.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.entities.Employee;
import it.epicode.DevicesManagment.repositories.EmployeeRepository;
import it.epicode.DevicesManagment.services.dto.EmployeeDTO;
import it.epicode.DevicesManagment.services.interfaces.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employee;

    private final Path directory = Paths.get("src/main/java/it/epicode/DevicesManagment/uploads");

    @Override
    public Page<Employee> getAll(Pageable p) {
        return employee.findAll(p);
    }

    @Override
    public Employee getById(Long id) {
        return employee.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public Employee save(EmployeeDTO e) {
        var emailDuplicated = employee.findByEmail(e.getEmail());
        var usernameDuplicated = employee.findByUsername(e.getUsername());

        if (emailDuplicated.isPresent()) {
            throw new DuplicateEmailException(e.getEmail());
        }else if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(e.getUsername());
        } else {
            return employee.save(
                    Employee.builder()
                            .withFirstName(e.getFirstName())
                            .withLastName(e.getLastName())
                            .withUsername(e.getUsername())
                            .withEmail(e.getEmail())
                            .build());
        }
    }

    @Override
    public Employee update(Long id, Employee e) {
        var toModify = this.getById(id);
        toModify.setFirstName(e.getFirstName());
        toModify.setLastName(e.getLastName());
        toModify.setEmail(e.getEmail());
        toModify.setUsername(e.getUsername());
        toModify.setDevices(e.getDevices());
        return employee.save(toModify);
    }

    @Override
    public Employee delete(Long id) {
        var e = this.getById(id);
        employee.delete(e);
        return e;
    }

    @Override
    public Employee saveProfileImage(Long id, MultipartFile file) throws IOException {
        Employee e = this.getById(id);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        String filename = id + "-" + file.getOriginalFilename();
        Path destinationFile = this.directory.resolve(filename);

        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        e.setProfileImagePath(destinationFile.toString());
        return employee.save(e);
    }
}
