package it.epicode.DevicesManagment.datalayer.entities;

import it.epicode.DevicesManagment.datalayer.entities.enums.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("smartphone")
public class Smartphone extends Device{

    @Builder(setterPrefix = "with")
    public Smartphone(long id, String model, String brand, Long serialNumber, double screenSize, Status status, Employee employee) {
        super(id, model, brand, serialNumber, screenSize, status, employee);
    }

    public static class SmartphoneBuilder extends DeviceBuilder{
        SmartphoneBuilder() {
            super();
        }
    }
}
