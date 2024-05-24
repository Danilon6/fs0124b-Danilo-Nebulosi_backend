package it.epicode.DevicesManagment.entities;

import it.epicode.DevicesManagment.entities.enums.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("laptop")
public class Laptop extends Device{
    @Builder(setterPrefix = "with")
    public Laptop(String model, String brand, Long serialNumber, double screenSize, Status status, Employee employee) {
        super(model, brand, serialNumber, screenSize, status, employee);
    }

    public static class LaptopBuilder extends DeviceBuilder{
        LaptopBuilder() {
            super();
        }
    }
}
