package it.epicode.DevicesManagment.presentationlayer.controllers.api;

import it.epicode.DevicesManagment.businesslayer.services.dto.RegisteredEmployeeDto;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.ApiValidationException;
import it.epicode.DevicesManagment.presentationlayer.controllers.models.EmployeeRequest;
import it.epicode.DevicesManagment.datalayer.entities.Employee;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.EmployeeService;
import it.epicode.DevicesManagment.businesslayer.services.dto.EmployeeDTO;
import it.epicode.DevicesManagment.presentationlayer.controllers.models.LoginEmployee;
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
    public ResponseEntity<RegisteredEmployeeDto> addEmployee (@RequestBody @Validated EmployeeRequest employeeToSave, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var e = employee.save(EmployeeDTO.builder()
                        .withFirstName(employeeToSave.firstName())
                        .withLastName(employeeToSave.lastName())
                        .withUsername(employeeToSave.username())
                        .withEmail(employeeToSave.email())
                        .withPassword(employeeToSave.password())
                        .withRoles(employeeToSave.roles())
                        .build());
        return new ResponseEntity<>(e, null, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RegisteredEmployeeDto> loginEmployee (@RequestBody @Validated LoginEmployee loginEmployee, BindingResult validator) {
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var e = employee.login(loginEmployee.username(), loginEmployee.password()).orElseThrow();
        return new ResponseEntity<>(e, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee (@PathVariable Long id) {
        var e = employee.getById(id);
        return new ResponseEntity<>(e, null, HttpStatus.FOUND);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RegisteredEmployeeDto> updateEmployee (
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
        employee.delete(id);
        return ResponseEntity.noContent().build();
    }


}
