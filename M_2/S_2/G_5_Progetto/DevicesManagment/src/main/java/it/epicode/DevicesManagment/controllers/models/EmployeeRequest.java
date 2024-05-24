package it.epicode.DevicesManagment.controllers.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
        @NotNull(message = "Il nome non può essere omesso")
        @NotBlank(message = "Il nome  non può contenere solo spazi vuoti")
        @Size(max = 50)
        String firstName,
        @NotNull(message = "Il cognome non può essere omesso")
        @NotBlank(message = "Il cognome  non può contenere solo spazi vuoti")
        @Size(max = 50)
        String lastName,
        @NotNull(message = "Lo username non può essere omesso")
        @NotBlank(message = "Lo username  non può contenere solo spazi vuoti")
        @Size(max = 20, message ="Il tuo username è troppo lungo max 20 caratteri")
        String username,
        @Email
        String email
) {
}
