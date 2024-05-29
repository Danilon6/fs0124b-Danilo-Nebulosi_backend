package it.epicode.designpatterns.adapters;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Adaptee implements DataSource{

    private Info info;

    @Override
    public String getNomeCompleto() {
        return info.getNome() + " " + info.getCognome();
    }

    @Override
    public int getEta() {
        var y = LocalDate.now().getYear();
        var ytoDeduct = info.getDataDiNascita().getYear();
        return y -ytoDeduct;
    }
}
