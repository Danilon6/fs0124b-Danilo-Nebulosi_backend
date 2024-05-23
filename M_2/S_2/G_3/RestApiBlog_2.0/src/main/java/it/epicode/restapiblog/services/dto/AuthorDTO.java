package it.epicode.restapiblog.services.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class AuthorDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
}
