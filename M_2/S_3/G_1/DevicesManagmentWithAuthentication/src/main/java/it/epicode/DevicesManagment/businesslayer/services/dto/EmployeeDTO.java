package it.epicode.DevicesManagment.businesslayer.services.dto;

import lombok.*;

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
}
