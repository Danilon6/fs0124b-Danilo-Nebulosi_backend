package it.epicode.Events.presentationlayer.controllers.api.models;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record LoginModel(
		@NotBlank(message = "Lo username  non può contenere solo spazi vuoti")
		@Size(max = 20, message ="Il tuo username è troppo lungo max 20 caratteri")
		String username,
		@NotBlank(message = "La password non può contenere solo spazi vuoti")
		@Size(max = 25, message ="La password è troppo lunga max 20 caratteri")
		String password
) { }
