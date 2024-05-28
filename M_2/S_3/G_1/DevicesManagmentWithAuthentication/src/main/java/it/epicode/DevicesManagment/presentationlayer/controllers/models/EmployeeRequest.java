package it.epicode.DevicesManagment.presentationlayer.controllers.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeRequest(
        @NotBlank(message = "Il nome  non può contenere solo spazi vuoti")
        @Size(max = 50)
        String firstName,
        @NotBlank(message = "Il cognome  non può contenere solo spazi vuoti")
        @Size(max = 50)
        String lastName,
        @NotBlank(message = "Lo username  non può contenere solo spazi vuoti")
        @Size(max = 20, message ="Il tuo username è troppo lungo max 20 caratteri")
        String username,
        @Email
        String email,
        @NotBlank(message = "La password non può contenere solo spazi vuoti")
        @Size(max = 125, message ="La password è troppo lunga max 20 caratteri")
        String password
) {
}
