package it.epicode.DevicesManagment.entities;

import it.epicode.DevicesManagment.entities.enums.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("smartphone")
public class Smartphone extends Device{
    @Builder(setterPrefix = "with")
    public Smartphone(String model, String brand, Long serialNumber, double screenSize, Status status, Employee employee) {
        super(model, brand, serialNumber, screenSize, status, employee);
    }

    public static class SmartphoneBuilder extends DeviceBuilder{
        SmartphoneBuilder() {
            super();
        }
    }
}
