package it.epicode.Events.businesslayer.services.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RegisterUserDTO extends BaseDTO {
    String firstName;
    String lastName;
    String username;
    String email;
    String password;
    String roles;
}
