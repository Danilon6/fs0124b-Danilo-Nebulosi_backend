package it.epicode.designpatterns.COR;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class HandlerUfficiale {
    private HandlerUfficiale superiore;
    private int stipendio;

    public void doCheck(int cifra) {
        if (cifra <= stipendio) {
            System.out.println("Lo stipendio: " + stipendio + " di " + getClass().getSimpleName() + " è maggiore o uguale di:" + cifra );

        }else {
            System.out.println("Lo stipendio: " + stipendio + " di " + getClass().getSimpleName() + " è minore di " + cifra);
        }
        if (superiore !=null) {
            superiore.doCheck(cifra);
        } else {
            System.out.println("Non ci sono altri superiori");
        }



    }

}
