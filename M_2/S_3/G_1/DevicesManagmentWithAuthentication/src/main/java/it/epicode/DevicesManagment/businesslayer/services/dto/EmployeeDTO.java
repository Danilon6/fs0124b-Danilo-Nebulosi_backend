package it.epicode.DevicesManagment.businesslayer.services.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class EmployeeDTO extends BaseDTO{
    String firstName;
    String lastName;
    String username;
    String email;
    String password;
    String roles;
}
