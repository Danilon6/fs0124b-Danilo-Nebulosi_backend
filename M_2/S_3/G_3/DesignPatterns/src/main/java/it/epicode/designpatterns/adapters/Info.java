package it.epicode.designpatterns.adapters;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Info {
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
}
