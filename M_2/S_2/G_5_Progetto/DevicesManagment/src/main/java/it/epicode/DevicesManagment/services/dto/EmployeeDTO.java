package it.epicode.DevicesManagment.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class EmployeeDTO {
    String firstName;
    String lastName;
    String username;
    String email;
}
