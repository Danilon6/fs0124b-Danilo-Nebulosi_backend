package it.epicode.restapiblog.controllers.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
        @NotNull(message = "Il nome non può essere omesso")
        @NotBlank(message = "Il nome  non può contenere solo spazi vuoti")
        @Size(max = 50)
        String firstName,
        @NotNull(message = "Il cognome non può essere omesso")
        @NotBlank(message = "Il cognome  non può contenere solo spazi vuoti")
        @Size(max = 50)
        String lastName,
        @Email(message = "L'email deve avere un formato corretto")
        String email,
        @NotNull(message = "La data di nascita non può essere omessa")
        @NotBlank(message = "La data non può contenere solo spazi vuoti")
        String birthDate
) {
}
