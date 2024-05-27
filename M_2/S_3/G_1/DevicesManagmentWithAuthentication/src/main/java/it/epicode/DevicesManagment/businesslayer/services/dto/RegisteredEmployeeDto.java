package it.epicode.DevicesManagment.businesslayer.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegisteredEmployeeDto extends BaseDTO {
    Long id;
    LocalDate createdAt;
    String firstName;
    String lastName;
    String username;
    String email;
}
