package it.epicode.restapiblog.controllers.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record PostRequest(
        @NotNull(message = "La categoria non può essere omessa")
        @NotBlank(message = "La categoria non può contenere solo spazi vuoti")
        @Size(max = 25)
        String category,
        @NotNull(message = "Il titolo non può essere omesso")
        @NotBlank(message = "Il titolo non può contenere solo spazi vuoti")
        @Size(max = 100)
        String title,
        @URL(message = "l'url della cover deve essere valido")
        String cover,
        @NotNull(message = "Il contenuto del post non può essere omesso")
        @NotBlank(message = "Il contenuto del post non può contenere solo spazi vuoti")
        @Size(min = 250, message = "Il contenuto del post è troppo breve min 250 caratteri")
        @Size(max = 500, message = "Il contenuto del post è troppo lungo max 500 caratteri")
        String content,
        @NotNull(message = "Devi specificare un tempo di lettura per il post")
        @Positive(message = "Il tempo di lettura deve essere un valore positivo")
        double readingTime,
        @NotNull(message = "Devi specificare quale autore sta creando il post")
        @Positive(message = "Il tempo di lettura deve essere un valore positivo")
        Long AuthorId
){}