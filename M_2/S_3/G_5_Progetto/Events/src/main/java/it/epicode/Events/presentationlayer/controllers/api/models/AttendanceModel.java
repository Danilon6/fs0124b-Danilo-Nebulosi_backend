package it.epicode.Events.presentationlayer.controllers.api.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AttendanceModel (
        @Positive(message = "Non puo inserire riferimento ad un utente che non sia un numero positivo")
        @NotNull
        Long user_id,
        @Positive(message = "Non puo inserire riferimento ad un evento che non sia un numero positivo")
        @NotNull
        Long event_id
) {

}
