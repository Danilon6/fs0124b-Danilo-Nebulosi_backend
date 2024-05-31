package it.epicode.Events.presentationlayer.controllers.api.models;

import it.epicode.Events.presentationlayer.controllers.utility.EqualsTo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EventModel(
        @NotBlank(message = "Il titolo non può essere vuoto")
        @Size(max = 50, message = "Il titolo non può superare i 50 caratteri")
        String title,
        @NotBlank(message = "La descrizione non può essere vuoto")
        @Size(max = 250, message = "La descrizione non può superare i 250 caratteri")
        String description,
        @EqualsTo(values = {"ROMA", "MILANO", "TORINO", "NAPOLI", "FIRENZE", "TRIESTE", "VENEZIA"})
        String place,
        @Positive(message = "Non puo inserire un numero di aprtecipanti negativo")
        int maxParticipants
) {
}
