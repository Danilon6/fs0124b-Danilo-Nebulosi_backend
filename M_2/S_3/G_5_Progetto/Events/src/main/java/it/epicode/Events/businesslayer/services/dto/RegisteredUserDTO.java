package it.epicode.Events.businesslayer.services.dto;

import it.epicode.Events.datalayer.entities.Roles;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class RegisteredUserDTO {
    Long id;
    String firstName;
    String lastName;
    String username;
    String email;
    private final List<Roles> roles;

    @Builder(setterPrefix = "with")
    public RegisteredUserDTO(Long id, String firstName, String lastName, String username, String email, List<Roles> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
