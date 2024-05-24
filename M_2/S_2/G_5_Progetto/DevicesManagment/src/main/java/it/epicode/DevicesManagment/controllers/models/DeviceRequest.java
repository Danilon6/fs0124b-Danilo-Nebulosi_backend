package it.epicode.DevicesManagment.controllers.models;

import it.epicode.DevicesManagment.entities.enums.Status;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DeviceRequest(
        @NotNull(message = "Il modello non può essere omesso")
        @NotBlank(message = "Il modello  non può contenere solo spazi vuoti")
        @Size(max = 100)
        String model,
        @NotNull(message = "Il modello non può essere omesso")
        @NotBlank(message = "Il brand  non può contenere solo spazi vuoti")
        @Size(max = 50)
        String brand,
        @NotNull(message = "Il serialNumber non può essere omesso")
        Long serialNumber,
        @NotNull(message = "Lo screenSize non può essere omesso")
        double screenSize
) {
}
