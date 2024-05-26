package it.epicode.DevicesManagment.controllers.api;

import it.epicode.DevicesManagment.controllers.exceptions.ApiValidationException;
import it.epicode.DevicesManagment.controllers.models.EmployeeRequest;
import it.epicode.DevicesManagment.entities.Employee;
import it.epicode.DevicesManagment.services.interfaces.EmployeeService;
import it.epicode.DevicesManagment.services.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employee;

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees (Pageable p) {
        var allEmployee = employee.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allEmployee.getTotalElements()));
        return new ResponseEntity<>(allEmployee, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee (@RequestBody @Validated EmployeeRequest employeeToSave, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var e = employee.save(EmployeeDTO.builder()
                        .withFirstName(employeeToSave.firstName())
                        .withLastName(employeeToSave.lastName())
                        .withUsername(employeeToSave.username())
                        .withEmail(employeeToSave.email())
                        .build());
        return new ResponseEntity<>(e, null, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee (@PathVariable Long id) {
        var e = employee.getById(id);
        return new ResponseEntity<>(e, null, HttpStatus.FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee (
            @PathVariable Long id,
            @RequestBody Employee employeeModified
    ) {
      var e = employee.update(id, employeeModified);
      return new ResponseEntity<>(e, null, HttpStatus.OK);
    }

    @PutMapping("/{id}/profileImage")
    public ResponseEntity<Employee> uploadProfileImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException, IOException {
        employee.saveProfileImage(id, file);
        return new ResponseEntity<>(employee.getById(id), null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee ( @PathVariable Long id){
        var e = employee.delete(id);
        return new ResponseEntity<>(e, null, HttpStatus.OK);
    }


}
