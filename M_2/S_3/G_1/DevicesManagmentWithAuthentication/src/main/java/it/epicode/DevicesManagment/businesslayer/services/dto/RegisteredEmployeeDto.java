package it.epicode.DevicesManagment.businesslayer.services.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder(setterPrefix = "with")
public class RegisteredEmployeeDto extends BaseDTO {
    Long id;
    LocalDate createdAt;
    String firstName;
    String lastName;
    String username;
    String email;
    private final List<String> roles;

    @Builder(setterPrefix = "with")
    public RegisteredEmployeeDto(Long id, LocalDate createdAt, String firstName, String lastName, String username, String email, List<String> roles) {
        this.id = id;
        this.createdAt = createdAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
