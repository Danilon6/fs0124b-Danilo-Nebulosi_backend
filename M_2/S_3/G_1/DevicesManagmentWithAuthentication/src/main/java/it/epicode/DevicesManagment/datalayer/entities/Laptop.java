package it.epicode.DevicesManagment.datalayer.entities;

import it.epicode.DevicesManagment.datalayer.entities.enums.Status;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("laptop")
public class Laptop extends Device{

    @Builder(setterPrefix = "with")
    public Laptop(long id, String model, String brand, Long serialNumber, double screenSize, Status status, Employee employee) {
        super(id, model, brand, serialNumber, screenSize, status, employee);
    }

    public static class LaptopBuilder extends DeviceBuilder{
        LaptopBuilder() {
            super();
        }
    }
}
